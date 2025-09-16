package Controller;

import Model.*;
import Utility.CookieManagemnt;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@WebServlet("/Updatecarrello")
public class UpdateCarrello extends HttpServlet {

    UtenteDAO utenteDAO = new UtenteDAOImplement();
    CarrelloDAO carrelloDAO = new CarrelloDAOImplement();
    AcquistoDAO acquistoDAO = new AcquistoDAOImplement();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JsonObject json = new JsonObject();
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter("productID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float prezzo = Float.parseFloat(request.getParameter("p"));
        Utente utente = (Utente) session.getAttribute("utente");
        float prezzoTotale = 0;
        float spesespedizione = (float) session.getAttribute("spesespedizione");
        ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

        if(request.getParameter("quantity") == null){
            System.out.println("errore");
        }

        if (utente == null) {
            CookieManagemnt cookieManagemnt = new CookieManagemnt(request);

            List<String> ids = cookieManagemnt.AllCookieId();

            for (int i = 0; i < ids.size(); i++) {

                if (Objects.equals(ids.get(i), String.valueOf(productId))) {
                    int quantita = cookieManagemnt.getCookieProductQuantity(ids.get(i));

                    if (quantity == 0){
                        cookieManagemnt.RemouveProduct(response, String.valueOf(productId));
                    }else if (quantita != quantity) {
                        cookieManagemnt.updateCookieProductQuantity(response, String.valueOf(productId), quantity);
                    }

                }

                try {
                    prezzoTotale += (prodottoDAO.GetPrezzo(Integer.parseInt(ids.get(i))) * cookieManagemnt.getCookieProductQuantity(ids.get(i)));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }else {

            try {
                int quantita = acquistoDAO.GetQuntita(carrelloDAO.GetIdCarrello(utente.getCf()), productId);

                if (quantity == 0){
                    acquistoDAO.remuveAcquisto(acquistoDAO.getIdAcquisto(productId,carrelloDAO.GetIdCarrello(utente.getCf())));
                    carrelloDAO.remuveProcductCarello(String.valueOf(productId),utente.getCf());
                }else if (quantita != quantity) {
                    acquistoDAO.UpdateQuantity(productId, carrelloDAO.GetIdCarrello(utente.getCf()), quantity);
                }

                List<String> ids = carrelloDAO.GetProductCarello(carrelloDAO.GetIdCarrello(utente.getCf()));
                for (int i = 0; i < ids.size(); i++){

                    prezzoTotale += (prodottoDAO.GetPrezzo(Integer.parseInt(String.valueOf(ids.get(i)))) * acquistoDAO.GetQuntita(carrelloDAO.GetIdCarrello(utente.getCf()), Integer.parseInt(ids.get(i))));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        if ((prezzoTotale - spesespedizione) != prezzoTotale) {
            prezzoTotale += spesespedizione;
        }

        json.addProperty("success", true);
        json.addProperty("prezzototale", prezzoTotale);
        session.setAttribute("prezzotatale", prezzoTotale);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);

    }

}