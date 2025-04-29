package DataManagement;
import java.sql.SQLException;
import java.util.ArrayList;
public interface OrdineDAO {
    public void DoSave (Ordine ordine) throws SQLException;
}
