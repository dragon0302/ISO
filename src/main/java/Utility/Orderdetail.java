package Utility;

import java.util.List;

public class Orderdetail {

    private List<String> NomeProdotti;
    private List<Float> Prezzo;
    private List<Integer> iva;
    private Float totale;

    public Orderdetail(List<String> nomeProdotti, List<Float> prezzi, List<Integer> iva, float totale) {
        NomeProdotti = nomeProdotti;
        Prezzo = prezzi;
        this.iva = iva;
        this.totale = totale;
    }

    public List<String> getNomeProdotto() {
        return NomeProdotti;
    }

    public void setNomeProdotto(List<String> nomeProdotto) {
        NomeProdotti = nomeProdotto;
    }

    public List<Float> getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(List<Float> prezzo) {
        Prezzo = prezzo;
    }

    public List<Integer> getIva() {
        return iva;
    }

    public void setIva(List<Integer> iva) {
        this.iva = iva;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}
