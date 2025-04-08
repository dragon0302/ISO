package DataManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.Arrays.stream;

public class ProdottoDAOImplement implements ProdottoDAO{
    private static DataSource ds;
    private static final String TABLE_NAME = "prodotto";

    static {
        try {
            Context inixCtx = new InitialContext();
            Context envCtx = (Context) inixCtx.lookup("java:/comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ISO16");
        } catch (NamingException e) {
            System.out.println("Naming Exception: " + e.getMessage());
        }
    }
    public synchronized void doSave(Prodotto prodotto) throws SQLException{
        Connection conn = null;
        PreparedStatement query = null;
        try {
            conn = ds.getConnection();
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME +  "(Nome, MediaValutazione, Taglia, Descrizione, Categoria) VALUES (?,?,?,?,?);");
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
    public synchronized ArrayList<Prodotto> getProdotti() throws SQLException{
        Connection conn = null;
        PreparedStatement query2 = null;
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        try {
            conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
            ResultSet rs = query2.executeQuery();

            while (rs.next()) {
                int idProdotto = rs.getInt("ID_prodotto");
                String nomeProdotto = rs.getString("Nome");
                Double mediaValutazione = Double.valueOf(rs.getString("MediaValutazione"));
                String taglia = rs.getString("Taglia");
                String descrizione = rs.getString("Descrizione");
                String categoria = rs.getString("Categoria");
                Double prezzo = rs.getDouble("Prezzo");
                Prodotto p = new Prodotto( nomeProdotto, mediaValutazione, taglia, descrizione, categoria, prezzo);
                prodotti.add(p);
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
        return prodotti;
    }
    public synchronized void deleteProdotto(Prodotto prodotto) throws SQLException{
        Connection conn = ds.getConnection();
        PreparedStatement query3 = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE ID_prodotto = " + prodotto.getId_prodotto());
    }

    public synchronized ArrayList<Prodotto> getProdottiRecenti() throws SQLException{
        Connection conn = null;
        PreparedStatement query4 = null;
        ArrayList<Prodotto> prodottiRecenti = new ArrayList<>();
        LocalDate ofsetDate= LocalDate.now().minusDays(7);

        try {
            conn = ds.getConnection();
            query4 = conn.prepareStatement("select * from " + TABLE_NAME + " where DataInserimento >= ?");

            query4.setDate(1, Date.valueOf(ofsetDate));

            ResultSet rs = query4.executeQuery();

            while (rs.next()) {
                int idProdotto = rs.getInt("ID_prodotto");
                String nomeProdotto = rs.getString("Nome");
                Double mediaValutazione = Double.valueOf(rs.getString("MediaValutazione"));
                String taglia = rs.getString("Taglia");
                String descrizione = rs.getString("Descrizione");
                String categoria = rs.getString("Categoria");
                Double prezzo = rs.getDouble("Prezzo");
                Prodotto p = new Prodotto(nomeProdotto,mediaValutazione,taglia,descrizione,categoria,prezzo);
                p.setId_prodotto(idProdotto);
                prodottiRecenti.add(p);
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
        return prodottiRecenti;
    }

    @Override
    public Prodotto getProdottoByID(int idProdotto) throws SQLException {

        Connection conn = null;
        PreparedStatement query5 = null;
        Prodotto prodotto = null;

        try {

            conn = ds.getConnection();
            query5 = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE ID_prodotto = ?");

            query5.setInt(1, idProdotto);

            ResultSet rs = query5.executeQuery();

            if (rs.next()) {
                String nomeProdotto = rs.getString("Nome");
                Double mediaValutazione = Double.valueOf(rs.getString("MediaValutazione"));
                String taglia = rs.getString("Taglia");
                String descrizione = rs.getString("Descrizione");
                String categoria = rs.getString("Categoria");
                Double prezzo = rs.getDouble("Prezzo");

                prodotto = new Prodotto(nomeProdotto,mediaValutazione,taglia,descrizione,categoria,prezzo);
            }

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
        return prodotto;
    }
}
