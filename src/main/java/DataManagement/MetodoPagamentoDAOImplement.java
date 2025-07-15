package DataManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class MetodoPagamentoDAOImplement implements MetodoPagamentoDAO {
    private static DataSource ds;
    private static final String TABLE_NAME = "metodopagamento";

    static {
        try {
            Context inixCtx = new InitialContext();
            Context envCtx = (Context) inixCtx.lookup("java:/comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ISO16");
        } catch (NamingException e) {
            System.out.println("Naming Exception: " + e.getMessage());
        }
    }
    public synchronized void doSave(MetodoPagamento metodopagamento) throws SQLException{
        Connection conn = null;
        PreparedStatement query = null;
        try {
            conn = ds.getConnection();
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME +  " (CF_Utente, NumeroCarta, DataScadenza, CVV, Tipo, Default_pagamento) VALUES (?,?,?,?,?,?)");
            query = conn.prepareStatement("INSERT INTO " + TABLE_NAME +  " (CF_Utente, NumeroCarta, DataScadenza, CVV, Tipo, Default_pagamento) VALUES (?,?,?,?,?,?)");
            query.setString(1,metodopagamento.getCF_utente());
            query.setDouble(2,metodopagamento.getNumerocarta());
            query.setDate(3,metodopagamento.getDataScadenza());
            query.setDouble(4,metodopagamento.getCvv());
            query.setString(5,metodopagamento.getTipo());
            query.setBoolean(6, metodopagamento.isDefaultPagamento());
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
    public double getNumeroCarta(String CF_Utente) throws SQLException{
        double result = 0;
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            query = conn.prepareStatement("SELECT NumeroCarta FROM " + TABLE_NAME + " WHERE CF_Utente = ?");
            query.setString(1,CF_Utente);
            rs = query.executeQuery();
            if(rs.next()){
                result = rs.getDouble(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public Date getDataScadenza(String CF_Utente) throws SQLException{
        Date result = null;
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            query = conn.prepareStatement("SELECT DataScadenza FROM " + TABLE_NAME + " WHERE CF_Utente = ?");
            query.setString(1,CF_Utente);
            rs = query.executeQuery();
            if(rs.next()){
                result = rs.getDate(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public double getCVV(String CF_Utente) throws SQLException{
        double result = 0;
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            query = conn.prepareStatement("SELECT CVV FROM " + TABLE_NAME + " WHERE CF_Utente = ?");
            query.setString(1,CF_Utente);
            rs = query.executeQuery();
            if(rs.next()){
                result = rs.getDouble(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }


    public String getTipo(String CF_Utente) throws SQLException {
        String result = "";
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            query = conn.prepareStatement("SELECT Tipo FROM " + TABLE_NAME + " WHERE CF_Utente = ?");
            query.setString(1,CF_Utente);
            rs = query.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}

