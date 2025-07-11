package Servlet;

import DataManagement.*;
import Utility.CookieManagemnt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/ProductCartMenegment")
public class ProductCartMenegment extends HttpServlet {

    CarrelloDAO carrelloDAO = new CarrelloDAOImplement();
    AcquistoDAO acquistoDAO = new AcquistoDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String prodottoID = request.getParameter("prodottoID");
            String sourcePage = request.getParameter("SourcePage");
            Utente utente = (Utente) request.getSession().getAttribute("utente");
            CookieManagemnt cm = new CookieManagemnt(request);
            String redirectPage = null;
            Boolean contenuto = true;

            if(utente == null) {
                if (prodottoID != null) {
                    cm.CookieCartManagemnt(response, prodottoID);
                }
            }else {

                if (!carrelloDAO.CeckProdotto(Integer.parseInt(prodottoID),utente.getCf())) {
                    carrelloDAO.ProdottoCarello(Integer.parseInt(prodottoID), carrelloDAO.GetIdCarrello(utente.getCf()));
                    Acquisto acquisto = new Acquisto(false,1, carrelloDAO.GetIdCarrello(utente.getCf()),Integer.parseInt(prodottoID));
                    acquistoDAO.DoSave(acquisto);
                }else {
                    acquistoDAO.UpdateQuantity(Integer.parseInt(prodottoID), carrelloDAO.GetIdCarrello(utente.getCf()), '+');
                }
            }

            if("Prodotto".equals(sourcePage)) {
                redirectPage = "ProdottoS?id=" + prodottoID;
            }else if("Home".equals(sourcePage)) {
                redirectPage = "Catalogo";
            }

            response.sendRedirect(redirectPage);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
