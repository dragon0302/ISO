package DataManagement;

public class Prodotto {
    private int id_prodotto;
    private String nome;
    private String taglia;
    private String descrizione;
    private String categoria;
    private float prezzo;
    private int iva;

    public Prodotto(String nome, String taglia, String descrizione, String categoria, float prezzo, int iva) {
        this.nome = nome;
        this.taglia = taglia;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.iva = iva;
    }

    public int getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTaglia() {
        return taglia;
    }

    public void setTaglia(String taglia) {
        this.taglia = taglia;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrezzo() {return prezzo;}

    public void setPrezzo(float prezzo) {}

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }
}
