package Servlet;

import DataManagement.*;
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
    CarrelloDAO carrelloDAO = new CarrelloDAOImplement();
    AcquistoDAO acquistoDAO = new AcquistoDAOImplement();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            Utente utente = (Utente) request.getSession().getAttribute("utente");
            CookieManagemnt cm = new CookieManagemnt(request);
            HttpSession session = request.getSession();
            List<Integer> Nquantita = new ArrayList<>();
            List<Prodotto> carrello = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            Prodotto prodotto = null;
            float prezzototale = 0;

            if (utente == null ) {
                ids = cm.AllCookieId();

                if (ids != null) {

                    carrello = (List<Prodotto>) session.getAttribute("carrello");
                    Nquantita = (List<Integer>) session.getAttribute("Quantità");
                    if (session.getAttribute("prezzotatale") == null) {
                        prezzototale = 0;
                    }else {
                        prezzototale = (float) session.getAttribute("prezzotatale");
                    }

                    if (carrello == null) {
                        carrello = new ArrayList<>();
                        session.setAttribute("carrello", carrello);
                    }

                    for (String id : ids) {
                        prodotto = prodottoDAO.getProdottoByID(Integer.parseInt(id));

                        if (carrello.isEmpty()) {
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
                            prezzototale += finalProdotto.getPrezzo() * cm.getCookieProductQuantity(id);
                        } else {

                            for (int i = 0; i < carrello.size(); i++) {

                                if (carrello.get(i).getId_prodotto() == Integer.parseInt(id)) {
                                    int dif = cm.getCookieProductQuantity(id) - Nquantita.get(i);
                                    Nquantita.set(i, cm.getCookieProductQuantity(id));
                                    float temp = dif * finalProdotto.getPrezzo();

                                    prezzototale += temp;
                                }

                            }
                        }
                    }
                }
            }else {

                ids = carrelloDAO.GetProductCarello(carrelloDAO.GetIdCarrello(utente.getCf()));
                carrello = (List<Prodotto>) session.getAttribute("carrello");
                Nquantita = (List<Integer>) session.getAttribute("Quantità");


                if (session.getAttribute("prezzotatale") == null) {
                    prezzototale = 0;
                }else {
                    prezzototale = (float) session.getAttribute("prezzotatale");
                }

                if (carrello == null) {
                    carrello = new ArrayList<>();
                    session.setAttribute("carrello", carrello);
                }

                if (Nquantita == null) {
                    Nquantita = new ArrayList<>();
                    session.setAttribute("quantità", Nquantita);
                }

                if (ids != null) {
                    for (String id : ids) {
                        prodotto = prodottoDAO.getProdottoByID(Integer.parseInt(id));


                        if (carrello.isEmpty()) {
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

                            Nquantita.add(acquistoDAO.GetQuntita(carrelloDAO.GetIdCarrello(utente.getCf()), Integer.parseInt(id)));
                            prezzototale += prodotto.getPrezzo() * acquistoDAO.GetQuntita(carrelloDAO.GetIdCarrello(utente.getCf()), Integer.parseInt(id));
                        } else {

                            for (int i = 0; i < carrello.size(); i++) {
                                if (carrello.get(i).getId_prodotto() == Integer.parseInt(id)) {

                                    if (Nquantita.get(i) < acquistoDAO.GetQuntita(carrelloDAO.GetIdCarrello(utente.getCf()), Integer.parseInt(id))) {
                                        int temp = acquistoDAO.GetQuntita(carrelloDAO.GetIdCarrello(utente.getCf()), Integer.parseInt(id)) - Nquantita.get(i);
                                        prezzototale += (prodotto.getPrezzo() * temp);
                                    }

                                    Nquantita.set(i, acquistoDAO.GetQuntita(carrelloDAO.GetIdCarrello(utente.getCf()), Integer.parseInt(id)));

                                }

                            }
                        }
                    }
                }

            }


            session.setAttribute("carrello", carrello);
            session.setAttribute("Quantità", Nquantita);
            session.setAttribute("prezzotatale", prezzototale);

            response.sendRedirect("Carrello.jsp");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
