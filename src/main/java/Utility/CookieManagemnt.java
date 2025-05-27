package Utility;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class CookieManagemnt {

    Cookie cookieCart = null;
    String CartValue;
    Cookie[] cookies;

    public CookieManagemnt(HttpServletRequest request) {

        cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("Cart")) {
                    cookieCart = c;
                    CartValue = c.getValue();
                    break;
                }
            }
        }
    }

    public void CookieCartManagemnt(HttpServletResponse response, String prodottoID) {



        int quantita = 0;
        if(cookieCart == null) {
            quantita = 1;
            CartValue = ("(I" + prodottoID + "!" + quantita + ")").trim();
            cookieCart = new Cookie("Cart", CartValue);
            cookieCart.setMaxAge(30 * 24 * 60 * 60);
            cookieCart.setPath("/ISO_16_war_exploded");
            response.addCookie(cookieCart);
        }else {
            CartValue = cookieCart.getValue();
            if(!CartValue.contains("I" + prodottoID)) {
                quantita = 1;
                CartValue += ("-" + "(I" + prodottoID + "!" + quantita + ")").trim();
                cookieCart.setValue(CartValue);
                response.addCookie(cookieCart);
            } else {
                String[] Cprodotti = CartValue.split("-");
                StringBuilder newCartValue = new StringBuilder();

                for (String item : Cprodotti) {
                    if (item.contains("I" + prodottoID)) {
                        // Estrai e incrementa quantità
                        quantita = Integer.parseInt(item.split("!")[1].split("\\)")[0]);
                        quantita++;
                        // Ricostruisci l'item
                        item =  "(I" + prodottoID + "!" + quantita + ")";
                    }
                    if(!newCartValue.isEmpty()) {
                        newCartValue.append("-");
                    }
                    newCartValue.append(item);
                }

                // Aggiorna il cookie
                cookieCart.setValue(newCartValue.toString());
                response.addCookie(cookieCart);
            }
        }
    }

    public int getCookieProductQuantity(String prodottoID) {


        int quantita = 0;
        if(CartValue != null) {
            String[] Cprodotti = CartValue.split("-");

            for (String item : Cprodotti) {
                if (item.contains("I" + prodottoID)) {
                    // Estrai la quantità
                    quantita = Integer.parseInt(item.split("!")[1].split("\\)")[0]);
                }
            }
            return quantita;
        }
        return 0;
    }

    public void updateCookieProductQuantity(HttpServletResponse response,String prodottoID,int operazione) {

        StringBuilder newCartValue = new StringBuilder();

        int quantita = 0;

        if(CartValue != null) {
            String[] Cprodotti = CartValue.split("-");

            for (int i = 0; i < Cprodotti.length; i++) {
                String item = Cprodotti[i];
                if (item.contains("I" + prodottoID)) {
                    // Estrai e incrementa quantità
                    quantita = Integer.parseInt(item.split("!")[1].split("\\)")[0]);
                    if (operazione == 0) {
                        quantita++;
                    } else if (operazione == 1) {
                        System.out.println("nel if " + quantita);
                        quantita--;
                        System.out.println("nel if 2 " + quantita);
                        System.out.println("prova");
                    }else {
                        System.out.println("erroere operazzione errata");
                    }
                    System.out.println("fuori dall if " + quantita);
                    // Ricostruisci l'item
                    item = "(I" + prodottoID + "!" + quantita + ")";
                }
                newCartValue.append(item);

                if (i < Cprodotti.length - 1) {
                    newCartValue.append("-");
                }
            }
            cookieCart.setValue(newCartValue.toString());
            response.addCookie(cookieCart);
        }

    }

    public List<String> AllCookieId(){

        if(cookieCart != null) {
            String[] Cprodotti = CartValue.split("-");
            List<String> ids = new ArrayList<>();

            for (String item : Cprodotti) {
                ids.add(item.split("!")[0].split("I")[1]);
            }
            return ids;
        }
        return null;
    }

    public boolean checkCookieCart() {

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("Cart")) {
                    return true;
                }
            }
        }
        return false;
    }

}
