package Controller;

import Model.*;
import Model.Fatturazione.Fattura;
import Model.Fatturazione.StatoFattura;
import Model.Fatturazione.StatoPagamento;
import Model.Indirizzo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/FatturaServlet")
public class FatturaServlet extends HttpServlet {
    Fattura fattura;
    OrdineDAO odao = new OrdineDAOImplement();
    IndirizzoDAO idao = new IndirizzoDAOImplement();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        String idordine = request.getParameter("idordine");

        Indirizzo indirizzo = null;
        Ordine ordine = null;
        try {
            ordine = odao.getOrdineByID(Integer.parseInt(idordine));

            int idIndirizzo = odao.getIdIndirizzo(Integer.parseInt(idordine));
            indirizzo = idao.getIndirizzoByID(idIndirizzo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        StatoFattura sf = StatoFattura.Emessa;
        StatoPagamento sp = StatoPagamento.Pagato;
        Float totale = (Float) request.getAttribute("totale");
        OrdineDAO odao = new OrdineDAOImplement();
        try {
            fattura = new Fattura(ordine,utente,indirizzo,sf,sp,odao);
            fattura.CreatePDF(System.getProperty("user.home") + File.separator + "Desktop/Fattura.pdf", request.getServletContext().getRealPath("/Immagini/isologo.png"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("Impostazioni");
    }
}
