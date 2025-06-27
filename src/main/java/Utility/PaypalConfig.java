package Utility;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PaypalConfig {

    private String paypal_client_id;
    private String paypal_client_secret;
    private String mode;

    public PaypalConfig() {
        try {
            Context ctx = new InitialContext();
            Context envCtx = (Context) ctx.lookup("java:/comp/env");
            this.paypal_client_id = (String) envCtx.lookup("paypalClientID");
            this.paypal_client_secret = (String) envCtx.lookup("paypalClientSecret");
            this.mode = (String) envCtx.lookup("paypalMode");
        } catch (NamingException e) {
            throw new RuntimeException("Failed to load PayPal config", e);
        }
    }

    public String getClientId() { return paypal_client_id; }
    public String getClientSecret() { return paypal_client_secret; }
    public String getMode() { return mode; }

}
