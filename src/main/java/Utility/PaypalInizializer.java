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

            Map<String,String> ppConfig = new HashMap<>();
            ppConfig.put("mode", getJndiParameter("paypalMode"));

            ppConfig.putAll(getDefaultPaypalConfig());

            OAuthTokenCredential credential = new OAuthTokenCredential(
                    getJndiParameter("paypalClientID"),
                    getJndiParameter("paypalClientSecret"),
                    ppConfig
            );

            APIContext apiContext = new APIContext(credential.getAccessToken());
            apiContext.setConfigurationMap(ppConfig);

            sce.getServletContext().setAttribute("paypalApiContext", apiContext);

        } catch (Exception e) {
        throw new RuntimeException("PayPal initialization failed", e);
        }
    }

    private String getJndiParameter(String name) throws NamingException{

        Context ctx = new InitialContext();
        Context envCtx = (Context) ctx.lookup("java:/comp/env");
        String value = (String) envCtx.lookup(name);

        if (value == null || value.trim().isEmpty()) {
            throw new NamingException("JNDI parameter '" + name + "' not found or empty");
        }
        return value;

    }

    private Map<String, String> getDefaultPaypalConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("http.ConnectionTimeOut", "5000");
        config.put("http.Retry", "2");
        config.put("http.ReadTimeOut", "30000");
        config.put("http.MaxConnection", "100");
        config.put("http.ProxyHost", "");
        config.put("http.ProxyPort", "");
        config.put("http.ProxyUserName", "");
        config.put("http.ProxyPassword", "");
        config.put("http.GoogleAppEngine", "false");
        return config;
    }
}