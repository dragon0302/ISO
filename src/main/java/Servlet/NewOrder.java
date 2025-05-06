package Servlet;

import DataManagement.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class NewOrder extends HttpServlet {

    OrdineDAO ordineDAO = new OrdineDAOImplement();
    CarrelloDAO carrelloDAO = new CarrelloDAOImplement();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            String idProdotti = null;

            List<Prodotto> prodotti = (List<Prodotto>) session.getAttribute("carrello");
            float PrezzoTotale = (float) request.getAttribute("prezzototale");
            String CFutente = (String) session.getAttribute("cfutente");

            for (Prodotto prodotto : prodotti) {

                if (idProdotti == null) {
                    idProdotti = prodotto + ",";
                }else {
                    idProdotti += prodotto.getId_prodotto() + ",";
                }
            }

            Ordine ordine = new Ordine(Date.valueOf(LocalDate.now()),PrezzoTotale,idProdotti,carrelloDAO.GetIdCarrello(CFutente));
            ordineDAO.DoSave(ordine);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
