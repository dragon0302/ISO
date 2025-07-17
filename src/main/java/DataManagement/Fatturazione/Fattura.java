package DataManagement.Fatturazione;

import DataManagement.*;
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
    private String listaProdotti;
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

    public void CreatePDF() throws IOException {

        Float totale = (float) 0;
        String dest = "fattura.pdf";
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

        try {
            String imagePath = "src/main/java/DataManagement/Fatturazione/isologo.png";
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image logo = new Image(imageData).scaleToFit(200, 200);
            document.add(logo);
        } catch (Exception e) {
            System.out.println("Logo non trovato o non valido.");
        }

        document.add(new Paragraph(titolo));
        document.add(new Paragraph("ISO 16 S.r.l.\n Via Giovanni Paolo II 123, 00100 Roma\nP.IVA: 01234567890")
                .setFontSize(10)
                .setMarginBottom(20));

        // Dati cliente
        document.add(new Paragraph(titolo_fattuarazione));
//        document.add(new Paragraph(f.getNomeFatturato() +" " + f.getCognomeFatturato() + "\n" + f.getViaFatturato() + " " + f.getCAP_Fatturato() + " " + f.getCittàFatturato() + " " + f.getProvinciaFatturato() + "\n" + f.getCF_fatturato())
//                .setFontSize(10)
//                .setMarginBottom(20));

        // Tabella dei prodotti/servizi
        float[] columnWidths = {4, 1, 2, 2}; // Descrizione, Quantità, Prezzo Unitario, Totale
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();
        table.addHeaderCell("Descrizione");
        table.addHeaderCell("Q.tà");
        table.addHeaderCell("Prezzo Unit.");
        table.addHeaderCell("Totale");
        while (!listaProdotti.isEmpty()) {
            Prodotto p;
            table.addCell("");
            table.addCell("");
            table.addCell("");
            table.addCell("");

            document.add(table);

            document.add(new Paragraph("\nTotale imponibile: € 0,00\nIVA (22%): € 0,00\n**Totale Fattura: € 0,00**")
                    .setFontSize(12)
                    .setMarginTop(20));

            document.add(new Paragraph("\nGrazie mille per averci scelto!")
                    .setFontSize(10));

            document.close();
            System.out.println("Fattura generata: " + dest);
        }
    }
}
