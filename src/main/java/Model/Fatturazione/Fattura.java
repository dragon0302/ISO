package Model.Fatturazione;

import Model.*;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

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

    // Getter e setter omessi per brevità (già corretti nel tuo codice)

    public void CreatePDF() throws IOException {
        String imagePath = "C:\\Users\\vxvit\\IdeaProjects\\ISO\\src\\main\\webapp\\Immagini\\isologo.png";
        String dest = "C:\\Users\\vxvit\\Desktop\\fattura.pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        PdfFont font = PdfFontFactory.createFont();
        document.setMargins(20, 20, 20, 20);

        // Intestazione
        ImageData imageData = ImageDataFactory.create(imagePath);
        Image logo = new Image(imageData).scaleToFit(200, 200);
        document.add(logo);
        Text titolo = new Text("FATTURA")
                .setFont(font)
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
                .setStrokeWidth(0.5f)
                .setStrokeColor(DeviceGray.BLACK)
                .setFontSize(14);

        document.add(new Paragraph(titolo));
        document.add(new Paragraph("ISO 16 S.r.l.\nVia Giovanni Paolo II 123, 00100 Roma\nP.IVA: 01234567890")
                .setFontSize(10)
                .setMarginBottom(20));

        // Dati Cliente
        document.add(new Paragraph(new Text("Indirizzo di Fatturazione:")
                .setFont(font)
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
                .setStrokeWidth(0.5f)
                .setStrokeColor(DeviceGray.BLACK)
                .setFontSize(10)));

        document.add(new Paragraph(nomeFatturato + " " + cognomeFatturato + "\n" +
                viaFatturato + " " + civicoFatturato + ", " + CAP_Fatturato + " " + cittàFatturato + " (" + provinciaFatturato + ")\nCF: " + CF_fatturato)
                .setFontSize(10)
                .setMarginBottom(20));

        // Tabella prodotti
        float[] columnWidths = {1, 4, 2, 1, 1, 2, 2, 2};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

        table.addHeaderCell("ID");
        table.addHeaderCell("Descrizione");
        table.addHeaderCell("Prezzo Unitario");
        table.addHeaderCell("Quantità");
        table.addHeaderCell("IVA (%)");
        table.addHeaderCell("Totale Netto (€)");
        table.addHeaderCell("IVA (€)");
        table.addHeaderCell("Totale Lordo (€)");

        double totaleNetto = 0, totaleIVA = 0, totaleLordo = 0;

        for (int i = 0; i < listaProdotti.size(); i++) {
            Prodotto p = listaProdotti.get(i);
            int q = quantitaProdotti.get(i);
            double prezzoUnitario = p.getPrezzo();
            double percentualeIVA = p.getIva();
            double totaleProdotto = prezzoUnitario * q;
            double valoreIVA = totaleProdotto * percentualeIVA / 100.0;
            double netto = totaleProdotto - valoreIVA;

            totaleNetto += netto;
            totaleIVA += valoreIVA;
            totaleLordo += totaleProdotto;

            table.addCell(String.valueOf(p.getId_prodotto()));
            table.addCell(p.getDescrizione());
            table.addCell(String.format("%.2f", prezzoUnitario));
            table.addCell(String.valueOf(q));
            table.addCell(String.format("%.2f", percentualeIVA));
            table.addCell(String.format("%.2f", netto));
            table.addCell(String.format("%.2f", valoreIVA));
            table.addCell(String.format("%.2f", totaleProdotto));
        }

        document.add(table);

        // Totali
        document.add(new Paragraph("\nTotale Imponibile (Netto): € " + String.format("%.2f", totaleNetto) +
                "\nTotale IVA: € " + String.format("%.2f", totaleIVA) +
                "\nTotale Fattura (IVA Inclusa): € " + String.format("%.2f", totaleLordo))
                .setFontSize(12)
                .setMarginTop(20));

        // Footer
        document.add(new Paragraph("\nGrazie mille per averci scelto!\n**ROCK AND ROLL NEVER DIES!**")
                .setFontSize(10));

        document.close();
        System.out.println("Fattura generata: " + dest);
    }
}