package Servlet;

import DataManagement.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/selectDefault")
public class selectDefault extends HttpServlet {

  IndirizzoDAO indirizzoDAO = new IndirizzoDAOImplement();
  MetodoPagamentoDAO metodoPagamentoDAO = new MetodoPagamentoDAOImplement();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    Utente utente = (Utente) session.getAttribute("utente");
    Integer indirizzoFatturazione = null;
    if (request.getParameter("indirizzoFatturazione") != null) {
      indirizzoFatturazione = Integer.parseInt(request.getParameter("indirizzoFatturazione"));
    }
    String pagamentoDefault = String.valueOf(request.getParameter("pagamentoDefault"));

    if(indirizzoFatturazione != null){

      try {
        indirizzoDAO.setDefaultIndirizzo(indirizzoFatturazione, utente.getCf());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

    }

    if(pagamentoDefault != null){
      System.out.println(pagamentoDefault);

      try {
        metodoPagamentoDAO.setPagamentoDefault(pagamentoDefault, utente.getCf());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

    }

    response.sendRedirect(request.getContextPath() + "/Impostazioni");

  }

}
