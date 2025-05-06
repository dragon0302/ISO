package DataManagement;

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
        PreparedStatement query2 = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            query2 = conn.prepareStatement("SELECT * FROM " + TABLE_NAME);
            rs = query2.executeQuery();
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
            System.out.println("Naming Exeption: " + e.getMessage());
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
        return l;
    }
    public ArrayList<Ordine> getOrdersByData(java.sql.Date d1, java.sql.Date d2) throws SQLException {
        ArrayList<Ordine> l = new ArrayList<>();
        Connection conn = null;
        PreparedStatement query3 = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            query3 = conn.prepareStatement("SELECT * FROM "+ TABLE_NAME + " WHERE Data_ordine < ? AND ID_carrello > ?");
            query3.setDate(1, d1);
            query3.setDate(2, d2);
            rs = query3.executeQuery();
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
            System.out.println("Naming Exeption: " + e.getMessage());
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
                ordine.setID_carrello(id_ordine);
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

    public void editCF (Utente utente,String new_cf) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE utente" +  " SET CF = ?" + "WHERE CF = ?");
            query.setString(1, new_cf);
            query.setString(2, utente.getCf());
            ResultSet rs = query.executeQuery();

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

    public void editNomeUtente (Utente utente,String new_nomeutente) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE utente" +  " SET nomeutente = ?" + "WHERE nomeutente = ?");
            query.setString(1, new_nomeutente);
            query.setString(2, utente.getNomeutente());

            ResultSet rs = query.executeQuery();

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
    public void editpassword (Utente utente,String new_password) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE utente" +  " SET Password = ?" + "WHERE CF = ?");
            query.setString(1, new_password);
            query.setString(2, utente.getCf());

            ResultSet rs = query.executeQuery();

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
    public void editNome (Utente utente,String new_nome) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE utente" +  " SET Nome = ?" + "WHERE CF = ?");
            query.setString(1, new_nome);
            query.setString(2, utente.getCf());

            ResultSet rs = query.executeQuery();

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




    public void editCognome(Utente utente,String new_cognome) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE utente" +  " SET Cognome = ?" + "WHERE CF = ?");
            query.setString(1, new_cognome);
            query.setString(2, utente.getCf());

            ResultSet rs = query.executeQuery();

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

    public void editSesso(Utente utente,String new_sesso) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE utente" +  " SET Sesso = ?" + "WHERE CF = ?");
            query.setString(1, new_sesso);
            query.setString(2, utente.getCf());

            ResultSet rs = query.executeQuery();

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
    public void editAdminPrivileges(Utente utente,boolean new_admin) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE utente" +  " SET Amministratore = ?" + "WHERE CF = ?");
            query.setBoolean(1, new_admin);
            query.setString(2, utente.getCf());

            ResultSet rs = query.executeQuery();

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
    public void editDataNascita(Utente utente,java.sql.Date new_dn) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE utente" +  " SET DataNascita = ?" + "WHERE CF = ?");
            query.setDate(1, new_dn);
            query.setString(2, utente.getCf());

            ResultSet rs = query.executeQuery();

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
    public void editIDProdotto(Prodotto prodotto,int new_id) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("UPDATE prodotto" +  " SET ID_Prodotto = ?" + "WHERE ID_Prodotto = ?");
            query.setInt(1, new_id);
            query.setInt(2, prodotto.getId_prodotto());

            ResultSet rs = query.executeQuery();

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
            query = conn.prepareStatement("UPDATE prodotto" +  " SET Nome = ?" + "WHERE ID_Prodotto = ?");
            query.setString(1, new_nomeprodotto);
            query.setInt(2, prodotto.getId_prodotto());

            ResultSet rs = query.executeQuery();

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
            query = conn.prepareStatement("UPDATE prodotto" +  " SET MediaValutazione = ?" + "WHERE ID_prodotto = ?");
            query.setDouble(1, new_mv);
            query.setInt(2, prodotto.getId_prodotto());

            ResultSet rs = query.executeQuery();

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
            query = conn.prepareStatement("UPDATE prodotto" +  " SET Taglia = ?" + "WHERE ID_prodotto = ?");
            query.setString(1, new_taglia);
            query.setInt(2, prodotto.getId_prodotto());

            ResultSet rs = query.executeQuery();

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
            query = conn.prepareStatement("UPDATE prodotto" +  " SET Descrizione = ?" + "WHERE ID_prodotto = ?");
            query.setString(1, new_descrizione);
            query.setInt(2, prodotto.getId_prodotto());

            ResultSet rs = query.executeQuery();

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
            query = conn.prepareStatement("UPDATE prodotto" +  " SET Categoria = ?" + "WHERE ID_Prodotto = ?");
            query.setString(1, new_categoria);
            query.setInt(2, prodotto.getId_prodotto());

            ResultSet rs = query.executeQuery();

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
