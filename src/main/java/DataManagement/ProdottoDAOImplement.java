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
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME +  "(ID_prodotto, Nome, MediaValutazione, Taglia, Descrizione, Categoria) VALUES (?,?,?,?,?,?);");
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
        Connection conn = ds.getConnection();
        PreparedStatement query = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
        ResultSet rs = query.executeQuery();
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        while (rs.next()) {
            int idProdotto = rs.getInt("ID_prodotto");
            String nomeProdotto = rs.getString("Nome");
            Double mediaValutazione = Double.valueOf(rs.getString("MediaValutazione"));
            String taglia = rs.getString("Taglia");
            String descrizione = rs.getString("Descrizione");
            String categoria = rs.getString("Categoria");
            Double prezzo = rs.getDouble("Prezzo");
            Prodotto p = new Prodotto(idProdotto,nomeProdotto,mediaValutazione,taglia,descrizione,categoria,prezzo);
            prodotti.add(p);
        }
        return prodotti;
    }
    public synchronized void deleteProdotto(Prodotto prodotto) throws SQLException{
        Connection conn = ds.getConnection();
        PreparedStatement query = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE ID_prodotto = " + prodotto.getId_prodotto());
    }
}
