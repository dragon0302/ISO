package Model.Fatturazione;

import Model.*;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.UnitValue;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

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
    private List<Prodotto> listaProdotti;
    private List<Integer> quantitaProdotti;
    private Date dataFatturazione;
    private StatoFattura statoFattura;
    private StatoPagamento statoPagamento;

    public Fattura(Ordine ordine, Utente utente, Indirizzo indirizzo, StatoFattura statoFattura, StatoPagamento statoPagamento, OrdineDAO odao) throws SQLException {
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
        this.listaProdotti = odao.getProdotti(ordine.getIdOrdine());
        this.quantitaProdotti = odao.getQuantityByID(ordine.getIdOrdine());
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

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<Prodotto> listaProdotti) {
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

    public void CreatePDF() throws IOException {

        Float totale = (float) 0;
        String dest = "C:\\Users\\vxvit\\Desktop\\fattura.pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        PdfFont font = PdfFontFactory.createFont();
        Text titolo = new Text("FATTURA")
                .setFont(font)
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
                .setStrokeWidth(0.5f)
                .setStrokeColor(DeviceGray.BLACK)
                .setFontSize(10);
        Text titolo_fattuarazione = new Text("Indirizzo di Fatturazione:")
                .setFont(font)
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
                .setStrokeWidth(0.5f)
                .setStrokeColor(DeviceGray.BLACK)
                .setFontSize(10);

        document.setMargins(20, 20, 20, 20);

//        try {
//            String imagePath = "src/main/webapp/Immagini/isologo.png";
//            ImageData imageData = ImageDataFactory.create(imagePath);
//            Image logo = new Image(imageData).scaleToFit(200, 200);
//            document.add(logo);
//        } catch (Exception e) {
//            System.out.println("Logo non trovato o non valido.");
//        }

        document.add(new Paragraph(titolo));
        document.add(new Paragraph("ISO 16 S.r.l.\n Via Giovanni Paolo II 123, 00100 Roma\nP.IVA: 01234567890")
                .setFontSize(10)
                .setMarginBottom(20));

        // Dati cliente
        document.add(new Paragraph(titolo_fattuarazione));
        Document add = document.add(new Paragraph(this.nomeFatturato + " " + this.cognomeFatturato + "\n" + getViaFatturato() + " " + getCAP_Fatturato() + " " + getCittàFatturato() + " " + getProvinciaFatturato() + "\n" + getCF_fatturato())
                .setFontSize(10)
                .setMarginBottom(20));

        // Tabella dei prodotti/servizi
        ;
        float[] columnWidths = {4, 1, 2, 2}; // Descrizione, Prezzo per Unità, Totale
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();
        table.addHeaderCell("ID");
        table.addHeaderCell("Descrizione");
        table.addHeaderCell("Prezzo Unitario");
        table.addHeaderCell("Quantità");
        table.addHeaderCell("IVA (%)");
        table.addHeaderCell("Totale (€)");
        table.addHeaderCell("IVA (€)");
        table.addHeaderCell("Totale IVA Inclusa (€)");
        double p_iva,p_prezzototale,p_prezzosenzaiva;
        double iva = 0,prezzototale = 0,prezzosenzaiva = 0;
        System.out.println(quantitaProdotti.size());
        for (int i = 0; i < listaProdotti.size(); i++) {
            System.out.println("Ciclo:"+ i);
            Prodotto p = listaProdotti.get(i);
            int q = quantitaProdotti.get(i);
            p_iva = (p.getPrezzo()*22/100);
            p_prezzosenzaiva = (p.getPrezzo()-p_iva);
            p_prezzototale = (p_prezzosenzaiva * q);
            iva+=p_iva;
            prezzosenzaiva+=p_prezzosenzaiva;
            prezzototale+=p_prezzototale;
            table.addCell(String.valueOf(p.getId_prodotto())); //id prodotto
            table.addCell(String.valueOf(p.getDescrizione())); //descrizione
            table.addCell(String.valueOf(p.getPrezzo())); //prezzo unitario
            table.addCell(String.valueOf(q)); // quantità
            table.addCell(String.valueOf(p.getIva())); // IVA (%)
            table.addCell(String.valueOf(p_prezzosenzaiva)); //TOTALE SENZA IVA
            table.addCell(String.valueOf(p_iva)); // IVA IN EURO
            table.addCell(String.valueOf(p_prezzototale)); // TOTALE CON IVA
        }
        document.add(table);

        document.add(new Paragraph("\nTotale imponibile (€): " + prezzosenzaiva +"\nTotale IVA (€): " + iva + "\n**Totale Fattura (IVA INCLUSA):" + prezzototale )
                .setFontSize(12)
                .setMarginTop(20));

        document.add(new Paragraph("\nGrazie mille per averci scelto! **ROCK AND ROLL NEVER DIES!**")
                .setFontSize(10));

        document.close();
        System.out.println("Fattura generata: " + dest);
    }
}
