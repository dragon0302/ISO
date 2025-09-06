package Controller;

import Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Showproduct")
public class Showproduct extends HttpServlet {

  UtenteDAO utenteDAO = new UtenteDAOImplement();
  CarrelloDAO carrelloDAO = new CarrelloDAOImplement();
  OrdineDAO ordineDAO = new OrdineDAOImplement();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    String nomeUtente = request.getParameter("nomeUtente");

    try {
      List<Prodotto> prodotti = ordineDAO.getProdottiByUser(carrelloDAO.GetIdCarrello(utenteDAO.getCF(nomeUtente)));

      for (Prodotto prodotto : prodotti) {
        System.out.println(prodotto.getId_prodotto());
      }

      response.sendRedirect("/FILE_JSP/Impostazioni.jsp");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

}
