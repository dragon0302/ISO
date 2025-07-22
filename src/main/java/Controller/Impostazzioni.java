package Controller;

import Model.*;
import Model.Indirizzo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Impostazioni")
public class Impostazzioni extends HttpServlet {

  IndirizzoDAO indirizzoDAO = new IndirizzoDAOImplement();

  protected void doGet(HttpServletRequest request, HttpServletResponse response){

    try {

      HttpSession session = request.getSession();
      Utente utente = (Utente) session.getAttribute("utente");
      List<Indirizzo> indirizzi = indirizzoDAO.getIndirizzo(utente.getCf());

      request.setAttribute("listaIndirizzi", indirizzi);

      request.getRequestDispatcher("/FILE_JSP/Impostazioni.jsp").forward(request, response);

    } catch (SQLException | ServletException | IOException e) {
      throw new RuntimeException(e);
    }

  }

}
