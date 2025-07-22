package Model;

import java.util.Date;

public class Utente {
    private String cf;
    private String nomeutente;
    private String nome;
    private String Cognome;
    private String password;
    private String salt;
    private String email;
    private String sesso;
    private Date DataNascita;
    private boolean isAmministratore;

    public Utente(String cf, String nomeutente, String password,String salt, String nome,String cognome,String email, String sesso, Date dataNascita, boolean isAmministratore) {
        this.cf = cf;
        this.nomeutente = nomeutente;
        this.nome = nome;
        this.Cognome = cognome;
        this.password = password;
        this.salt = salt;
        this.email = email;
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

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public java.sql.Date getDataNascita() {
        return (java.sql.Date) DataNascita;
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
