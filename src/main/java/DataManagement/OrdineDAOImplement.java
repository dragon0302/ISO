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
            query = conn.prepareStatement("SELECT * FROM ORDINE");
            rs = query.executeQuery();
            while (rs.next()) {
                Integer id_ordine = rs.getInt(1);
                java.sql.Date data_ordine = rs.getDate(2);
                Float prezzo = rs.getFloat(3);
                String prodotti = rs.getString(4);
                Integer id_carrello = rs.getInt(5);
                Ordine o = new Ordine(id_ordine,data_ordine,prezzo,prodotti,id_carrello);
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
            query = conn.prepareStatement("SELECT * FROM ORDINE WHERE Data_ordine < ? AND ID_carrello > ?");
            query.setDate(1, d1);
            query.setDate(2, d2);
            rs = query.executeQuery();
            while (rs.next()) {
                Integer id_ordine = rs.getInt(1);
                java.sql.Date data_ordine = rs.getDate(2);
                Float prezzo = rs.getFloat(3);
                String prodotti = rs.getString(4);
                Integer id_carrello = rs.getInt(5);
                Ordine o = new Ordine(id_ordine,data_ordine,prezzo,prodotti,id_carrello);
                l.add(o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    public synchronized ArrayList<Prodotto> getAllOrdersFromUtente(Utente utente) throws SQLException {
        ArrayList<Prodotto> p = new ArrayList<>();
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        String nome,desc,taglia,cat;
        Double mv,prz;
        Integer id;
        try {
            query = conn.prepareStatement("SELECT Lista_prodotti" + " FROM Ordine JOIN Carrello on Ordine.ID_Carrello = carrello.ID_Carrello" + " WHERE Carrello.CF_Utente = ? ");
            query.setString(1,utente.getCf());
            rs = query.executeQuery();
            while (rs.next()) {
                nome = rs.getString("Nome");
                desc = rs.getString("Descrizione");
                taglia = rs.getString("Taglia");
                cat = rs.getString("Categoria");
                mv = rs.getDouble("MediaValutazione");
                id = rs.getInt("ID_prodotto");
                prz = rs.getDouble("Prezzo");
                p.add(new Prodotto(nome,mv,taglia,desc,cat,prz));
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    query.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return p;
    }

}
