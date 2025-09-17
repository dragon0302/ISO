package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import Model.ProdottoDAOImplement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ProdottoS")
public class ProdottoS extends HttpServlet {

  ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      int idProdtto = Integer.parseInt(request.getParameter("id"));

      Prodotto prodotto = prodottoDAO.getProdottoByID(idProdtto);

      request.setAttribute("Prodotto", prodotto);

      RequestDispatcher dispatcher = request.getRequestDispatcher("/FILE_JSP/Prodotto.jsp");
      dispatcher.forward(request, response);

    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
