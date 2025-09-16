package Controller;

import Model.*;
import Utility.CookieManagemnt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Login")
public class Login extends HttpServlet {

    UtenteDAO u = new UtenteDAOImplement();
    AcquistoDAO a = new AcquistoDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        CookieManagemnt cm = new CookieManagemnt(request);
        CarrelloDAO carrelloDAO = new CarrelloDAOImplement();
        String errore = "Nome utente o password non validi";

        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            u.isUtente(username,password);
            if (!u.isUtente(username, password)){
                request.setAttribute("Errore_USER_PASS", errore);
                request.getRequestDispatcher("/FILE_JSP/Log_in.jsp").forward(request, response);
            }else {

                if (cm.checkCookieCart()){

                    List<String> ids = cm.AllCookieId();

                    carrelloDAO.UpdateProductCarello(ids,u.getCF(username));

                    for (String id : ids){

                        int quantita = cm.getCookieProductQuantity(id);
                        Acquisto acquisto = new Acquisto(false,quantita, carrelloDAO.GetIdCarrello(u.getUtente(username).getCf()),Integer.parseInt(id));
                        a.DoSave(acquisto);

                    }

                    cm.RemouveCoockieCart(response);

                }

                session.setAttribute("utente", u.getUtente(username));
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
