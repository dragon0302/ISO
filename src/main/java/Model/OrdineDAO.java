package Model;
import java.sql.SQLException;
import java.util.ArrayList;
public interface OrdineDAO {
    public void DoSave (Ordine ordine) throws SQLException;
    public ArrayList<Ordine> getOrders() throws SQLException;
    public ArrayList<Ordine> getOrdersByUser (int ID_carrello) throws SQLException;
    public ArrayList<Ordine> getOrdersByData(java.sql.Date d1, java.sql.Date d2) throws SQLException;
    public ArrayList<Prodotto> getProdotti(int IdOrdine) throws SQLException;
//    public int getIdIndirizzo(int IdOrdine) throws SQLException;
}
