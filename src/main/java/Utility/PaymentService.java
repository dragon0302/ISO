package Utility;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private final APIContext apiContext;

    public PaymentService(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    public String AuthorizePayment(Orderdetail orderdetail) throws PayPalRESTException{

        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setTotal(String.valueOf(orderdetail.getTotale()));

        Transaction transaction = new Transaction();
        transaction.setDescription(orderdetail.getNomeProdotto());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        RedirectUrls redirectUrls = new RedirectUrls();
        String cancelURL = "";
        String returnURL = "";
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
}
