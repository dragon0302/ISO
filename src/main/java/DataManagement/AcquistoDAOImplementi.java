package DataManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AcquistoDAOImplementi implements AcquistoDAO {

    private static DataSource ds;
    private static final String TABLE_NAME = "acquisto";

    static {
        try {
            Context inixContext = new InitialContext();
            Context envContext = (Context) inixContext.lookup("java:/comp/anv");

            ds = (DataSource) envContext.lookup("jdbc/ISO16");
        }catch (NamingException e){
            System.out.println("Naming Exeption: " + e.getMessage());
        }
    }

    public synchronized void DoSave(Acquisto acquisto) throws SQLException{
        Connection conn = null;
        PreparedStatement query = null;

        try{
            conn= ds.getConnection();
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " (ID_acquisto, CodiceSconto, Quantita, ID_Carello, ID_Prodotto) VALUES (?,?,?,?,?)");

            query.setInt(1, acquisto.getID_acquisto());
            query.setBoolean(2,acquisto.isCodiceSconto());
            query.setInt(3, acquisto.getQuantita());
            query.setInt(4, acquisto.getID_Carello());
            query.setInt(5, acquisto.getID_Prodotto());

            query.executeUpdate();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query != null) {
                    query.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

}
