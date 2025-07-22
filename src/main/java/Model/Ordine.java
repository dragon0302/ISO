package Model;

public class Ordine {
    Integer idOrdine;
    java.sql.Date data_ordine;
    Float totale;
    String prodotti;
    String quantita;
    Integer ID_carrello;
    Integer ID_indirizzo;

    public Ordine(java.sql.Date data_ordine, Float totale, String prodotti,String quantita, Integer ID_carrello,Integer ID_indirizzo) {
        this.data_ordine = data_ordine;
        this.totale = totale;
        this.prodotti = prodotti;
        this.quantita = quantita;
        this.ID_carrello = ID_carrello;
        this.ID_indirizzo = ID_indirizzo;
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
    public String getQuantita() {return quantita;}
    public void setQuantita(String quantita) {this.quantita = quantita;}

    public Integer getID_indirizzo() {
        return ID_indirizzo;
    }

    public void setID_indirizzo(Integer ID_indirizzo) {
        this.ID_indirizzo = ID_indirizzo;
    }
}
