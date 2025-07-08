package DataManagement;

import java.sql.Date;
import java.sql.SQLException;

public interface MetodoPagamentoDAO {
    public void doSave(MetodoPagamento metodopagamento) throws SQLException;
    public double getNumeroCarta(String CF_Utente) throws SQLException;
    public Date getDataScadenza(String CF_Utente) throws SQLException;
    public double getCVV(String CF_Utente) throws SQLException;
    public String getTipo(String CF_Utente) throws SQLException;
}
