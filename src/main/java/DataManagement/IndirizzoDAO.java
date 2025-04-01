package DataManagement;

import java.sql.SQLException;

public interface IndirizzoDAO {
    public void doSave(Indirizzo indirizzo) throws SQLException;
}
