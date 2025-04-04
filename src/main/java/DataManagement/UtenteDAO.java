package DataManagement;

import java.sql.SQLException;

public interface UtenteDAO {
    public void DoSave(Utente utente) throws SQLException;
    public boolean CFEsistente(String CF) throws SQLException;
    public boolean UtenteEsistente(String nomeUtente) throws SQLException;
    public boolean isAmministratore(String CF) throws SQLException;
}
