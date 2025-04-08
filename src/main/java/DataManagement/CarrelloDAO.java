package DataManagement;

import java.sql.SQLException;

public interface CarrelloDAO {

    public void DoSave(Carrello carello) throws SQLException;
    public void ProdottiCarello(int idProdotto,int idCarrello) throws SQLException;

}
