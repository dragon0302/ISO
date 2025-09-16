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
            String quantities = null;

            List<Prodotto> prodotti = (List<Prodotto>) session.getAttribute("carrello");
            float PrezzoTotale = (float) session.getAttribute("prezzotatale");
            Utente utente = (Utente) session.getAttribute("utente");
            Integer ID_indirizzo = (Integer) session.getAttribute("ID_indirizzo");
            List<Integer> quantita = (List<Integer>) session.getAttribute("Quantità");

            for (Prodotto prodotto : prodotti) {

                if (idProdotti == null) {
                    idProdotti = prodotto.getId_prodotto() + ",";
                }else {
                    idProdotti += prodotto.getId_prodotto() + ",";
                }
            }
            for (Integer quantity : quantita) {

                if (quantities == null) {
                    quantities = quantity + ",";
                }else {
                    quantities += quantity + ",";
                }
            }

            Ordine ordine = new Ordine(Date.valueOf(LocalDate.now()),PrezzoTotale,idProdotti,quantities,carrelloDAO.GetIdCarrello(utente.getCf()),ID_indirizzo);
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
