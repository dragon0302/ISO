package Servlet;

import DataManagement.Utente;
import DataManagement.UtenteDAO;
import DataManagement.UtenteDAOImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/Utente")
public class NewUtente extends HttpServlet {
    UtenteDAO utenteDAO = new UtenteDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        String action = request.getParameter("action");

        try{
            System.out.println(action);
            if(action != null) {
                String CF = request.getParameter("CodiceFiscale");
                String nomeUtenete = request.getParameter("NomeUtente");
                String Password = request.getParameter("Password");
                String Nome = request.getParameter("Nome");
                String Cognome = request.getParameter("Cognome");
                String Sesso = request.getParameter("sesso");
                java.sql.Date dataNascimento = Date.valueOf(request.getParameter("DataNascita"));
                boolean Amministratore = Boolean.parseBoolean(request.getParameter("Amministratore"));

                Utente utente = new Utente(CF, nomeUtenete, Password, Nome, Cognome, Sesso, dataNascimento, Amministratore);
                utenteDAO.DoSave(utente);
                response.sendRedirect("index.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
