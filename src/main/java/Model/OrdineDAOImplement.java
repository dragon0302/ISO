package Model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class OrdineDAOImplement implements OrdineDAO {
    private static DataSource ds;
    private static final String TABLE_NAME = "ordine";

    static {
        try {
            Context inixContext = new InitialContext();
            Context envContext = (Context) inixContext.lookup("java:comp/env");
            ds = (DataSource) envContext.lookup("jdbc/ISO16");
        } catch (NamingException e) {
            System.out.println("Naming Exeption: " + e.getMessage());
        }
    }

    public synchronized void DoSave(Ordine ordine) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        try {
            conn = ds.getConnection();
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(Data_Ordine,Prezzo_tot,Lista_prodotti,ID_carrello)" + " VALUES (?,?,?,?)");
            query.setDate(1, ordine.getData_ordine());
            query.setFloat(2, ordine.getTotale());
            query.setString(3, ordine.getProdotti());
            query.setInt(4, ordine.getID_carrello());
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println("Naming Exeption: " + e.getMessage());
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
    public ArrayList<Ordine> getOrders() throws SQLException {
        ArrayList<Ordine> l = new ArrayList<>();
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            query = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
            rs = query.executeQuery();
            while (rs.next()) {
                Integer id_ordine = rs.getInt(1);
                java.sql.Date data_ordine = rs.getDate(2);
                Float prezzo = rs.getFloat(3);
                String prodotti = rs.getString(4);
                Integer id_carrello = rs.getInt(5);
                Ordine o = new Ordine(data_ordine,prezzo,prodotti,id_carrello);
                o.setID_carrello(id_ordine);
                l.add(o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return l;
    }
    public ArrayList<Ordine> getOrdersByData(java.sql.Date d1, java.sql.Date d2) throws SQLException {
        ArrayList<Ordine> l = new ArrayList<>();
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            query = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE Data_ordine < ? AND ID_carrello > ?");
            query.setDate(1, d1);
            query.setDate(2, d2);
            rs = query.executeQuery();
            while (rs.next()) {
                Integer id_ordine = rs.getInt(1);
                java.sql.Date data_ordine = rs.getDate(2);
                Float prezzo = rs.getFloat(3);
                String prodotti = rs.getString(4);
                Integer id_carrello = rs.getInt(5);
                Ordine o = new Ordine(data_ordine,prezzo,prodotti,id_carrello);
                o.setIdOrdine(id_ordine);
                l.add(o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    public ArrayList<Ordine> getOrdersByUser (int ID_carrello) throws SQLException{

        Connection conn = null;
        PreparedStatement query4 = null;
        ArrayList<Ordine> ordini = new ArrayList<>();

        try {

            conn = ds.getConnection();
            query4 = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE ID_carrello = ?");

            query4.setInt(1, ID_carrello);

            ResultSet rs = query4.executeQuery();

            while (rs.next()) {
                Integer id_ordine = rs.getInt(1);
                Date DataOrdine = rs.getDate(2);
                Float prezzo = rs.getFloat(3);
                String prodotti = rs.getString(4);
                Integer id_carrello = rs.getInt(5);
                Ordine ordine = new Ordine(DataOrdine,prezzo,prodotti,id_carrello);
                ordine.setIdOrdine(id_ordine);
                ordini.add(ordine);
            }

        }catch (Exception e) {
            System.out.println("Naming Exeption: " + e.getMessage());
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

        return ordini;

    }

    public ArrayList<Prodotto> getProdotti(int IdOrdine) throws SQLException{

        Connection conn = null;
        PreparedStatement query5 = null;
        String prodoti = null;
        ArrayList<Prodotto> listaProdotti = new ArrayList<>();
        ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

        try {

            conn = ds.getConnection();
            query5 = conn.prepareStatement("select Lista_prodotti from  " + TABLE_NAME + " where ID_ordine = ?");

            query5.setInt(1, IdOrdine);

            ResultSet rs = query5.executeQuery();

            if (rs.next()) {

                prodoti = rs.getString(1);
                String[] prodotti = prodoti.split(",");

                for (int i = 0; i < prodotti.length; i++) {
                    listaProdotti.add(prodottoDAO.getProdottoByID(Integer.parseInt(prodotti[i])));
                }
            }else {
                return null;
            }

        }catch (Exception e) {
            System.out.println("Naming Exeption: " + e.getMessage());
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

        return listaProdotti;

    }

}
