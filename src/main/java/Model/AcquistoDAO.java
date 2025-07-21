package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AcquistoDAO {

    public void DoSave(Acquisto acquisto) throws SQLException;
    public ArrayList<Integer> getProdottiPiuAqquistati(int quantita) throws SQLException;
    public void UpdateQuantity(int ProdottoID, int IDcarello, char segnio) throws SQLException;
    public Integer GetQuntita(int IDcarello,int ProdottoID) throws SQLException;
    public void remouveAqquisto(int idAqquisto) throws SQLException;
    public ArrayList<Integer> getAqquistiByUser(int ID_carrello) throws SQLException;

}
