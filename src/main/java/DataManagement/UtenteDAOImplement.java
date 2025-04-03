package DataManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtenteDAOImplement implements UtenteDAO {

    private static DataSource ds;
    private static final String TABLE_NAME = "Utente";

    static {
        try {
            Context inixCtx = new InitialContext();
            Context envCtx = (Context) inixCtx.lookup("java:/comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ISO16");
        } catch (NamingException e) {
            System.out.println("Naming Exception: " + e.getMessage());
        }
    }

    public synchronized void DoSave(Utente utente) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {
            conn = ds.getConnection();
            System.out.println("prova");
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " (CF, NomeUtente, Password, Nome, Cognome, Sesso, DataNascita, Amministratore) VALUES (?,?,?,?,?,?,?,?);");

            query.setString(1, utente.getCf());
            query.setString(2, utente.getNomeutente());
            query.setString(3, utente.getPassword());
            query.setString(4, utente.getNome());
            query.setString(5, utente.getCognome());
            query.setString(6, utente.getSesso());
            query.setDate(7, utente.getDataNascita());
            query.setBoolean(8, utente.isAmministratore());

            query.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
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

}
