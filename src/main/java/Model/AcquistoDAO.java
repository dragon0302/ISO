package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AcquistoDAO {

    public void DoSave(Acquisto acquisto) throws SQLException;
    public ArrayList<Integer> getProdottiPiuAqquistati() throws SQLException;
    public void UpdateQuantity(int ProdottoID, int IDcarello, char segnio) throws SQLException;
    public Integer GetQuntita(int IDcarello,int ProdottoID) throws SQLException;

}
