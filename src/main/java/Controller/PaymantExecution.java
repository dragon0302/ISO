package Controller;

import Utility.PaymentService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Execute_Payment")
public class PaymantExecution extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    String paymentId = request.getParameter("paymentId");
    String payerId = request.getParameter("PayerID");

    PaymentService paymentService = new PaymentService( getServletContext());
    try {

      Payment complitePayment = paymentService.executePayment(paymentId,payerId);

      if (complitePayment.getState().equals("approved")){
        response.sendRedirect("http://localhost:8080/ISO_16_war_exploded/");
      }else {
        response.sendRedirect("/Carrello");
      }


    } catch (PayPalRESTException | IOException e) {
      throw new RuntimeException(e);
    }

  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response){
    doPost(request, response);
  }

}
