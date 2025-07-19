package Controller;

import Model.Prodotto;
import Utility.Orderdetail;
import Utility.PaymentService;
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
        List<String> nomiProdotti = new ArrayList<>();
        List<Integer> ivaProdotti = new ArrayList<>();
        List<Float> prezzoProdotti = new ArrayList<>();

        for (Prodotto prodotto : carello) {

            nomiProdotti.add(prodotto.getNome());
            prezzoProdotti.add(prodotto.getPrezzo());
            ivaProdotti.add(prodotto.getIva());

        }

        Orderdetail orderdetail = new Orderdetail(nomiProdotti,prezzoProdotti,ivaProdotti,prezzotatale);

        try{
            String approvalLink = paymentService.AuthorizePayment(orderdetail);
            response.sendRedirect(approvalLink);
        }catch (PayPalRESTException e){
            e.printStackTrace();
        }

    }
}
