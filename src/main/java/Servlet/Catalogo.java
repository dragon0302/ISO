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
            List<Prodotto> prodottiRecenti = prodottoDAO.getProdottiRecenti();
            List<Integer> IDprodottiPiuAcquistati = acquistoDAO.getProdottiPiuAqquistati();
            List<Prodotto> prodottiPiuAcquistai = new ArrayList<>();
            List<String> paths = new ArrayList<>();
            for (int ID : IDprodottiPiuAcquistati) {

                Prodotto prodotto = prodottoDAO.getProdottoByID(ID);
                prodotto.setId_prodotto(ID);
                prodottiPiuAcquistai.add(prodotto);

                String nomeFile = prodotto.getNome() + ".jpg";
                Path percorsoImmagine = Paths.get(request.getServletContext().getRealPath("/Immagini"), nomeFile);

                if (Files.exists(percorsoImmagine)){

                    paths.add("/Immagini/" + prodotto.getNome() + ".jpg");

                }else {

                    paths.add("/Immagini/" + "ironmaden.png");

                }

            }

            request.setAttribute("paths", paths);
            request.setAttribute("prodottiNovita", prodottiRecenti);
            request.setAttribute("prodottiPiuAqqistati", prodottiPiuAcquistai);

            String Filter = request.getParameter("value");

            request.setAttribute("Filter", Filter);

            List<Prodotto> prodottiFiltro = prodottoDAO.SerchByCategory(Filter);

            request.setAttribute("prodottiFiltro", prodottiFiltro);

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
