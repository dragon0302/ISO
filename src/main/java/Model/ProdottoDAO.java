package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProdottoDAO {
    public void doSave(Prodotto prodotto) throws SQLException;
    public ArrayList<Prodotto> getProdotti() throws SQLException;
    public void deleteProdotto(int id) throws SQLException;
    public Prodotto getProdottoByID(int idProdotto) throws SQLException;
    public ArrayList<Prodotto> getProdottiRecenti() throws SQLException;
    public Float GetPrezzo(int id) throws SQLException;
    public List<Prodotto> SerchByCategory(String Category) throws SQLException;
    public void editPrezzo (Prodotto prodotto, float new_prezzo) throws SQLException;
    public void editCategoria(Prodotto prodotto,String new_categoria) throws SQLException;
    public void editDescrizione(Prodotto prodotto,String new_descrizione) throws SQLException;
    public void editTaglia(Prodotto prodotto,String new_taglia) throws SQLException;
    public void editMediaValutazione(Prodotto prodotto,Double new_mv) throws SQLException;
    public void editNomeProdotto(Prodotto prodotto,String new_nomeprodotto) throws SQLException;
    public void editIDProdotto(Prodotto prodotto,int new_id) throws SQLException;

}