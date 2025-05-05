package Servlet;

import Utility.Orderdetail;
import Utility.PaymentService;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/PaymentAutorization")
public class PaymentAutorization extends HttpServlet {

    PaymentService paymentService;

    public void init() throws ServletException {

        APIContext apiContext = (APIContext) getServletContext().getAttribute("paypalApiContext");
        this.paymentService = new PaymentService(apiContext);

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String NomeProdotto = request.getParameter("NomeProdotto");
        String prezzo = request.getParameter("Prezzo");
        String iva = request.getParameter("Iva");
        String totale = request.getParameter("Totale");

        Orderdetail orderdetail = new Orderdetail(NomeProdotto,Integer.parseInt(prezzo),iva,Integer.parseInt(totale));

        try{
            String approvalLink = paymentService.AuthorizePayment(orderdetail);
            response.sendRedirect(approvalLink);
        }catch (PayPalRESTException e){
            e.printStackTrace();
        }

    }
}
