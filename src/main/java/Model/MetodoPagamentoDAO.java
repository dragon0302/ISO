package Model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface MetodoPagamentoDAO {
    public void doSave(MetodoPagamento metodopagamento) throws SQLException;
    public List<MetodoPagamento> getMetodiPagamento(String CF) throws SQLException;
    public void setPagamentoDefault(String NumeroCarta, String CF) throws SQLException;
    public String getNumeroCarta(String CF_Utente) throws SQLException;
    public Date getDataScadenza(String CF_Utente) throws SQLException;
    public double getCVV(String CF_Utente) throws SQLException;
    public String getTipo(String CF_Utente) throws SQLException;
}
