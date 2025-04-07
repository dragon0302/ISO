package DataManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcquistoDAOImplementi implements AcquistoDAO {

    private static DataSource ds;
    private static final String TABLE_NAME = "acquisto";

    static {
        try {
            Context inixContext = new InitialContext();
            Context envContext = (Context) inixContext.lookup("java:/comp/env");

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

    public ArrayList<Integer> getProdottiPiuAqquistati() throws SQLException{

        Connection conn = null;
        PreparedStatement query2 = null;
        ArrayList<Integer> prodottiPiuAqquistati = new ArrayList<>();

        try {
            conn = ds.getConnection();
            query2 = conn.prepareStatement("select distinct ID_prodotto from " + TABLE_NAME + " where Quantità >= 10");

            ResultSet rs = query2.executeQuery();

            while (rs.next()) {
                prodottiPiuAqquistati.add(rs.getInt(1));
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query2 != null) {
                    query2.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return prodottiPiuAqquistati;
    }

}
