package Controller;

import Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CheckUtente")

public class CheckUtente extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Utente utente = (Utente) session.getAttribute("utente");
            if (utente == null) {
                response.sendRedirect("FILE_JSP/Log_in.jsp");
            }else{
                response.sendRedirect(request.getContextPath() + "/Payment");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
