package Servlet;

import DataManagement.Prodotto;
import DataManagement.ProdottoDAO;
import DataManagement.ProdottoDAOImplement;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Prodotto> prodotti = prodottoDAO.getProdottiRecenti();

            request.setAttribute("prodottiNovita", prodotti);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
