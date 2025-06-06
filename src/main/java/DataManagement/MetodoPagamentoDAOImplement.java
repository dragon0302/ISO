package DataManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            query = conn.prepareStatement("INSERT INTO" + TABLE_NAME +  " (CF_Utente, NumeroCarta, DataScadenza, CVV, Tipo, Default_pagamento) VALUES (?,?,?,?,?,?)");
            query = conn.prepareStatement("INSERT INTO" + TABLE_NAME +  " (CF_Utente, NumeroCarta, DataScadenza, CVV, Tipo, Default_pagamento) VALUES (?,?,?,?,?,?)");
            query.setString(1,metodopagamento.getCF_utente());
            query.setDouble(2,metodopagamento.getNumerocarta());
            //query.setDate(3,metodopagamento.getDataScadenza());
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
}

