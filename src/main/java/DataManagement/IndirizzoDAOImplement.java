package DataManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IndirizzoDAOImplement implements IndirizzoDAO {
    private static DataSource ds;
    private static final String TABLE_NAME = "indirizzo";

    static {
        try {
            Context inixCtx = new InitialContext();
            Context envCtx = (Context) inixCtx.lookup("java:/comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ISO16");
        } catch (NamingException e) {
            System.out.println("Naming Exception: " + e.getMessage());
        }
    }
    public synchronized void doSave(Indirizzo indirizzo) throws SQLException{
        Connection conn = null;
        PreparedStatement query = null;
        try {
            conn = ds.getConnection();
            query = conn.prepareStatement("INSERT INTO" + TABLE_NAME +  "(CF_Utente, città, Provincia, CAP, Via, Civico, Indirizzo2, Note, Fatturazione) VALUES (?,?,?,?,?,?,?,?,?)");
            query.setString(1,indirizzo.getCF_utente());
            query.setString(2,indirizzo.getCittà());
            query.setString(3,indirizzo.getProvincia());
            query.setString(4,indirizzo.getCap());
            query.setString(5,indirizzo.getVia());
            query.setString(6, indirizzo.getIndirizzo2());
            query.setString(7,indirizzo.getNote());
            query.setBoolean(8,indirizzo.isFatturazione());
            query.executeUpdate();
        }finally {
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
