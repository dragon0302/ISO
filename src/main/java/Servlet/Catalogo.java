package Servlet;

import DataManagement.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
    ProdottoDAO prodottoDAO = new ProdottoDAOImplement();
    AcquistoDAO acquistoDAO = new AcquistoDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Prodotto> prodottiRecenti = prodottoDAO.getProdottiRecenti();
            List<Integer> IDprodottiPiuAcquistati = acquistoDAO.getProdottiPiuAqquistati();
            List<Prodotto> prodottiPiuAcquistai = new ArrayList<>();
            for (int ID : IDprodottiPiuAcquistati) {

                Prodotto prodotto = prodottoDAO.getProdottoByID(ID);
                prodotto.setId_prodotto(ID);
                prodottiPiuAcquistai.add(prodotto);

            }

            request.setAttribute("prodottiNovita", prodottiRecenti);
            request.setAttribute("prodottiPiuAqqistati", prodottiPiuAcquistai);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
            dispatcher.forward(request, response);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
