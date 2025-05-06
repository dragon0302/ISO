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
import java.util.Properties;

@WebListener
public class PaypalInizializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        try {

            Context context = new InitialContext();

            Properties properties = (Properties) context.lookup("java:/comp/env/paypal/APIConfig");

            String clientId = properties.getProperty("clientID");
            String clientSecret = properties.getProperty("clientSicret");
            String mode = properties.getProperty("mode");

            Map<String,String> ppConfig = new HashMap<>();
            ppConfig.put("mode", mode);

            OAuthTokenCredential credential = new OAuthTokenCredential(
                    clientId,
                    clientSecret,
                    ppConfig
            );

            APIContext apiContext = new APIContext(credential.getAccessToken());

            sce.getServletContext().setAttribute("paypalApiContext", apiContext);

        }catch (NamingException | PayPalRESTException e) {
            throw new RuntimeException("Configurazione PayPal fallita", e);
        }

    }

}
