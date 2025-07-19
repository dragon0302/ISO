package Model;

public class Ordine {
    Integer idOrdine;
    java.sql.Date data_ordine;
    Float totale;
    String prodotti;
    Integer ID_carrello;

    public Ordine(java.sql.Date data_ordine, Float totale, String prodotti, Integer ID_carrello) {
        this.data_ordine = data_ordine;
        this.totale = totale;
        this.prodotti = prodotti;
        this.ID_carrello = ID_carrello;
    }

    public Integer getIdOrdine() {return idOrdine;}
    public void setIdOrdine(Integer idOrdine) {this.idOrdine = idOrdine;}
    public java.sql.Date getData_ordine() {return data_ordine;}
    public void setData_ordine(java.sql.Date data_ordine) {this.data_ordine = data_ordine;}
    public Float getTotale() {return totale;}
    public void setTotale(Float totale) {this.totale = totale;}
    public Integer getID_carrello() {return ID_carrello;}
    public void setID_carrello(Integer ID_carrello) {this.ID_carrello = ID_carrello;}
    public String getProdotti() {return prodotti;}
    public void setProdotti(String prodotti) {this.prodotti = prodotti;}
}
