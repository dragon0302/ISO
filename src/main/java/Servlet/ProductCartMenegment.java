package Servlet;

import Utility.CookieManagemnt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/ProductCartMenegment")
public class ProductCartMenegment extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String prodottoID = request.getParameter("prodottoID");
            String sourcePage = request.getParameter("SourcePage");
            CookieManagemnt cm = new CookieManagemnt(request);
            String redirectPage = null;
            Boolean contenuto = true;

            if(prodottoID != null) {
                cm.CookieCartManagemnt(response, prodottoID);
            }



            if("Prodotto".equals(sourcePage)) {
                redirectPage = "ProdottoS?id=" + prodottoID;
            }else if("Home".equals(sourcePage)) {
                redirectPage = "Catalogo";
            }

            response.sendRedirect(redirectPage);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
