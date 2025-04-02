package DataManagement;

public class Prodotto {
    private String id_prodotto;
    private String nome;
    private Double media_valutazione;
    private String taglia;
    private String descrizione;
    private String categoria;
    private Double prezzo;

    public Prodotto(String id_prodotto, String nome, Double media_valutazione, String taglia, String descrizione, String categoria, Double prezzo) {
        this.id_prodotto = id_prodotto;
        this.nome = nome;
        this.media_valutazione = media_valutazione;
        this.taglia = taglia;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.prezzo = prezzo;
    }

    public String getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(String id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getMedia_valutazione() {
        return media_valutazione;
    }

    public void setMedia_valutazione(Double media_valutazione) {
        this.media_valutazione = media_valutazione;
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

    public Double getPrezzo() {return prezzo;}

    public void setPrezzo(Double prezzo) {}
}
