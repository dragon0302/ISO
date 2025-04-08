package DataManagement;

public class Carrello {
    private Integer id_carrello;
    private String cf_utente;

    public Carrello(String cf_utente) {
        this.cf_utente = cf_utente;
    }

    public Integer getId_carrello() {
        return id_carrello;
    }

    public String getCf_utente() {
        return cf_utente;
    }

    public void setCf_utente(String cf_utente) {
        this.cf_utente = cf_utente;
    }
}
