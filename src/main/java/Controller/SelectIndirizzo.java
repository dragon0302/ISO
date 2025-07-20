package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/SelectIndirizzo")
public class SelectIndirizzo extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

    HttpSession session = request.getSession();
    Integer Indirizzo = null;

    if (request.getParameter("indirizzoConsegnia") != null) {
      Indirizzo = Integer.parseInt(request.getParameter("indirizzoConsegnia"));
    }

    if (Indirizzo != null) {

      System.out.println("indirizzo");
      session.setAttribute("ID_indirizzo", Indirizzo);

    }

    response.sendRedirect(request.getContextPath() + "/Payment");

  }

}
