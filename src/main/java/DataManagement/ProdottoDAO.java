package DataManagement;

import java.sql.SQLException;

public interface ProdottoDAO {
    public void doSave(Prodotto prodotto) throws SQLException;
}