package Servlet;

import DataManagement.*;
import DataManagement.Carrello;
import Utility.CookieManagement;
import Utility.EncodingPassword;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/Sign-up")
public class Signup extends HttpServlet {
    UtenteDAO utenteDAO = new UtenteDAOImplement();
    CarrelloDAO carelloDao = new CarrelloDAOImplement();
    EncodingPassword encod = new EncodingPassword();

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        String erroreCF = "Codice Fiscale errato";
        String erroreNomeUtente = "Nome utente gia esistente";
        String erroreData = "Data non coretta";
        String erroreEmail = "Email non coretta";
        CookieManagement cm = new CookieManagement(request);

        try{
            String CF = request.getParameter("CodiceFiscale");
            String nomeUtenete = request.getParameter("NomeUtente");
            String Password = request.getParameter("Password");
            String Nome = request.getParameter("Nome");
            String Cognome = request.getParameter("Cognome");
            String Email = request.getParameter("E-mail");
            String Sesso = request.getParameter("sesso");
            Date dataNascita = Date.valueOf(request.getParameter("DataNascita"));
            boolean Amministratore = Boolean.parseBoolean(request.getParameter("Amministratore"));

            String salt = encod.genereitSalt();
            String encodedPassword = encod.codePassword(Password,salt);

            if (CF.length() != 16) {
                request.setAttribute("errore", erroreCF);
                request.getRequestDispatcher("/Sign-up.jsp").forward(request, response);
            }else if(utenteDAO.CFEsistente(CF)){
                request.setAttribute("errore", erroreCF);
                request.getRequestDispatcher("/Sign-up.jsp").forward(request, response);
            }else if (utenteDAO.UtenteEsistente(nomeUtenete)) {
                request.setAttribute("errore", erroreNomeUtente);
                request.getRequestDispatcher("/Sign-up.jsp").forward(request, response);
            } else if (dataNascita.compareTo(new Date(1900,1,1)) < 0 && dataNascita.compareTo(Date.valueOf(LocalDate.now())) > 0) {
                request.setAttribute("errore", erroreData);
                request.getRequestDispatcher("/Sign-up.jsp").forward(request, response);
            } else if(utenteDAO.EmailEsistente(Email)) {
                request.setAttribute("errore", erroreEmail);
                request.getRequestDispatcher("/Sign-up.jsp").forward(request, response);
            }else{
                Utente utente = new Utente(CF, nomeUtenete, encodedPassword,salt, Nome, Cognome, Email, Sesso, dataNascita, Amministratore);
                Carrello carrello = new Carrello(CF);
                utenteDAO.DoSave(utente);
                carelloDao.DoSave(carrello);

                if (cm.CookieCartExist(request)){
                    List<String> idProdotti = cm.GetAllID();
                    String ProdottiId = null;
                    String idCarello = null;

                    idCarello = carelloDao.GetIdCarello(CF);

                    for (String idProdotto : idProdotti){
                        ProdottiId += "," + idProdotto;
                    }
                    carelloDao.SetProdottiCarello(ProdottiId,idCarello);
                }

                response.sendRedirect("Home.jsp");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
