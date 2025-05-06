package DataManagement.Fatturazione;

import DataManagement.Ordine;
import DataManagement.OrdineDAOImplement;
import DataManagement.Prodotto;
import DataManagement.Utente;

import java.util.ArrayList;
import java.sql.Date;

public class Fattura {
    OrdineDAOImplement oDAO = new OrdineDAOImplement();
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
    private ArrayList<Prodotto> listaProdotti;
    private Date dataFatturazione;
    private StatoFattura statoFattura;
    private StatoPagamento statoPagamento;

    public Fattura(Ordine ordine, Utente utente,String CF_fatturato, String nomeFatturato, String cognomeFatturato, String viaFatturato, Integer civicoFatturato, String scalaFatturato, String indirizzo2Fatturato, String cittàFatturato, String provinciaFatturato, String CAP_Fatturato, String tipoPagamento, ArrayList<Prodotto> listaProdotti, Date dataFatturazione, StatoFattura statoFattura, StatoPagamento statoPagamento) {
        this.CF_fatturato = oDAO.getAllOrdersFromUtente();
        this.nomeFatturato = nomeFatturato;
        this.cognomeFatturato = cognomeFatturato;
        this.viaFatturato = viaFatturato;
        this.civicoFatturato = civicoFatturato;
        this.scalaFatturato = scalaFatturato;
        this.indirizzo2Fatturato = indirizzo2Fatturato;
        this.cittàFatturato = cittàFatturato;
        this.provinciaFatturato = provinciaFatturato;
        this.CAP_Fatturato = CAP_Fatturato;
        this.tipoPagamento = tipoPagamento;
        this.listaProdotti = listaProdotti;
        this.dataFatturazione = dataFatturazione;
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

    public ArrayList<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(ArrayList<Prodotto> listaProdotti) {
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
