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
import java.time.LocalDate;

@WebServlet("/Sign-up")
public class Signup extends HttpServlet {
    UtenteDAO utenteDAO = new UtenteDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        String action = request.getParameter("action");
        String erroreCF = "Codice Fiscale errato";
        String erroreNomeUtente = "Nome utente gia esistente";
        String erroreData = "Data non coretta";

        try{
            if(action != null) {
                String CF = request.getParameter("CodiceFiscale");
                String nomeUtenete = request.getParameter("NomeUtente");
                String Password = request.getParameter("Password");
                String Nome = request.getParameter("Nome");
                String Cognome = request.getParameter("Cognome");
                String Sesso = request.getParameter("sesso");
                Date dataNascita = Date.valueOf(request.getParameter("DataNascita"));
                boolean Amministratore = Boolean.parseBoolean(request.getParameter("Amministratore"));

                if (CF.length() != 16) {
                    request.setAttribute("errore", erroreCF);
                    request.getRequestDispatcher("/Signup.jsp").forward(request, response);
                }else if(utenteDAO.CFEsistente(CF)){
                    request.setAttribute("errore", erroreCF);
                    request.getRequestDispatcher("/Signup.jsp").forward(request, response);
                }else if (utenteDAO.UtenteEsistente(nomeUtenete)) {
                    request.setAttribute("errore", erroreNomeUtente);
                    request.getRequestDispatcher("/Signup.jsp").forward(request, response);
                } else if (dataNascita.compareTo(new Date(1900,1,1)) < 0 && dataNascita.compareTo(Date.valueOf(LocalDate.now())) > 0) {
                    request.setAttribute("errore", erroreData);
                    request.getRequestDispatcher("/Signup.jsp").forward(request, response);
                } else {
                    Utente utente = new Utente(CF, nomeUtenete, Password, Nome, Cognome, Sesso, dataNascita, Amministratore);
                    utenteDAO.DoSave(utente);
                    response.sendRedirect("Home.jsp");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
