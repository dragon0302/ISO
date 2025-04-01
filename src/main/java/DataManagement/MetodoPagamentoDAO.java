package DataManagement;

import java.sql.SQLException;

public interface MetodoPagamentoDAO {
    public void doSave(MetodoPagamento metodopagamento) throws SQLException;
}
