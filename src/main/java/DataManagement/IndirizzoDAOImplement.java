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
import java.util.List;

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
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME +  "(CF_Utente, città, Provincia, CAP, Via, Civico, Indirizzo2, Note, Fatturazione) VALUES (?,?,?,?,?,?,?,?,?)");
            query.setString(1,indirizzo.getCF_utente());
            query.setString(2,indirizzo.getCittà());
            query.setString(3,indirizzo.getProvincia());
            query.setString(4,indirizzo.getCap());
            query.setString(5,indirizzo.getVia());
            query.setInt(6,indirizzo.getCivico());
            query.setString(7, indirizzo.getIndirizzo2());
            query.setString(8,indirizzo.getNote());
            query.setBoolean(9,indirizzo.isFatturazione());
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

    public List<Indirizzo> getIndirizzo(String CF) throws SQLException{

        Connection conn = null;
        PreparedStatement query = null;
        ResultSet resultSet = null;
        List<Indirizzo> indirizzi = new ArrayList<Indirizzo>();

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " where CF_utente = ?");

            query.setString(1,CF);
            resultSet = query.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String citta = resultSet.getString(2);
                String provincia = resultSet.getString(3);
                String cap = resultSet.getString(4);
                String via = resultSet.getString(5);
                int civico = resultSet.getInt(6);
                String scala = resultSet.getString(7);
                String indirizzo2 = resultSet.getString(8);
                String note = resultSet.getString(9);
                boolean fatturazione = resultSet.getBoolean(10);
                String CF_Utente = resultSet.getString(11);

                Indirizzo indirizzo = new Indirizzo(citta,provincia,cap,via,civico,scala,indirizzo2,note,fatturazione,CF_Utente);
                indirizzo.setID_Indirizzo(id);
                indirizzi.add(indirizzo);

            }

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

        return indirizzi;

    }

    public void setDefaultIndirizzo(int indirizzoID, String CF) throws SQLException{

        Connection conn = null;
        PreparedStatement query = null;
        PreparedStatement query2 = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("update " + TABLE_NAME + " set Fatturazione = ? where ID_indirizzo = ?");
            query2 = conn.prepareStatement("select ID_indirizzo from " + TABLE_NAME + " where Fatturazione = 1 and CF_utente = ?");

            query2.setString(1,CF);

            ResultSet rs = query2.executeQuery();

            rs.next();
            int id = rs.getInt(1);

            query.setInt(1,0);
            query.setInt(2,id);
            query.execute();

            query.setInt(1,1);
            query.setInt(2,indirizzoID);

            query.execute();

        }finally {
            try {
                if (query != null) {
                    query.close();
                }
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
