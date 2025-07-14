package Servlet;

import DataManagement.Prodotto;
import DataManagement.ProdottoDAO;
import DataManagement.ProdottoDAOImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ProductCatalogoMenegment")
public class ProductCatalogoMenegment extends HttpServlet {

  ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String action = request.getParameter("action");
    int prodottoID = Integer.parseInt(request.getParameter("id_prodotto"));
    String nome = request.getParameter("nome");
    float prezzo = Float.parseFloat(request.getParameter("prezzo"));
    String descrizzione = request.getParameter("descrizione");

    if(action.equals("modifica")) {

        try {

          Prodotto prodotto = prodottoDAO.getProdottoByID(prodottoID);
          prodottoDAO.editNomeProdotto(prodotto, nome);
          prodottoDAO.editPrezzo(prodotto, prezzo);
          prodottoDAO.editDescrizione(prodotto, descrizzione);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*try {
      prodottoDAO.deleteProdotto(prodottoID);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }*/

    response.sendRedirect("Catalogo");

  }

}
