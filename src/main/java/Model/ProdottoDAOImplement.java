package Model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME +  "(Nome, MediaValutazione, Taglia, Descrizione, Categoria,Prezzo,Iva) VALUES (?,?,?,?,?,?,?)");

            query.setString(1, prodotto.getNome());
            query.setDouble(2,prodotto.getMedia_valutazione());
            query.setString(3, prodotto.getTaglia());
            query.setString(4, prodotto.getDescrizione());
            query.setString(5, prodotto.getCategoria());
            query.setFloat(6, prodotto.getPrezzo());
            query.setInt(7, prodotto.getIva());

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
            conn = ds.getConnection();
            query2 = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
            ResultSet rs = query2.executeQuery();

            while (rs.next()) {
                int idProdotto = rs.getInt("ID_prodotto");
                String nomeProdotto = rs.getString("Nome");
                Double mediaValutazione = Double.valueOf(rs.getString("MediaValutazione"));
                String taglia = rs.getString("Taglia");
                String descrizione = rs.getString("Descrizione");
                String categoria = rs.getString("Categoria");
                float prezzo = rs.getFloat("Prezzo");
                int iva = rs.getInt("Iva");
                Prodotto p = new Prodotto( nomeProdotto, mediaValutazione, taglia, descrizione, categoria, prezzo,iva);
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
    public synchronized void deleteProdotto(int id) throws SQLException{
        Connection conn = null;
        PreparedStatement query3 = null;

        try {
            conn = ds.getConnection();
            query3 = conn.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE ID_prodotto = ?");

            query3.setInt(1, id);
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
                float prezzo = rs.getFloat("Prezzo");
                int iva = rs.getInt("Iva");
                Prodotto p = new Prodotto(nomeProdotto,mediaValutazione,taglia,descrizione,categoria,prezzo,iva);
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
                int ID = rs.getInt("ID_prodotto");
                String nomeProdotto = rs.getString("Nome");
                Double mediaValutazione = Double.valueOf(rs.getString("MediaValutazione"));
                String taglia = rs.getString("Taglia");
                String descrizione = rs.getString("Descrizione");
                String categoria = rs.getString("Categoria");
                float prezzo = rs.getFloat("Prezzo");
                int iva = rs.getInt("Iva");

                prodotto = new Prodotto(nomeProdotto,mediaValutazione,taglia,descrizione,categoria,prezzo,iva);
                prodotto.setId_prodotto(ID);
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

    public Float GetPrezzo(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement query6 = null;

        try {
            conn = ds.getConnection();
            query6 = conn.prepareStatement("SELECT Prezzo FROM " + TABLE_NAME + " WHERE ID_prodotto = ?");

            query6.setInt(1, id);

            ResultSet rs = query6.executeQuery();

            if (rs.next()) {
                return rs.getFloat("Prezzo");
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
        return null;
    }

    public List<Prodotto> SerchByCategory(String Category) throws SQLException{

        Connection conn = null;
        PreparedStatement query7 = null;
        ArrayList prodtti = new ArrayList();

        try {
            conn = ds.getConnection();
            query7 = conn.prepareStatement("select * from " + TABLE_NAME + " where Categoria like concat('%', ?, '%')");

            query7.setString(1, Category);

            ResultSet rs = query7.executeQuery();

            while (rs.next()) {
                int ID = rs.getInt("ID_prodotto");
                String nomeProdotto = rs.getString("Nome");
                Double mediaValutazione = Double.valueOf(rs.getString("MediaValutazione"));
                String taglia = rs.getString("Taglia");
                String descrizione = rs.getString("Descrizione");
                String Categoria = rs.getString("Categoria");
                float prezzo = rs.getFloat("Prezzo");
                int iva = rs.getInt("Iva");
                Date dataInserimento = rs.getDate("DataInserimento");

                Prodotto prodotto = new Prodotto(nomeProdotto, mediaValutazione, taglia, descrizione, Categoria, prezzo,iva);
                prodotto.setId_prodotto(ID);

                prodtti.add(prodotto);
            }


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

        return prodtti;

    }

    public void editIDProdotto(Prodotto prodotto,int new_id) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET ID_Prodotto = ? WHERE ID_Prodotto = ?");
            query.setInt(1, new_id);
            query.setInt(2, prodotto.getId_prodotto());

            query.execute();

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

    public void editNomeProdotto(Prodotto prodotto,String new_nomeprodotto) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET Nome = ? WHERE ID_Prodotto = ?");
            query.setString(1, new_nomeprodotto);
            query.setInt(2, prodotto.getId_prodotto());

            query.execute();

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
    public void editMediaValutazione(Prodotto prodotto,Double new_mv) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET MediaValutazione = ? WHERE ID_prodotto = ?");
            query.setDouble(1, new_mv);
            query.setInt(2, prodotto.getId_prodotto());

            query.execute();

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

    public void editTaglia(Prodotto prodotto,String new_taglia) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET Taglia = ? WHERE ID_prodotto = ?");
            query.setString(1, new_taglia);
            query.setInt(2, prodotto.getId_prodotto());

            query.execute();

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
    public void editDescrizione(Prodotto prodotto,String new_descrizione) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET Descrizione = ? WHERE ID_prodotto = ?");
            query.setString(1, new_descrizione);
            query.setInt(2, prodotto.getId_prodotto());

            query.execute();

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

    public void editCategoria(Prodotto prodotto,String new_categoria) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET Categoria = ? WHERE ID_Prodotto = ?");
            query.setString(1, new_categoria);
            query.setInt(2, prodotto.getId_prodotto());

            query.execute();

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

    public void editPrezzo (Prodotto prodotto, float new_prezzo) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET Prezzo = ? WHERE ID_Prodotto = ?");
            query.setFloat(1, new_prezzo);
            query.setInt(2, prodotto.getId_prodotto());

            query.execute();

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
}
