package DataManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarrelloDAOImplement implements CarrelloDAO {

    private static DataSource ds;
    private static final String TABLE_NAME = "carrello";

    static {
        try {
            Context inixCtx = new InitialContext();
            Context envCtx = (Context) inixCtx.lookup("java:/comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ISO16");
        } catch (NamingException e) {
            System.out.println("Naming Exception: " + e.getMessage());
        }
    }

    public synchronized void DoSave(Carrello carrello) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {
            conn = ds.getConnection();
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME +  " ( CF_utente) VALUES (?)");

            query.setString(1,carrello.getCf_utente());

            query.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query != null) {
                    query.close();
                }
            }finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

    public void ProdottiCarello(int idProdotto,int idCarrello) throws SQLException{

        Connection conn = null;
        PreparedStatement query2 = null;

        try {
            conn = ds.getConnection();
            query2 = conn.prepareStatement("update " + TABLE_NAME + " set Lista_prodotti = concat_ws(',',Lista_prodotti ,?) where ID_carrello = ?");

            query2.setString(1,String.valueOf(idProdotto));
            query2.setInt(2,idCarrello);

            query2.executeUpdate();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query2 != null) {
                    query2.close();
                }
            }finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }

    }

}
