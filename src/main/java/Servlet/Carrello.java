package Servlet;

import DataManagement.Prodotto;
import DataManagement.ProdottoDAO;
import DataManagement.ProdottoDAOImplement;
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

@WebServlet("/Carrello")
public class Carrello extends HttpServlet {

    ProdottoDAO prodottoDAO = new ProdottoDAOImplement();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> ids = new ArrayList<>();

        try {
            CookieManagemnt cm = new CookieManagemnt(request);
            HttpSession session = request.getSession();
            List<Integer> Nquantita = new ArrayList<>();
            List<Prodotto> carrello = new ArrayList<>();
            Prodotto prodotto = null;
            ids = cm.AllCookieId();

            if (ids != null) {

                carrello = (List<Prodotto>) session.getAttribute("carrello");
                Nquantita = (List<Integer>) session.getAttribute("Quantità");

                if (carrello == null) {
                    carrello = new ArrayList<>();
                    session.setAttribute("carrello", carrello);
                }

                System.out.println("numero prodtti" + carrello.size());

                for (String id : ids) {
                    prodotto = prodottoDAO.getProdottoByID(Integer.parseInt(id));

                    if (carrello.isEmpty()){
                        carrello.add(prodotto);
                    }

                    Prodotto finalProdotto = prodotto;
                    if (!(carrello.stream().anyMatch(p -> p.getId_prodotto() == finalProdotto.getId_prodotto()))) {
                        carrello.add(prodotto);
                    }

                    if (Nquantita == null) {
                        Nquantita = new ArrayList<>();
                        session.setAttribute("quantità", Nquantita);
                    }

                    if (carrello.size() > Nquantita.size()) {

                        Nquantita.add(cm.getCookieProductQuantity(id));
                    } else {

                        for (int i = 0; i < carrello.size(); i++) {

                            if (carrello.get(i).getId_prodotto() == Integer.parseInt(id)) {
                                Nquantita.set(i, cm.getCookieProductQuantity(id));
                            }

                        }
                    }
                }
            }

            session.setAttribute("carrello", carrello);
            session.setAttribute("Quantità", Nquantita);

            response.sendRedirect("Carrello.jsp");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
