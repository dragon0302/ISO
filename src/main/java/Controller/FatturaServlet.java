package Controller;

import Model.Fatturazione.Fattura;
import Model.Fatturazione.StatoFattura;
import Model.Fatturazione.StatoPagamento;
import Model.Indirizzo;
import Model.Ordine;
import Model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/FatturaServlet")
public class FatturaServlet extends HttpServlet {
    Fattura fattura;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        Ordine ordine = (Ordine) request.getAttribute("ordine");
        StatoFattura sf = StatoFattura.Emessa;
        StatoPagamento sp = StatoPagamento.Pagato;
        Float totale = (Float) request.getAttribute("totale");
        Float totalesenzaiva = (totale * 22)/100;
        String paykey = request.getParameter("transaction_id");
        Indirizzo indirizzo = (Indirizzo) request.getAttribute("indirizzo");
        try {
            fattura = new Fattura(ordine,utente,indirizzo,sf,sp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
