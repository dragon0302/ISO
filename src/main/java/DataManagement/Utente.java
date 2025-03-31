package DataManagement;

import java.util.Date;

public class Utente {
    private String cf;
    private String nomeutente;
    private String nome;
    private String password;
    private String sesso;
    private Date DataNascita;
    private boolean isAmministratore;

    public Utente(String cf, String nomeutente, String nome, String password, String sesso, Date dataNascita, boolean isAmministratore) {
        this.cf = cf;
        this.nomeutente = nomeutente;
        this.nome = nome;
        this.password = password;
        this.sesso = sesso;
        DataNascita = dataNascita;
        this.isAmministratore = isAmministratore;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNomeutente() {
        return nomeutente;
    }

    public void setNomeutente(String nomeutente) {
        this.nomeutente = nomeutente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public Date getDataNascita() {
        return DataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        DataNascita = dataNascita;
    }

    public boolean isAmministratore() {
        return isAmministratore;
    }

    public void setAmministratore(boolean amministratore) {
        isAmministratore = amministratore;
    }
}
