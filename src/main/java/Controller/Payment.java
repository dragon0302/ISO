package Controller;

import Model.Indirizzo;
import Model.IndirizzoDAO;
import Model.IndirizzoDAOImplement;
import Model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Payment")
public class Payment extends HttpServlet {

  IndirizzoDAO indirizzoDAO = new IndirizzoDAOImplement();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    HttpSession session = request.getSession();
    Utente utente = (Utente) session.getAttribute("utente");
    List<Indirizzo> indirizzi = null;

    try {
      indirizzi = indirizzoDAO.getIndirizzo(utente.getCf());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    request.setAttribute("listaIndirizzi", indirizzi);

    RequestDispatcher dispatcher = request.getRequestDispatcher("/FILE_JSP/Pagamento.jsp");
    dispatcher.forward(request, response);

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doPost(request, response);
  }

}
