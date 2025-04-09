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
            Cookie[] cookies = request.getCookies();
            Cookie cookieCart = null;
            String CartValue = null;

            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("Cart")) {
                        cookieCart = c;
                        CartValue = c.getValue();
                        break;
                    }
                }
            }

            if(cookieCart == null) {
                CartValue = prodottoID;
                cookieCart = new Cookie("Cart", CartValue);
                cookieCart.setMaxAge(30 * 24 * 60 * 60);
                cookieCart.setPath("/ISO_16_war_exploded");
                response.addCookie(cookieCart);
            }else {
                CartValue = cookieCart.getValue();
                if(!CartValue.contains(prodottoID)) {
                    CartValue += "|" + prodottoID;
                    cookieCart.setValue(CartValue);
                    response.addCookie(cookieCart);
                }
            }

            Prodotto prodotto = prodottoDAO.getProdottoByID(Integer.parseInt(prodottoID));

            HttpSession session = request.getSession();

            List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");

            if (carrello == null) {
                carrello = new ArrayList<>();
                session.setAttribute("carrello", carrello);
            }

            carrello.add(prodotto);

            session.setAttribute("carrello", carrello);

            response.sendRedirect("Catalogo");

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
