package Servlet;

import DataManagement.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Login")
public class Login extends HttpServlet {

    UtenteDAO u = new UtenteDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String errore = "Nome utente o password non validi";

        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            u.isUtente(username,password);
            if (u.isUtente(username,password) == false){
                request.setAttribute("Errore_USER_PASS", errore);
                request.getRequestDispatcher("/Log_in.jsp").forward(request, response);
            }else {
                response.sendRedirect("Catalogo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
