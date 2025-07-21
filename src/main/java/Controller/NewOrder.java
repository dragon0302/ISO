package Controller;

import Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/NewOrder")
public class NewOrder extends HttpServlet {

    OrdineDAO ordineDAO = new OrdineDAOImplement();
    CarrelloDAO carrelloDAO = new CarrelloDAOImplement();
    AcquistoDAO acquistoDAO = new AcquistoDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            String idProdotti = null;

            List<Prodotto> prodotti = (List<Prodotto>) session.getAttribute("carrello");
            float PrezzoTotale = (float) session.getAttribute("prezzotatale");
            Utente utente = (Utente) session.getAttribute("utente");
            Integer ID_indirizzo = (Integer) session.getAttribute("ID_indirizzo");

            for (Prodotto prodotto : prodotti) {

                if (idProdotti == null) {
                    idProdotti = prodotto.getId_prodotto() + ",";
                }else {
                    idProdotti += prodotto.getId_prodotto() + ",";
                }
            }
            System.out.println("prova");

            Ordine ordine = new Ordine(Date.valueOf(LocalDate.now()),PrezzoTotale,idProdotti,carrelloDAO.GetIdCarrello(utente.getCf()),ID_indirizzo);
            ordineDAO.DoSave(ordine);

            List<Integer> IDaqquisti = acquistoDAO.getAqquistiByUser(carrelloDAO.GetIdCarrello(utente.getCf()));

            for (int id : IDaqquisti) {

                acquistoDAO.remuveAcquisto(id);

            }

            carrelloDAO.remouveProduct(carrelloDAO.GetIdCarrello(utente.getCf()));

            response.sendRedirect("Catalogo");


        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
