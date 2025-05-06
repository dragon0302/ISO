package Utility;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class CookieManagement {


  int quantita = 0;
  String CartValue;
  Cookie cookieCart = null;

  public CookieManagement(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

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

  public int CookieCartManagement(String prodottoID, HttpServletResponse response, HttpServletRequest request) {

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
            quantita = GetQuantitaByID(prodottoID);
            quantita++;
            // Ricostruisci l'item
            item = "(I" + prodottoID + "!" + quantita + ")";
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

    return quantita;

  }

  public int GetQuantitaByID(String prodottoID) {

    String[] Cprodotti = CartValue.split("-");

    for (String item : Cprodotti){
      if (item.contains("I" + prodottoID)){
        quantita = Integer.parseInt(item.split("!")[1].replace(")", ""));
      }
    }
    return quantita;
  }

  public List<String> GetAllID(){
    List<String> idList = new ArrayList<String>();
    String[] Cprodotti = CartValue.split("-");

    for (String item : Cprodotti){
      idList.add(item.split("I")[1].split("!")[0]);
    }
    return idList;
  }

  public boolean CookieCartExist(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

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
