package Servlet;

import DataManagement.Prodotto;
import Utility.Orderdetail;
import Utility.PaymentService;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PaymentAutorization")
public class PaymentAutorization extends HttpServlet {

    PaymentService paymentService;
    HttpSession session;

    public void init() throws ServletException {

        //APIContext apiContext = (APIContext) getServletContext().getAttribute("paypalApiContext");
        this.paymentService = new PaymentService(getServletContext());

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        List<Prodotto> carello = (List<Prodotto>) session.getAttribute("carrello");
        float prezzotatale = (float) session.getAttribute("prezzotatale");

        String NomeProdotto = carello.get(0).getNome();
        String prezzo = String.valueOf(carello.get(0).getPrezzo());
        String iva = String.valueOf(carello.get(0).getIva());
        String totale = String.valueOf(prezzotatale);

        Orderdetail orderdetail = new Orderdetail(NomeProdotto,Float.parseFloat(prezzo),iva,Float.parseFloat(totale));

        try{
            String approvalLink = paymentService.AuthorizePayment(orderdetail);
            response.sendRedirect(approvalLink);
        }catch (PayPalRESTException e){
            e.printStackTrace();
        }

    }
}
