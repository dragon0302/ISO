package DataManagement;

import java.sql.SQLException;

public interface UtenteDAO {
    public void doSave(Utente utente) throws SQLException;
}
