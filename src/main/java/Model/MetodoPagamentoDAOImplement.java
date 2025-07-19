package Model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagamentoDAOImplement implements MetodoPagamentoDAO {
    private static DataSource ds;
    private static final String TABLE_NAME = "metodoPagamento";

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
            query.setString(2,metodopagamento.getNumerocarta());
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

    public List<MetodoPagamento> getMetodiPagamento(String CF) throws SQLException{

        Connection conn = null;
        PreparedStatement query = null;
        List<MetodoPagamento> metodiPagamento = new ArrayList<>();

        try {
            conn = ds.getConnection();
            query = conn.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE CF_Utente = ?");
            query.setString(1,CF);
            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {

                String numeroCarta = resultSet.getString("NumeroCarta");
                Date dataScadenza = resultSet.getDate("DataScadenza");
                int CVV = resultSet.getInt("CVV");
                String Tipo = resultSet.getString("Tipo");
                boolean Default_pagamento = resultSet.getBoolean("Default_pagamento");
                String CF_utente = resultSet.getString("CF_Utente");

                MetodoPagamento metodoPagamento = new MetodoPagamento(numeroCarta,dataScadenza,CVV,Tipo,Default_pagamento,CF_utente);
                metodiPagamento.add(metodoPagamento);

            }
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

        return metodiPagamento;

    }

    public void setPagamentoDefault(String NumeroCarta, String CF) throws SQLException{

        Connection conn = null;
        PreparedStatement query = null;
        PreparedStatement query2 = null;

        try {

            conn = ds.getConnection();
            query = conn.prepareStatement("update " + TABLE_NAME + " set Default_pagamento = ? where NumeroCarta = ?");
            query2 = conn.prepareStatement("SELECT NumeroCarta FROM " + TABLE_NAME + " WHERE Default_pagamento = 1 AND CF_utente = ?");

            query2.setString(1,CF);

            ResultSet resultSet = query2.executeQuery();

            resultSet.next();

            String numeroCarta = resultSet.getString("NumeroCarta");

            query.setInt(1,0);
            query.setString(2,numeroCarta);

            query.execute();

            query.setInt(1,1);
            query.setString(2,NumeroCarta);

            query.execute();

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

    public String getNumeroCarta(String CF_Utente) throws SQLException{
        String result = null;
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            query = conn.prepareStatement("SELECT NumeroCarta FROM " + TABLE_NAME + " WHERE CF_Utente = ?");
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

