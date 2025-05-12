package Utility;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class PaypalInizializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:/comp/env");

            // Recupera i valori separatamente
            String clientId = (String) envCtx.lookup("paypalClientID");
            String clientSecret = (String) envCtx.lookup("paypalClientSecret");
            String mode = (String) envCtx.lookup("paypalMode");

            // Verifica che tutti i parametri siano presenti
            if (clientId == null || clientSecret == null || mode == null) {
                throw new RuntimeException("Configurazione PayPal incompleta");
            }

            Map<String,String> ppConfig = new HashMap<>();
            ppConfig.put("mode", mode);

            OAuthTokenCredential credential = new OAuthTokenCredential(
                    clientId,
                    clientSecret,
                    ppConfig
            );

            APIContext apiContext = new APIContext(credential.getAccessToken());
            sce.getServletContext().setAttribute("paypalApiContext", apiContext);

        } catch (NamingException | PayPalRESTException e) {
            throw new RuntimeException("Configurazione PayPal fallita", e);
        }
    }
}