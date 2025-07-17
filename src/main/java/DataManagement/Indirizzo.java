package DataManagement;

public class Indirizzo {

  private int ID_Indirizzo;
  private String citta;
  private String provincia;
  private String cap;
  private String via;
  private int civico;
  private String scala;
  private String Indirizzo2;
  private String note;
  private boolean fatturazione;
  private String CF_utente;

  public Indirizzo(String citta, String provincia, String cap, String via, int civico, String scala, String indirizzo2, String note, boolean fatturazione, String CF_utente) {
    this.citta = citta;
    this.provincia = provincia;
    this.cap = cap;
    this.via = via;
    this.civico = civico;
    this.scala = scala;
    this.Indirizzo2 = indirizzo2;
    this.note = note;
    this.fatturazione = fatturazione;
    this.CF_utente = CF_utente;
  }

  public int getID_Indirizzo() {
    return ID_Indirizzo;
  }

  public void setID_Indirizzo(int ID_Indirizzo) {
    this.ID_Indirizzo = ID_Indirizzo;
  }

  public String getCittà() {
    return citta;
  }

  public void setCittà(String citta) {
    this.citta = citta;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public String getCap() {
    return cap;
  }

  public void setCap(String cap) {
    this.cap = cap;
  }

  public String getVia() {
    return via;
  }

  public void setVia(String via) {
    this.via = via;
  }

  public int getCivico() {
    return civico;
  }

  public void setCivico(int civico) {
    this.civico = civico;
  }

  public String getScala() {
    return scala;
  }

  public void setScala(String scala) {
    this.scala = scala;
  }

  public String getIndirizzo2() {
    return Indirizzo2;
  }

  public void setIndirizzo2(String indirizzo2) {
    Indirizzo2 = indirizzo2;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public boolean isFatturazione() {
    return fatturazione;
  }

  public void setFatturazione(boolean fatturazione) {
    this.fatturazione = fatturazione;
  }

  public String getCF_utente() {
    return CF_utente;
  }

  public void setCF_utente(String CF_utente) {
    this.CF_utente = CF_utente;
  }

}
