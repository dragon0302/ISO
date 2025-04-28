package DataManagement;

import java.sql.SQLException;

public interface CarrelloDAO {

    public void DoSave(Carrello carello) throws SQLException;
    public void GetProdottiCarello(int idProdotto,int idCarrello) throws SQLException;
    public void SetProdottiCarello(String idProdotti,String idCarello) throws SQLException;
    public String GetIdCarello(String CF_utente) throws SQLException;

}
