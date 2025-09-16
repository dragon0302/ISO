package Controller;

import Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Ordini")
public class Ordini extends HttpServlet {

  CarrelloDAO carrelloDAO = new CarrelloDAOImplement();
  OrdineDAO ordineDAO = new OrdineDAOImplement();

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    Utente utente = (Utente) session.getAttribute("utente");
    List<Prodotto> ProdottiOrdine = new ArrayList<Prodotto>();
    List<List<Prodotto>> ListeProdotti = new ArrayList<>();

    try {

      List<Ordine> listaOrdini = ordineDAO.getOrdersByUser(carrelloDAO.GetIdCarrello(utente.getCf()));

      session.setAttribute("ordine", listaOrdini);

      for (Ordine ordine : listaOrdini) {
        ProdottiOrdine = ordineDAO.getProdotti(ordine.getIdOrdine());
        ListeProdotti.add(ProdottiOrdine);

      }

      session.setAttribute("ListeProdotti", ListeProdotti);

      response.sendRedirect("FILE_JSP/Lista_ordini.jsp");

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

}
