package DataManagement;

import java.sql.SQLException;
import java.util.List;

public interface IndirizzoDAO {
    public void doSave(Indirizzo indirizzo) throws SQLException;
    public List<Indirizzo> getIndirizzo(String CF) throws SQLException;
    public void setDefaultIndirizzo(int indirizzoID, String CF) throws SQLException;
}
