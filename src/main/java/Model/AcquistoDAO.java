package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AcquistoDAO {

    public void DoSave(Acquisto acquisto) throws SQLException;
    public ArrayList<Integer> getProdottiPiuAqquistati(int quantita) throws SQLException;
    public void UpdateQuantity(int ProdottoID, int IDcarello, int quantita) throws SQLException;
    public Integer GetQuntita(int IDcarello,int ProdottoID) throws SQLException;
    public Integer getIdAcquisto(int IDprodotto, int Idcarello) throws SQLException;
    public ArrayList<Integer> getAqquistiByUser(int ID_carrello) throws SQLException;
    public void remuveAcquisto(int idAcquisto) throws SQLException;
    public List<Integer> getQuantitaProdotti(int IDcarello) throws SQLException;
}
