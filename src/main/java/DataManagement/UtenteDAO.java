package DataManagement;

import java.sql.SQLException;

public interface UtenteDAO {
    public void DoSave(Utente utente) throws SQLException;
}
