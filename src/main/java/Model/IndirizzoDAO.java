package Model;

import java.sql.SQLException;
import java.util.List;

public interface IndirizzoDAO {
    public void doSave(Indirizzo indirizzo) throws SQLException;
    public List<Indirizzo> getIndirizzo(String CF) throws SQLException;
    public void setDefaultIndirizzo(int indirizzoID, String CF) throws SQLException;
    public Indirizzo getIndirizzoByID(int ID_Indirizzo) throws SQLException;
}
