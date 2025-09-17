package Model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "(Data_Ordine,Prezzo_tot,Lista_prodotti,Lista_quantita,ID_carrello,ID_indirizzo)" + " VALUES (?,?,?,?,?,?)");
            query.setDate(1, ordine.getData_ordine());
            query.setFloat(2, ordine.getTotale());
            query.setString(3, ordine.getProdotti());
            query.setString(4, ordine.getQuantita());
            query.setInt(5, ordine.getID_carrello());
            query.setInt(6, ordine.getID_indirizzo());
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
                String quantita = rs.getString(5);
                Integer id_carrello = rs.getInt(6);
                Integer id_indirizzo = rs.getInt(7);
                Ordine o = new Ordine(data_ordine,prezzo,prodotti,quantita,id_carrello,id_indirizzo);
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
                String quantita = rs.getString(5);
                Integer id_carrello = rs.getInt(6);
                Integer id_indirizzo = rs.getInt(7);
                Ordine o = new Ordine(data_ordine,prezzo,prodotti,quantita,id_carrello,id_indirizzo);
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
                String quantita = rs.getString(5);
                Integer id_carrello = rs.getInt(6);
                Integer id_indirizzo = rs.getInt(7);
                Ordine o = new Ordine(DataOrdine,prezzo,prodotti,quantita,id_carrello,id_indirizzo);
                o.setIdOrdine(id_ordine);
                ordini.add(o);
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
        String prodottis = null;
        ArrayList<Prodotto> listaProdotti = new ArrayList<>();
        ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

        try {

            conn = ds.getConnection();
            query5 = conn.prepareStatement("select Lista_prodotti from  " + TABLE_NAME + " where ID_ordine = ?");

            query5.setInt(1, IdOrdine);

            ResultSet rs = query5.executeQuery();

            if (rs.next()) {

                prodottis = rs.getString(1);
                String[] prodotti = prodottis.split(",");

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


    public int getIdIndirizzo(int IdOrdine) throws SQLException {
        Connection conn = null;
        PreparedStatement query6 = null;
        ResultSet rs = null;
        Integer id_indirizzo = null;
        try{
           conn = ds.getConnection();
            query6 = conn.prepareStatement("SELECT ID_indirizzo FROM " + TABLE_NAME + " WHERE ID_ordine = ?");
            query6.setInt(1, IdOrdine);
            rs = query6.executeQuery();

            if (rs.next()) {
                id_indirizzo = rs.getInt(1);

            }
        }catch (Exception e) {
            System.out.println("Naming Exeption: " + e.getMessage());
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
        return id_indirizzo;
    }

    public Ordine getOrdineByID(int IdOrdine) throws SQLException {
        Connection conn = null;
        PreparedStatement query7 = null;
        ResultSet rs = null;
        Ordine ordine = null;
        try {
            conn = ds.getConnection();
            query7 = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE ID_ordine = ?");
            query7.setInt(1, IdOrdine);
            rs = query7.executeQuery();
            if (rs.next()) {
                Integer id_ordine = rs.getInt(1);
                Date DataOrdine = rs.getDate(2);
                Float prezzo_tot = rs.getFloat(3);
                String prodotti = rs.getString(4);
                String quantita = rs.getString(5);
                Integer id_carrello = rs.getInt(6);
                Integer id_indirizzo = rs.getInt(7);
                ordine = new Ordine(DataOrdine,prezzo_tot,prodotti,quantita,id_carrello,id_indirizzo);
                ordine.setIdOrdine(id_ordine);
            }
            return ordine;
        }finally {
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
    }

    public List<Integer> getQuantityByID(int IdOrdine) throws SQLException{
        Connection conn = null;
        PreparedStatement query8 = null;
        ResultSet rs = null;
        String quantity = null;
        List<Integer> quantityes =  new ArrayList<>();
        try{
            conn = ds.getConnection();
            query8 = conn.prepareStatement("SELECT Lista_quantita FROM " + TABLE_NAME + " WHERE ID_ordine = ?");
            query8.setInt(1, IdOrdine);
            rs = query8.executeQuery();
            if (rs.next()) {
                quantity = rs.getString(1);
            }
            String[] quantities = quantity.split(",");
            for (String c : quantities) {
                quantityes.add(Integer.valueOf(c));
            }
            return quantityes;
        }finally {
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
    }

    public List<Prodotto> getProdottiByUser(int ID_carrello) throws SQLException{

        Connection conn = null;
        PreparedStatement query9 = null;
        List<Prodotto> prodotti = new ArrayList<>();
        ProdottoDAO prodottoDAO = new ProdottoDAOImplement();

        try {
            conn = ds.getConnection();
            query9 = conn.prepareStatement("SELECT Lista_prodotti FROM " + TABLE_NAME + " WHERE ID_carrello = ?");

            query9.setInt(1, ID_carrello);
            ResultSet rs = query9.executeQuery();

            while (rs.next()) {
                String[] liste = new String[]{rs.getString(1)};
                String[] listep = liste[0].split(",");
                for (String p : listep) {

                    if (prodotti.contains(prodottoDAO.getProdottoByID(Integer.parseInt(p)))){
                        continue;
                    }

                    prodotti.add(prodottoDAO.getProdottoByID(Integer.parseInt(p)));
                }
            }

        }finally {
            try {
                if (query9 != null) {
                    query9.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return prodotti;
    }
}
