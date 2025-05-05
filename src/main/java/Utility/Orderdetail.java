package Utility;

public class Orderdetail {

    private String NomeProdotto;
    private float Prezzo;
    private String iva;
    private float totale;

    public Orderdetail(String nomeProdotto, float prezzo, String iva, float totale) {
        NomeProdotto = nomeProdotto;
        Prezzo = prezzo;
        this.iva = iva;
        this.totale = totale;
    }

    public String getNomeProdotto() {
        return NomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        NomeProdotto = nomeProdotto;
    }

    public float getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(float prezzo) {
        Prezzo = prezzo;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}
