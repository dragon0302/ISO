package DataManagement;

import java.sql.SQLException;
import java.util.List;

public interface CarrelloDAO {

    public void DoSave(Carrello carello) throws SQLException;
    public void ProdottiCarello(int idProdotto,int idCarrello) throws SQLException;
    public void UpdateProductCarello(List<String> idsProduct, String CF) throws SQLException;
    public Integer GetIdCarrello(String CF) throws SQLException;

}
