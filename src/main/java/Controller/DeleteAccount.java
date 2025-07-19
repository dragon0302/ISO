package Controller;

import Model.Utente;
import Model.UtenteDAO;
import Model.UtenteDAOImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {

  UtenteDAO utenteDAO = new UtenteDAOImplement();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    Utente utente = (Utente) session.getAttribute("utente");

    try {
      utenteDAO.remouveUtente(utente);

      response.sendRedirect(request.getContextPath() + "/Catalogo");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

}
