package Servlet;

import DataManagement.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
    ProdottoDAO prodottoDAO = new ProdottoDAOImplement();
    AcquistoDAO acquistoDAO = new AcquistoDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String ricerca = request.getParameter("search");
            Integer giorni = null;
            if(request.getParameter("giorni") != null) {
                giorni = Integer.parseInt(request.getParameter("giorni"));
            }else {
                giorni=10;
            }

            Integer quantita =null;
            if(request.getParameter("maxVendite") != null) {
                quantita = Integer.parseInt(request.getParameter("maxVendite"));
            }else {
                quantita=10;
            }
            List<Prodotto> prodottiRecenti = prodottoDAO.getProdottiRecenti(giorni);
            List<Integer> IDprodottiPiuAcquistati = acquistoDAO.getProdottiPiuAqquistati(quantita);
            List<Prodotto> prodottiPiuAcquistai = new ArrayList<>();
            for (int ID : IDprodottiPiuAcquistati) {

                Prodotto prodotto = prodottoDAO.getProdottoByID(ID);
                prodotto.setId_prodotto(ID);
                prodottiPiuAcquistai.add(prodotto);

            }

            request.setAttribute("prodottiNovita", prodottiRecenti);
            request.setAttribute("prodottiPiuAqqistati", prodottiPiuAcquistai);

            String Filter = request.getParameter("value");

            if (Filter != null) {
                request.setAttribute("Filter", Filter);

                List<Prodotto> prodottiFiltro = prodottoDAO.SerchByCategory(Filter);

                request.setAttribute("prodottiFiltro", prodottiFiltro);
            } else if (ricerca != null) {

                request.setAttribute("Filter", ricerca);

                List<Prodotto> prodottiFiltro = prodottoDAO.SerchByCategory(ricerca);

                request.setAttribute("prodottiFiltro", prodottiFiltro);
                
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/FILE_JSP/home.jsp");
            dispatcher.forward(request, response);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
