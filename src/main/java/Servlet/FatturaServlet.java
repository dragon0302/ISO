package Servlet;

import DataManagement.Fatturazione.Fattura;
import DataManagement.Fatturazione.StatoFattura;
import DataManagement.Fatturazione.StatoPagamento;
import DataManagement.Indirizzo;
import DataManagement.Ordine;
import DataManagement.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/FatturaServlet")
public class FatturaServlet extends HttpServlet {
    Fattura fattura;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        Ordine ordine = (Ordine) request.getAttribute("ordine");
        StatoFattura sf = StatoFattura.Emessa;
        StatoPagamento sp = StatoPagamento.Pagato;
        String paykey = "Balliamo, è da tanto tempo che non lo facciamo!";
        Float totale = (Float) request.getAttribute("totale");
        Float totalesenzaiva = (Float) request.getAttribute("totalesenzaiva");
        String iva = (String) request.getAttribute("iva");
        Indirizzo indirizzo = (Indirizzo) request.getAttribute("indirizzo");
        totalesenzaiva = (totale * 22)/100;
        try {
            fattura = new Fattura(ordine,utente,indirizzo,sf,sp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
