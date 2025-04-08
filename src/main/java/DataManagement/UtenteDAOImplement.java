package DataManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        CarelloDAO carelloDAO = new CarelloDAOImplement();
        Carrello carrello = null;

        try {
            conn = ds.getConnection();
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
            carrello = new Carrello(utente.getCf());
            carelloDAO.DoSave(carrello);
        } catch (Exception e) {
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

    public synchronized boolean CFEsistente(String CF) throws SQLException {
        Connection conn = null;
        PreparedStatement query2 = null;
        boolean esistente = false;

        try {
            conn = ds.getConnection();
            query2 = conn.prepareStatement("SELECT CF FROM " + TABLE_NAME + " WHERE CF = ?");

            query2.setString(1, CF);

            ResultSet rs = query2.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
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
        return esistente;
    }

    public synchronized boolean UtenteEsistente(String nomeUtente) throws SQLException {
        Connection conn = null;
        PreparedStatement query3 = null;
        boolean esistente = false;

        try {
            conn = ds.getConnection();
            query3 = conn.prepareStatement("SELECT NomeUtente FROM " + TABLE_NAME + " WHERE NomeUtente = ?");

            query3.setString(1, nomeUtente);

            ResultSet rs = query3.executeQuery();

            if (rs.next()) {
                esistente = true;
            }else {
                esistente = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query3 != null) {
                    query3.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return esistente;
    }

    public synchronized boolean isAmministratore(String CF) throws SQLException{
        Connection conn = null;
        PreparedStatement query4 = null;
        boolean esistente = false;

        try {
            conn = ds.getConnection();
            query4 = conn.prepareStatement("SELECT Amministratore FROM " + TABLE_NAME + " WHERE CF = ?");

            query4.setString(1, CF);
            ResultSet rs = query4.executeQuery();

            if (rs.next()) {
                esistente = true;
            }else {
                esistente = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query4 != null) {
                    query4.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return esistente;
    }
    public boolean isUtente(String nomeutente, String password) throws SQLException{
        Connection conn = ds.getConnection();
        PreparedStatement ql = conn.prepareStatement("SELECT nomeUtente, password FROM " + TABLE_NAME + " WHERE NomeUtente = ? AND Password = ?");
        ql.setString(1,nomeutente);
        ql.setString(2,password);
        ResultSet rs = ql.executeQuery();
        if (rs.next()) {
            return true;
        }else return false;
    }
}
