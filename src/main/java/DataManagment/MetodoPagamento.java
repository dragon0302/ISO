package DataManagment;

import java.util.Date;

public class MetodoPagamento {

  private double numerocarta;
  private Date dataScadenza;
  private int cvv;
  private String tipo;
  private boolean DefaultPagamento;
  private String CF_utente;

  public MetodoPagamento(double numerocarta, Date dataScadenza, int cvv, String tipo, boolean defaultPagamento, String CF_utente){
    this.numerocarta = numerocarta;
    this.dataScadenza = dataScadenza;
    this.cvv = cvv;
    this.tipo = tipo;
    this.DefaultPagamento = defaultPagamento;
    this.CF_utente = CF_utente;
  }

  public double getNumerocarta() {
    return numerocarta;
  }

  public void setNumerocarta(double numerocarta) {
    this.numerocarta = numerocarta;
  }

  public Date getDataScadenza() {
    return dataScadenza;
  }

  public void setDataScadenza(Date dataScadenza) {
    this.dataScadenza = dataScadenza;
  }

  public int getCvv() {
    return cvv;
  }

  public void setCvv(int cvv) {
    this.cvv = cvv;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public boolean isDefaultPagamento() {
    return DefaultPagamento;
  }

  public void setDefaultPagamento(boolean defaultPagamento) {
    DefaultPagamento = defaultPagamento;
  }

  public String getCF_utente() {
    return CF_utente;
  }

  public void setCF_utente(String CF_utente) {
    this.CF_utente = CF_utente;
  }
}
