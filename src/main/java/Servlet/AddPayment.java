package Servlet;

import jakarta.servlet.http.*;
import DataManagement.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/AddPayment")
public class AddPayment extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MetodoPagamentoDAOImplement mpi = new MetodoPagamentoDAOImplement();
        MetodoPagamento mp = null;
        try{ //controllo che il codicecarta + cvv non esiste già + data scadenza inferiore o uguale a oggi
            HttpSession session = request.getSession();
            Utente utente = (Utente)session.getAttribute("utente");
            String numerocarta = String.valueOf(request.getParameter("NumeroCarta"));
            if (mpi.getNumeroCarta(utente.getCf()) == numerocarta){
                response.sendRedirect("metodo_di_pagamento.jsp");
            }
            Date dataScadenza = Date.valueOf(request.getParameter("DataScadenza"));
            if (dataScadenza.toLocalDate().isAfter(LocalDate.now())){
                response.sendRedirect("metodo_di_pagamento.jsp");
            }
            int cvv =  Integer.parseInt(request.getParameter("CVV"));
            String tipo =  request.getParameter("Tipo");
            boolean defaultPagamento = Boolean.parseBoolean(request.getParameter("Default_Pagamento"));
            mp = new MetodoPagamento(numerocarta,dataScadenza,cvv,tipo,defaultPagamento, utente.getCf());
            mpi.doSave(mp);
            response.sendRedirect("Catalogo");
    }catch(Exception e){
        e.printStackTrace();}
    }
}