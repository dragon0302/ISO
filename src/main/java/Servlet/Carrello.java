package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Carrello")
public class Carrello extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String prodottoID = request.getParameter("prodottoID");
            Cookie[] cookies = request.getCookies();
            Cookie cookieCart = null;
            String CartValue = null;

            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("Cart")) {
                        cookieCart = c;
                        CartValue = c.getValue();
                        break;
                    }
                }
            }

            if(cookieCart == null) {
                CartValue = prodottoID;
                cookieCart = new Cookie("Cart", CartValue);
                cookieCart.setMaxAge(30 * 24 * 60 * 60);
                cookieCart.setPath("/");
                response.addCookie(cookieCart);
            }else {
                CartValue = cookieCart.getValue();
                if(!CartValue.contains(prodottoID)) {
                    CartValue += "|" + prodottoID;
                    cookieCart.setValue(CartValue);
                    response.addCookie(cookieCart);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
