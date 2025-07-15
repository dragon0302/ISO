package Servlet;

import DataManagement.*;
import DataManagement.Indirizzo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/NewIndirizzo")
public class NewIndirizzo extends HttpServlet {

  IndirizzoDAO indirizzoDAO = new IndirizzoDAOImplement();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
    Utente utente = (Utente) session.getAttribute("utente");
    String citta = request.getParameter("Città");
    String Provincia = request.getParameter("Provincia");
    String Cap = request.getParameter("CAP");
    String Via = request.getParameter("Via");
    int Civico = Integer.parseInt(request.getParameter("Civico"));
    String Scala = request.getParameter("Scala");
    String Indirizzo2 = request.getParameter("Indirizzo2");
    String note = request.getParameter("Note");
    Boolean Fatturazione = Boolean.parseBoolean(request.getParameter("Fatturazione"));

    Indirizzo indirizzo = new Indirizzo(citta,Provincia,Cap,Via,Civico,Scala,Indirizzo2,note,Fatturazione, utente.getCf());
    try {
      indirizzoDAO.doSave(indirizzo);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    response.sendRedirect("Catalogo");

  }

}
