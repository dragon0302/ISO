package Utility;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.ServletContext;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private final APIContext apiContext;

    public PaymentService(ServletContext servletContext) {
        this.apiContext = (APIContext) servletContext.getAttribute("paypalApiContext");
        if (this.apiContext == null) {
            throw new IllegalStateException("PayPal API Context not initialized");
        }
    }

    public String AuthorizePayment(Orderdetail orderdetail) throws PayPalRESTException{

        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setTotal(String.valueOf(orderdetail.getTotale()));

        Transaction transaction = new Transaction();
        transaction.setDescription(String.valueOf(orderdetail.getNomeProdotto()));
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        RedirectUrls redirectUrls = new RedirectUrls();
        String baseUrl = "http://localhost:8080/ISO_16_war_exploded";
        String cancelURL = baseUrl + "/Catalogo";
        String returnURL = baseUrl + "/Execute_Payment";
        redirectUrls.setCancelUrl(cancelURL);
        redirectUrls.setReturnUrl(returnURL);

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);

        Payment CreatePayment = payment.create(apiContext);

        for (Links link : CreatePayment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                return link.getHref();
            }
        }

        throw new PayPalRESTException("Payment failed");

    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecute);
    }
}
