package Controller;

import Model.*;
import Utility.CookieManagemnt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carrello")
public class Carrello extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    ProdottoDAO prodottoDAO = new ProdottoDAOImplement();
    CarrelloDAO carrelloDAO = new CarrelloDAOImplement();
    AcquistoDAO acquistoDAO = new AcquistoDAOImplement();

    try {

      //prendo i dati
      HttpSession session = request.getSession();
      Utente utente = (Utente) session.getAttribute("utente");
      CookieManagemnt cm = new CookieManagemnt(request);
      List<Integer> Nquantita = new ArrayList<>();
      List<Prodotto> carrello = new ArrayList<>();
      List<String> ids = new ArrayList<>();
      Prodotto prodotto = null;
      Float prezzototale = null;
      Float spesespedizione =7F;


      if (utente == null) {

        //recupero gli id dei prodotti che si trovano all'interno del cookie
        ids = cm.AllCookieId();

        if (ids != null) {


          //verifico se i campi sono inizializzati
          prezzototale = VerIniCampi(Nquantita,carrello,prezzototale, session);

          for (String id : ids){

            //vado a prendere i prodotti che sono nel cookie
            prodotto = prodottoDAO.getProdottoByID(Integer.parseInt(id));

            Prodotto finalProdotto = prodotto;
            //vado ad aggungere un prodotto al carrello se non è già presente
            if (!(carrello.stream().anyMatch(p -> p.getId_prodotto() == finalProdotto.getId_prodotto()))) {
              carrello.add(prodotto);
            }

            //vado a controllare se è già stata aggunta la quantità dell'ultimo prodotto
            if (carrello.size() != Nquantita.size()) {

              Nquantita.add(cm.getCookieProductQuantity(id));
              prezzototale += finalProdotto.getPrezzo() * cm.getCookieProductQuantity(id);

            } else {

              for (int i = 0; i < carrello.size(); i++) {

                if (carrello.get(i).getId_prodotto() == Integer.parseInt(id)) {
                  int dif = cm.getCookieProductQuantity(id) - Nquantita.get(i);
                  Nquantita.set(i, cm.getCookieProductQuantity(id));
                  float temp = dif * finalProdotto.getPrezzo();

                  prezzototale += temp;
                }

              }
            }

          }

        }else  {
          prezzototale = 0f;
        }

      }else{

        //verifico se i campi sono inizzializzati
        prezzototale = VerIniCampi(Nquantita,carrello,prezzototale, session);

        //mi faccio dare gli di dei prodotti che sono presenti nel carello all'interno del database
        ids = carrelloDAO.GetProductCarello(carrelloDAO.GetIdCarrello(utente.getCf()));

        //verifico se ci prodotti nel carello
        if (ids != null) {

          for (String id : ids){

            //converto gli id nei prodotti
            prodotto = prodottoDAO.getProdottoByID(Integer.parseInt(id));

            Prodotto finalProdotto = prodotto;
            if (!(carrello.stream().anyMatch(p -> p.getId_prodotto() == finalProdotto.getId_prodotto()))) {
              carrello.add(prodotto);
            }

            if (carrello.size() > Nquantita.size()) {

              Nquantita.add(acquistoDAO.GetQuntita(carrelloDAO.GetIdCarrello(utente.getCf()), Integer.parseInt(id)));
              prezzototale += prodotto.getPrezzo() * acquistoDAO.GetQuntita(carrelloDAO.GetIdCarrello(utente.getCf()), Integer.parseInt(id));

            }

          }

        }else{
          prezzototale = 0f;
        }

      }

      if (prezzototale != 0.0F) {
        prezzototale+=spesespedizione;
      }
      session.setAttribute("carrello", carrello);
      session.setAttribute("Quantità", Nquantita);
      session.setAttribute("prezzotatale", prezzototale);
      session.setAttribute("spesespedizione", spesespedizione);
      response.sendRedirect("FILE_JSP/Carrello.jsp");

    }catch (Exception e) {
      e.printStackTrace();
    }

  }

  protected Float VerIniCampi(List<Integer> Nquantita, List<Prodotto> carrello, Float prezzototale, HttpSession session) {
    carrello = (List<Prodotto>) session.getAttribute("carrello");
    Nquantita = (List<Integer>) session.getAttribute("Quantità");
    prezzototale = (Float) session.getAttribute("prezzotatale");

    //verifico se i valori sono già stati inizzializzati
    if (carrello == null) {
      carrello = new ArrayList<>();
      session.setAttribute("carrello", carrello);
    }

    if (Nquantita == null) {
      Nquantita = new ArrayList<>();
      session.setAttribute("quantità", Nquantita);
    }

    if (prezzototale == null) {
      prezzototale = 0f;
    }

    return prezzototale;
  }

}
