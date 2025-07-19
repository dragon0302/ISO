package Controller;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Indirizzo")
public class Indirizzo extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String errore = "errore";
        String errore_CF = "errore sul CF";
        String errore_CAP = "Errore sul CAP";
        String errore_Prov = "Errore sulla Provincia";


        try{
            String CF_Utente = request.getSession().getAttribute("CF_utente").toString();
            String città = request.getParameter("città");
            String provincia = request.getParameter("Provincia");
            String via = request.getParameter("via");
            String CAP = request.getParameter("CAP");
            String scala = request.getParameter("scala");
            Integer civico = Integer.valueOf(request.getParameter("civico"));
            String indirizzo2 = request.getParameter("Indirizzo2");
            String note = request.getParameter("note");
            boolean fatturazione = (boolean) request.getSession().getAttribute("fatturazione");


            if (CF_Utente.length() != 16) {
                request.setAttribute("erroreCF", errore_CF);
                request.getRequestDispatcher("/Indirizzo.jsp").forward(request, response);
            } else if(errore_CAP.length() != 5) {
                request.setAttribute("erroreCAP", errore_CAP);
                request.getRequestDispatcher("/Indirizzo.jsp").forward(request, response);
            } else if(provincia.length() != 2) {
                request.setAttribute("errore Provincia", errore_Prov);
                request.getRequestDispatcher("/Indirizzo.jsp").forward(request, response);
            }else {
                Model.Indirizzo indirizzo = new Model.Indirizzo(città,provincia,CAP,via,civico,scala,indirizzo2,note,fatturazione,CF_Utente);

                response.sendRedirect("Indirizzo.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        doGet(request,response);
    }
}
