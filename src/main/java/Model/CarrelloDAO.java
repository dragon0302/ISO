package Model;

import java.sql.SQLException;
import java.util.List;

public interface CarrelloDAO {

    public void DoSave(Carrello carello) throws SQLException;
    public void ProdottoCarello(int idProdotto, int idCarrello) throws SQLException;
    public void UpdateProductCarello(List<String> idsProduct, String CF) throws SQLException;
    public Integer GetIdCarrello(String CF) throws SQLException;
    public Boolean CeckProdotto(int idProdotto,String CF_utene) throws SQLException;
    public List<String> GetProductCarello(int idcarello) throws SQLException;

}
