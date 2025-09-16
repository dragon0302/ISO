package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import Model.ProdottoDAOImplement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebListener
public class ImagePathInitializer implements ServletContextListener {

  ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

  public void contextInitialized(ServletContextEvent sce) {

    //List<String> paths = new ArrayList<>();
    Map<String, String> imageMap = new HashMap<>();

    try {

      List<Prodotto> prodotti = prodottoDAO.getProdotti();
      ServletContext ctx = sce.getServletContext();

      for (Prodotto prodotto : prodotti) {

        String nomeFile = prodotto.getNome() + ".jpg";
        Path percorsoImmagine = Paths.get(ctx.getRealPath("/Immagini"), nomeFile);

        if (Files.exists(percorsoImmagine)) {

          imageMap.put(prodotto.getNome(), "/Immagini/" + prodotto.getNome() + ".jpg");
          //paths.add("/Immagini/" + prodotto.getNome() + ".jpg");

        } else {

          imageMap.put(prodotto.getNome(), "/Immagini/" + "ironmaden.png");
          //paths.add("/Immagini/" + "ironmaden.png");

        }

      }

      ctx.setAttribute("Paths", imageMap);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

}
