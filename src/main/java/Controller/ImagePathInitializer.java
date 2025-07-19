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
import java.util.List;

@WebListener
public class ImagePathInitializer implements ServletContextListener {

  ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

  public void contextInitialized(ServletContextEvent sce) {

    List<String> paths = new ArrayList<>();

    try {

      List<Prodotto> prodotti = prodottoDAO.getProdotti();
      ServletContext ctx = sce.getServletContext();

      for (Prodotto prodotto : prodotti) {

        String nomeFile = prodotto.getNome() + ".jpg";
        Path percorsoImmagine = Paths.get(ctx.getRealPath("/Immagini"), nomeFile);

        if (Files.exists(percorsoImmagine)) {

          paths.add("/Immagini/" + prodotto.getNome() + ".jpg");

        } else {

          paths.add("/Immagini/" + "ironmaden.png");

        }

      }

      ctx.setAttribute("Paths", paths);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

}
