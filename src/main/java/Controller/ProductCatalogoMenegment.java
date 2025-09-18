package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import Model.ProdottoDAOImplement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@WebServlet("/ProductCatalogoMenegment")
public class ProductCatalogoMenegment extends HttpServlet {

  ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String action = request.getParameter("action");
    ServletContextEvent sce = new ServletContextEvent(this.getServletContext());
    ServletContext ctx = sce.getServletContext();

        try {

          if(action.equals("modifica")) {

            int prodottoID = Integer.parseInt(request.getParameter("id_prodotto"));
            String nome = request.getParameter("nome");
            float prezzo = Float.parseFloat(request.getParameter("prezzo"));
            String descrizzione = request.getParameter("descrizione");

            Prodotto prodotto = prodottoDAO.getProdottoByID(prodottoID);
            prodottoDAO.editNomeProdotto(prodotto, nome);
            prodottoDAO.editPrezzo(prodotto, prezzo);
            prodottoDAO.editDescrizione(prodotto, descrizzione);

          } else if (action.equals("nuovo")) {

            String nome = request.getParameter("productName");
            float prezzo = Float.parseFloat(request.getParameter("price"));
            String descrizzione = request.getParameter("productDescrizione");
            String categoria = request.getParameter("filtro");
            String taglia = request.getParameter("taglia");
            int iva = Integer.parseInt(request.getParameter("iva"));
            Part filePart = request.getPart("immagine");

//            System.out.println(fileName);
//            System.out.println(ctx.getRealPath("/Immagini"));

            String uploadPath = ctx.getRealPath("/Immagini");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            String filePath = "/Immagini/" + nome + ".jpg";
            filePart.write(String.valueOf(Paths.get(ctx.getRealPath("/Immagini"), nome + ".jpg")));

            Map<String, String> imageMap = (Map<String, String>) ctx.getAttribute("Paths");

            imageMap.put(nome, filePath);

            ctx.setAttribute("Paths", imageMap);

            Prodotto prodotto = new Prodotto(nome,taglia,descrizzione,categoria,prezzo,iva);

            prodottoDAO.doSave(prodotto);

          }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    /*try {
      prodottoDAO.deleteProdotto(prodottoID);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }*/

    response.sendRedirect("Catalogo");

  }

}
