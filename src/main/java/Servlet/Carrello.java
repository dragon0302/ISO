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
            String CartValue;
            Prodotto prodotto = null;
            int quantita = 0;
            Boolean contenuto = true;

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
                quantita = 1;
                CartValue = ("(I" + prodottoID + "!" + quantita + ")").trim();
                cookieCart = new Cookie("Cart", CartValue);
                cookieCart.setMaxAge(30 * 24 * 60 * 60);
                cookieCart.setPath("/ISO_16_war_exploded");
                response.addCookie(cookieCart);
            }else {
                CartValue = cookieCart.getValue();
                if(!CartValue.contains("I" + prodottoID)) {
                    quantita = 1;
                    CartValue += ("-" + "(I" + prodottoID + "!" + quantita + ")").trim();
                    cookieCart.setValue(CartValue);
                    response.addCookie(cookieCart);
                } else {
                    String[] Cprodotti = CartValue.split("-");
                    StringBuilder newCartValue = new StringBuilder();

                    for (String item : Cprodotti) {
                        if (item.contains("I" + prodottoID)) {
                            // Estrai e incrementa quantità
                            quantita = Integer.parseInt(item.split("!")[1].replace(")", ""));
                            quantita++;
                            // Ricostruisci l'item
                            item = "(I" + prodottoID + "!" + quantita + ")";
                        }
                        if(!newCartValue.isEmpty()) {
                            newCartValue.append("-");
                        }
                        newCartValue.append(item);
                    }

                    // Aggiorna il cookie
                    cookieCart.setValue(newCartValue.toString());
                    response.addCookie(cookieCart);
                }
            }

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
