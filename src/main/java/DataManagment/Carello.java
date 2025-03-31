package DataManagment;

public class Carello {

  private int ID_carello;
  private  String CF_utente;

  public Carello(int ID_carello, String CF_utente) {
    this.ID_carello = ID_carello;
    this.CF_utente = CF_utente;
  }

  public String getCF_utente() {
    return CF_utente;
  }

  public void setCF_utente(String CF_utente) {
    this.CF_utente = CF_utente;
  }

  public int getID_carello() {
    return ID_carello;
  }

  public void setID_carello(int ID_carello) {
    this.ID_carello = ID_carello;
  }
}
