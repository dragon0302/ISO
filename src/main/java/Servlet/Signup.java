package Servlet;

import DataManagement.*;
import DataManagement.Carrello;
import Utility.CookieManagemnt;
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
import java.util.regex.*;

@WebServlet("/Sign-up")
public class Signup extends HttpServlet {
    UtenteDAO utenteDAO = new UtenteDAOImplement();
    CarrelloDAO carrelloDAO = new CarrelloDAOImplement();
    EncodingPassword encod = new EncodingPassword();

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        CookieManagemnt cm = new CookieManagemnt(request);
        String erroreCF = "Codice Fiscale gia presente nel sito";
        String erroreNomeUtente = "Nome utente gia esistente";
        String erroreData = "Data non coretta";
        String erroreEmail = "Email non coretta";
        String erroreNomeRegex = "Il campo Nome contiene caratteri non ammessi ";
        String erroreCognomeRegex = "Il campo Cognome contiene caratteri non ammessi ";
        String erroreEmailRegex = "Il campo Email contiene caratteri non ammessi ";
        String erroreNomeUtenteRegex = "Il campo Nome Utente contiene caratteri non ammessi ";
        String errorePasswordRegex = "Il campo Password contiene caratteri non ammessi ";

        // Regex
        Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9_]{1,10}$");
        Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]).{8,}$");
        Pattern NOME_REGEX = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ' ]+$");
        Pattern COGNOME_REGEX = Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ' ]+$");
        Pattern CODICEFISCALE_REGEX = Pattern.compile("^([A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1})$|([0-9]{11})$");


        try{
            String CF = request.getParameter("CodiceFiscale");
            String nomeUtente = request.getParameter("NomeUtente");
            String Password = request.getParameter("Password");
            String Nome = request.getParameter("Nome");
            String Cognome = request.getParameter("Cognome");
            String Email = request.getParameter("E-mail");
            String Sesso = request.getParameter("sesso");
            Date dataNascita = Date.valueOf(request.getParameter("DataNascita"));
            boolean Amministratore = Boolean.parseBoolean(request.getParameter("Amministratore"));

            String salt = encod.genereitSalt();
            String encodedPassword = encod.codePassword(Password,salt);

            if (!CODICEFISCALE_REGEX.matcher(CF).matches()) {
                request.setAttribute("erroreCF", erroreCF);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            }else if (!NOME_REGEX.matcher(Nome).matches()) {
                request.setAttribute("erroreNomeRegex", erroreNomeRegex);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            }else if (!COGNOME_REGEX.matcher(Cognome).matches()) {
                request.setAttribute("erroreCognomeRegex", erroreCognomeRegex);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            }else if (!EMAIL_REGEX.matcher(Email).matches()) {
                request.setAttribute("erroreEmailRegex", erroreEmailRegex);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            }else if (!USERNAME_REGEX.matcher(nomeUtente).matches()) {
                request.setAttribute("erroreNomeUtenteRegex", erroreNomeUtenteRegex);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            }else if (!PASSWORD_REGEX.matcher(Password).matches()) {
                System.out.println(Password);
                request.setAttribute("errorePasswordRegex", errorePasswordRegex);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            }else if(utenteDAO.CFEsistente(CF)){
                request.setAttribute("erroreCF", erroreCF);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            }else if (utenteDAO.UtenteEsistente(nomeUtente)) {
                request.setAttribute("erroreNomeUtente", erroreNomeUtente);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            } else if (dataNascita.compareTo(new Date(1900,1,1)) < 0 && dataNascita.compareTo(Date.valueOf(LocalDate.now())) > 0) {
                request.setAttribute("erroreData", erroreData);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            } else if(utenteDAO.EmailEsistente(Email)) {
                request.setAttribute("erroreEmail", erroreEmail);
                request.getRequestDispatcher("/FILE_JSP/Sign-up.jsp").forward(request, response);
            } else{
                Utente utente = new Utente(CF, nomeUtente, encodedPassword,salt, Nome, Cognome, Email, Sesso, dataNascita, Amministratore);
                Carrello carrello = new Carrello(CF);
                utenteDAO.DoSave(utente);
                carrelloDAO.DoSave(carrello);
                AcquistoDAO acquistoDAO = new AcquistoDAOImplement();

                if (cm.checkCookieCart()){

                    List<String> ids = cm.AllCookieId();

                    for (String id : ids) {
                        Acquisto acquisto = new Acquisto(false,cm.getCookieProductQuantity(id), carrelloDAO.GetIdCarrello(CF),Integer.parseInt(id));
                        acquistoDAO.DoSave(acquisto);
                    }

                    carrelloDAO.UpdateProductCarello(ids,CF);

                }

                response.sendRedirect("Catalogo");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
