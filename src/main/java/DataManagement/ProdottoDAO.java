package DataManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProdottoDAO {
    public void doSave(Prodotto prodotto) throws SQLException;
    public ArrayList<Prodotto> getProdotti() throws SQLException;
    public void deleteProdotto(Prodotto prodotto) throws SQLException;
}