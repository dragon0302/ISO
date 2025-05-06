package DataManagement.Fatturazione;

import DataManagement.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class Fattura {
    OrdineDAOImplement oDAO = new OrdineDAOImplement();
    Utente utente;
    private String CF_fatturato;
    private String nomeFatturato;
    private String cognomeFatturato;
    private String viaFatturato;
    private Integer civicoFatturato;
    private String scalaFatturato;
    private String indirizzo2Fatturato;
    private String cittàFatturato;
    private String provinciaFatturato;
    private String CAP_Fatturato;
    private String tipoPagamento;
    private String listaProdotti; //TODO: Conversione da IDProdotti a ListaProdotti
    private Date dataFatturazione;
    private StatoFattura statoFattura;
    private StatoPagamento statoPagamento;

    public Fattura(Ordine ordine, Utente utente, Indirizzo indirizzo, StatoFattura statoFattura, StatoPagamento statoPagamento) throws SQLException {
        this.CF_fatturato = utente.getCf();
        this.nomeFatturato = utente.getNome();
        this.cognomeFatturato = utente.getCognome();
        this.viaFatturato = indirizzo.getVia();
        this.civicoFatturato = indirizzo.getCivico();
        this.scalaFatturato = indirizzo.getScala();
        this.indirizzo2Fatturato = indirizzo.getIndirizzo2();
        this.cittàFatturato = indirizzo.getCittà();
        this.provinciaFatturato = indirizzo.getProvincia();
        this.CAP_Fatturato = indirizzo.getCap();
        this.tipoPagamento = "PayPal";
        this.listaProdotti = ordine.getProdotti();
        this.dataFatturazione = ordine.getData_ordine();
        this.statoFattura = statoFattura;
        this.statoPagamento = statoPagamento;
    }

    public String getCF_fatturato() {
        return CF_fatturato;
    }

    public void setCF_fatturato(String CF_fatturato) {
        this.CF_fatturato = CF_fatturato;
    }

    public String getNomeFatturato() {
        return nomeFatturato;
    }

    public void setNomeFatturato(String nomeFatturato) {
        this.nomeFatturato = nomeFatturato;
    }

    public String getCognomeFatturato() {
        return cognomeFatturato;
    }

    public void setCognomeFatturato(String cognomeFatturato) {
        this.cognomeFatturato = cognomeFatturato;
    }

    public String getViaFatturato() {
        return viaFatturato;
    }

    public void setViaFatturato(String viaFatturato) {
        this.viaFatturato = viaFatturato;
    }

    public Integer getCivicoFatturato() {
        return civicoFatturato;
    }

    public void setCivicoFatturato(Integer civicoFatturato) {
        this.civicoFatturato = civicoFatturato;
    }

    public String getScalaFatturato() {
        return scalaFatturato;
    }

    public void setScalaFatturato(String scalaFatturato) {
        this.scalaFatturato = scalaFatturato;
    }

    public String getIndirizzo2Fatturato() {
        return indirizzo2Fatturato;
    }

    public void setIndirizzo2Fatturato(String indirizzo2Fatturato) {
        this.indirizzo2Fatturato = indirizzo2Fatturato;
    }

    public String getCittàFatturato() {
        return cittàFatturato;
    }

    public void setCittàFatturato(String cittàFatturato) {
        this.cittàFatturato = cittàFatturato;
    }

    public String getProvinciaFatturato() {
        return provinciaFatturato;
    }

    public void setProvinciaFatturato(String provinciaFatturato) {
        this.provinciaFatturato = provinciaFatturato;
    }

    public String getCAP_Fatturato() {
        return CAP_Fatturato;
    }

    public void setCAP_Fatturato(String CAP_Fatturato) {
        this.CAP_Fatturato = CAP_Fatturato;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(String listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public Date getDataFatturazione() {
        return dataFatturazione;
    }

    public void setDataFatturazione(Date dataFatturazione) {
        this.dataFatturazione = dataFatturazione;
    }

    public StatoFattura getStatoFattura() {
        return statoFattura;
    }

    public void setStatoFattura(StatoFattura statoFattura) {
        this.statoFattura = statoFattura;
    }

    public StatoPagamento getStatoPagamento() {
        return statoPagamento;
    }

    public void setStatoPagamento(StatoPagamento statoPagamento) {
        this.statoPagamento = statoPagamento;
    }
}
