package Servlet;

import DataManagement.Prodotto;
import DataManagement.ProdottoDAO;
import DataManagement.ProdottoDAOImplement;
import jakarta.servlet.RequestDispatcher;
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
            Cookie[] cookies = request.getCookies();
            Cookie cookieCart = null;
            String CartValue = null;
            Prodotto prodotto = null;

            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("Cart")) {
                        cookieCart = c;
                        CartValue = c.getValue();
                        break;
                    }
                }
            }

            System.out.println(prodottoID);

            if(cookieCart == null) {
                CartValue = prodottoID;
                cookieCart = new Cookie("Cart", CartValue);
                cookieCart.setMaxAge(30 * 24 * 60 * 60);
                cookieCart.setPath("/ISO_16_war_exploded");
                response.addCookie(cookieCart);
            }else {
                CartValue = cookieCart.getValue();
                if(!CartValue.contains(String.valueOf(prodottoID))) {
                    CartValue += "|" + prodottoID;
                    cookieCart.setValue(CartValue);
                    response.addCookie(cookieCart);
                }
            }

            prodotto = prodottoDAO.getProdottoByID(Integer.parseInt(prodottoID));

            Integer numero = (Integer) session.getAttribute("numero_" + prodottoID);
            System.out.println("servlet " + numero);
            if(numero == null) {
                numero = 1;
            } else {
                numero++;
            }
            session.setAttribute("numero", numero);

            List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");

            if (carrello == null) {
                carrello = new ArrayList<>();
                session.setAttribute("carrello", carrello);
            }

            if (carrello.size() == 0) {
                carrello.add(prodotto);
            }

            for (int i = 0; i < carrello.size(); i++) {
                if (carrello.get(i).getId_prodotto() != prodotto.getId_prodotto()) {
                    carrello.add(prodotto);
                }
            }

            session.setAttribute("carrello", carrello);

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
