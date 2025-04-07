package DataManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AcquistoDAO {

    public void DoSave(Acquisto acquisto) throws SQLException;
    public ArrayList<Integer> getProdottiPiuAqquistati() throws SQLException;

}
