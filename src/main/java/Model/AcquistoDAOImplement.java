package Model;

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

public class AcquistoDAOImplement implements AcquistoDAO {

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
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " ( CodiceSconto, Quantita, ID_Carello, ID_Prodotto) VALUES (?,?,?,?)");

            query.setBoolean(1,acquisto.isCodiceSconto());
            query.setInt(2, acquisto.getQuantita());
            query.setInt(3, acquisto.getID_Carrello());
            query.setInt(4, acquisto.getID_Prodotto());

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

    public ArrayList<Integer> getProdottiPiuAqquistati(int quantita) throws SQLException{

        Connection conn = null;
        PreparedStatement query2 = null;
        ArrayList<Integer> prodottiPiuAqquistati = new ArrayList<>();

        try {
            conn = ds.getConnection();
            query2 = conn.prepareStatement("select distinct ID_prodotto from " + TABLE_NAME + " where Quantita >= ?");

            query2.setInt(1,quantita);

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

    public void UpdateQuantity(int ProdottoID, int IDcarello, int quantita) throws SQLException{

        Connection conn = null;
        PreparedStatement query3 = null;
        PreparedStatement query4 = null;

        try {

            conn = ds.getConnection();
            query3 = conn.prepareStatement("update " + TABLE_NAME + " set Quantita = ? where (ID_Carello = ? and ID_Prodotto = ?)");

            query3.setInt(1, quantita);
            query3.setInt(2, IDcarello);
            query3.setInt(3, ProdottoID);

                query3.executeUpdate();
        }catch (Exception e) {
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
    }

    public Integer GetQuntita(int IDcarello,int ProdottoID) throws SQLException{

        Connection conn = null;
        PreparedStatement query4 = null;

        try {

            conn = ds.getConnection();
            query4 = conn.prepareStatement("SELECT Quantita FROM " + TABLE_NAME + " WHERE ID_Carello = ? and ID_Prodotto = ?");

            query4.setInt(1, IDcarello);
            query4.setInt(2, ProdottoID);

            ResultSet rs = query4.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }catch (Exception e) {
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
        return null;
    }

    public void remuveAcquisto(int idAcquisto) throws SQLException{

        Connection conn = null;
        PreparedStatement query5 = null;

        try {

            conn = ds.getConnection();
            query5 = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE ID_acquisto = ?");

            query5.setInt(1, idAcquisto);

            query5.execute();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query5 != null) {
                    query5.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }

    }

    public Integer getIdAcquisto(int IDprodotto, int Idcarello) throws SQLException{

        Connection conn = null;
        PreparedStatement query6 = null;
        Integer idAcquisto = null;

        try {

            conn = ds.getConnection();
            query6 = conn.prepareStatement("select ID_acquisto from " + TABLE_NAME + " where (ID_prodotto = ? and ID_Carello = ?)");

            query6.setInt(1, IDprodotto);
            query6.setInt(2, Idcarello);

            ResultSet rs = query6.executeQuery();

            if (rs.next()) {
                idAcquisto = rs.getInt(1);
                return idAcquisto;
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query6 != null) {
                    query6.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }

        return idAcquisto;

    }

    public ArrayList<Integer> getAqquistiByUser(int ID_carrello) throws SQLException{

        Connection conn = null;
        PreparedStatement query7 = null;
        ArrayList<Integer> IDaqquisti = new ArrayList<>();

        try{

            conn = ds.getConnection();
            query7 = conn.prepareStatement("select ID_acquisto from " + TABLE_NAME + " where ID_Carello = ?");

            query7.setInt(1, ID_carrello);
            ResultSet rs = query7.executeQuery();

            while (rs.next()) {

                IDaqquisti.add(rs.getInt(1));

            }

            return IDaqquisti;

        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query7 != null) {
                    query7.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }

        return IDaqquisti;

    }

    public List<Integer> getQuantitaProdotti(int IDcarello) throws SQLException {
        Connection conn = null;
        PreparedStatement query8 = null;
        List<Integer> quantitaProdotti = new ArrayList<>();

        try{
            conn = ds.getConnection();
            query8 = conn.prepareStatement("SELECT Quantita FROM " + TABLE_NAME + " WHERE ID_Carello = ?");

            query8.setInt(1, IDcarello);
            ResultSet rs = query8.executeQuery();
            while (rs.next()) {
                int Quantita = rs.getInt(1);
                quantitaProdotti.add(Quantita);
            }
            return  quantitaProdotti;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query8 != null) {
                    query8.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return quantitaProdotti;
    }
}
