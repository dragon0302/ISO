package Servlet;

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

    int prodottoID = Integer.parseInt(request.getParameter("prodottoID"));

    try {
      prodottoDAO.deleteProdotto(prodottoID);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    response.sendRedirect("Catalogo");

  }

}
