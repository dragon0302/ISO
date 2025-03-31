package DataManagement;

public class Prodotto {
    private String id_prodotto;
    private String nome;
    private Double media_valuazione;
    private String taglia;
    private String descrizione;
    private String categoria;

    public Prodotto(String id_prodotto, String nome, Double media_valuazione, String taglia, String descrizione, String categoria) {
        this.id_prodotto = id_prodotto;
        this.nome = nome;
        this.media_valuazione = media_valuazione;
        this.taglia = taglia;
        this.descrizione = descrizione;
        this.categoria = categoria;
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

    public Double getMedia_valuazione() {
        return media_valuazione;
    }

    public void setMedia_valuazione(Double media_valuazione) {
        this.media_valuazione = media_valuazione;
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
}
