package Controller;

import Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowOrder extends HttpServlet {

    OrdineDAO ordineDAO = new OrdineDAOImplement();
    CarrelloDAO carrelloDAO = new CarrelloDAOImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String CFutente = request.getParameter("CFutente");

            int IDcarello = carrelloDAO.GetIdCarrello(CFutente);

            List<Ordine> ordini = ordineDAO.getOrdersByUser(IDcarello);

            request.setAttribute("ordini", ordini);

            response.sendRedirect("/FILE_JSP/Impostazioni.jsp");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
