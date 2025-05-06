package DataManagement;

import Utility.EncodingPassword;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAOImplement implements UtenteDAO {

    private static DataSource ds;
    private static final String TABLE_NAME = "Utente";

    static {
        try {
            Context inixCtx = new InitialContext();
            Context envCtx = (Context) inixCtx.lookup("java:/comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ISO16");
        } catch (NamingException e) {
            System.out.println("Naming Exception: " + e.getMessage());
        }
    }

    public synchronized void DoSave(Utente utente) throws SQLException {
        Connection conn = null;
        PreparedStatement query = null;

        try {
            conn = ds.getConnection();
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME + " (CF, NomeUtente, Password,Salt, Nome, Cognome,Email, Sesso, DataNascita, Amministratore) VALUES (?,?,?,?,?,?,?,?,?,?);");

            query.setString(1, utente.getCf());
            query.setString(2, utente.getNomeutente());
            query.setString(3, utente.getPassword());
            query.setString(4, utente.getSalt());
            query.setString(5, utente.getNome());
            query.setString(6, utente.getCognome());
            query.setString(7, utente.getEmail());
            query.setString(8, utente.getSesso());
            query.setDate(9, utente.getDataNascita());
            query.setBoolean(10, utente.isAmministratore());
            query.execute();
        } catch (Exception e) {
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

    public synchronized boolean CFEsistente(String CF) throws SQLException {
        Connection conn = null;
        PreparedStatement query2 = null;
        boolean esistente = false;

        try {
            conn = ds.getConnection();
            query2 = conn.prepareStatement("SELECT CF FROM " + TABLE_NAME + " WHERE CF = ?");

            query2.setString(1, CF);

            ResultSet rs = query2.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
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
        return esistente;
    }

    public synchronized boolean UtenteEsistente(String nomeUtente) throws SQLException {
        Connection conn = null;
        PreparedStatement query3 = null;
        boolean esistente = false;

        try {
            conn = ds.getConnection();
            query3 = conn.prepareStatement("SELECT NomeUtente FROM " + TABLE_NAME + " WHERE NomeUtente = ?");

            query3.setString(1, nomeUtente);

            ResultSet rs = query3.executeQuery();

            if (rs.next()) {
                esistente = true;
            }else {
                esistente = false;
            }
        } catch (Exception e) {
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
        return esistente;
    }

    public synchronized boolean isAmministratore(String CF) throws SQLException{
        Connection conn = null;
        PreparedStatement query4 = null;
        boolean esistente = false;

        try {
            conn = ds.getConnection();
            query4 = conn.prepareStatement("SELECT Amministratore FROM " + TABLE_NAME + " WHERE CF = ?");

            query4.setString(1, CF);
            ResultSet rs = query4.executeQuery();

            if (rs.next()) {
                esistente = true;
            }else {
                esistente = false;
            }
        } catch (Exception e) {
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
        return esistente;
    }

    public boolean isUtente(String nomeutente, String password) throws SQLException{

        Connection conn = null;
        PreparedStatement query5 = null;
        PreparedStatement query6 = null;
        PreparedStatement query7 = null;
        ResultSet rs1;
        ResultSet rs2;
        ResultSet rs3;
        boolean esiste = false ;
        boolean Uesiste= false;
        boolean Pesiste= false;
        EncodingPassword encod = new EncodingPassword();

        try {
            conn = ds.getConnection();
            query5 = conn.prepareStatement("SELECT nomeUtente FROM " + TABLE_NAME + " WHERE NomeUtente = ?");
            query6 = conn.prepareStatement("select Salt, Password from " + TABLE_NAME + " where CF = ?");
            query7 = conn.prepareStatement("select CF from " + TABLE_NAME + " where NomeUtente = ?");

            query7.setString(1, nomeutente);

            rs2 = query7.executeQuery();
            if (rs2.next()) {
                Uesiste = true;
            }

            if (Uesiste) {
                query6.setString(1, rs2.getString(1));
            };
            rs3 = query6.executeQuery();

            if (rs3.next()) {
                Pesiste = encod.verifyPassword(password, rs3.getString("Password"),rs3.getString("Salt"));
            }

            query5.setString(1, nomeutente);
            rs1 = query5.executeQuery();
            if (rs1.next() && Pesiste) {
                esiste = true;
            } else {
                esiste = false;
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
        return esiste;
    }

    public String getCF(String nomeUtente) throws SQLException{
        Connection conn = null;
        PreparedStatement query8 = null;
        String CF = null;

        try {
            conn = ds.getConnection();
            query8 = conn.prepareStatement( "select CF from " + TABLE_NAME + " where NomeUtente = ?;");

            query8.setString(1, nomeUtente);

            ResultSet rs = query8.executeQuery();

            if (rs.next()) {
                CF = rs.getString("CF");
            }
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
        return CF;
    }

    public Utente getUtente(String NomeUtente) throws SQLException{

        Connection conn = null;
        PreparedStatement query9 = null;
        Utente utente = null;

        try {
            conn = ds.getConnection();
            query9 = conn.prepareStatement("select * from " + TABLE_NAME + " where NomeUtente = ?;");

            query9.setString(1, NomeUtente);
            ResultSet rs = query9.executeQuery();

            if (rs.next()) {
                utente = new Utente(rs.getString("CF"),rs.getString("NomeUtente"),rs.getString("Password"),rs.getString("Salt"),rs.getString("Nome"),rs.getString("Cognome"),rs.getString("email"),rs.getString("Sesso"),rs.getDate("DataNascita"),rs.getBoolean("Amministratore"));
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
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
        return utente;
    }

    public boolean EmailEsistente(String email) throws SQLException{
        Connection conn = null;
        PreparedStatement query10 = null;
        boolean esiste = false ;

        try {
            conn = ds.getConnection();
            query10 = conn.prepareStatement("select Email from " + TABLE_NAME + " where Email = ?;");
            query10.setString(1, email);

            ResultSet rs = query10.executeQuery();

            if (rs.next()) {
                esiste = true;
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (query10 != null) {
                    query10.close();
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return esiste;
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
    public void editPassword (Utente utente,String new_password) throws SQLException {
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
}
