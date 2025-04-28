package Servlet;

import DataManagement.Prodotto;
import DataManagement.ProdottoDAO;
import DataManagement.ProdottoDAOImplement;
import Utility.CookieManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Carrello")
public class Carrello extends HttpServlet {

    ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String prodottoID = request.getParameter("prodottoID");
            String sourcePage = request.getParameter("SourcePage");
            HttpSession session = request.getSession();
            String redirectPage = null;
            Prodotto prodotto = null;
            Boolean contenuto = true;
            int quantita = 0;
            CookieManagement cookieManagement = new CookieManagement(request);

            quantita = cookieManagement.CookieCartManagement(prodottoID,response,request);

            prodotto = prodottoDAO.getProdottoByID(Integer.parseInt(prodottoID));

            List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
            List<Integer> Nquantita = (List<Integer>) session.getAttribute("Quantità");

            if (carrello == null) {
                carrello = new ArrayList<>();
                session.setAttribute("carrello", carrello);
            }

            if (carrello.isEmpty()) {
                carrello.add(prodotto);
            }


            for (int i = 0; i < carrello.size(); i++) {
                if (carrello.get(i).getId_prodotto() == prodotto.getId_prodotto()) {
                    contenuto = false;
                }
            }

            if(contenuto){
                carrello.add(prodotto);
            }

            if (Nquantita == null) {
                Nquantita = new ArrayList<>();
                session.setAttribute("quantità", Nquantita);
            }

            if(carrello.size() > Nquantita.size()) {

                Nquantita.add(quantita);
            }else {

                for (int i = 0; i < carrello.size(); i++) {

                    if (carrello.get(i).getId_prodotto() == Integer.parseInt(prodottoID)) {
                        Nquantita.set(i, quantita);
                    }

                }
            }
            session.setAttribute("carrello", carrello);
            session.setAttribute("Quantità", Nquantita);

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
