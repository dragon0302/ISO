package DataManagement.Fatturazione;

import DataManagement.Indirizzo;
import DataManagement.Ordine;
import DataManagement.OrdineDAOImplement;
import DataManagement.Utente;
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
import javax.servlet;


import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import static com.itextpdf.kernel.pdf.PdfName.FontFamily;
import static com.itextpdf.layout.properties.Property.FONT;

public class CreatePDF {

    public static void main(String[] args) throws IOException, SQLException {
        Date datanascita = new Date(2004,10,4);
        Date dataordine = new Date(2025,6,9);
        Utente u = request.getSession().getAttribute("utente");

        Float totale = (float) 0;
        Ordine o = new Ordine(dataordine,totale,"ciao",1);
        Indirizzo i = new Indirizzo("Nocera Superiore","Salerno","84015","Via delle vie",3,"N/A","C/o Corte Dei Conti","N/A",true,"RTLVTR000000000");

        Fattura f = new Fattura(o,u,i,StatoFattura.Emessa,StatoPagamento.Pagato);
        String dest = "fattura.pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        PdfFont font =  PdfFontFactory.createFont();
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
        document.add(new Paragraph(f.getNomeFatturato() +" " + f.getCognomeFatturato() + "\n" + f.getViaFatturato() + " " + f.getCAP_Fatturato() + " " + f.getCittàFatturato() + " " + f.getProvinciaFatturato() + "\n" + f.getCF_fatturato())
                .setFontSize(10)
                .setMarginBottom(20));

        // Tabella dei prodotti/servizi
        float[] columnWidths = {4, 1, 2, 2}; // Descrizione, Quantità, Prezzo Unitario, Totale
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

        table.addHeaderCell("Descrizione");
        table.addHeaderCell("Q.tà");
        table.addHeaderCell("Prezzo Unit.");
        table.addHeaderCell("Totale");

        // Esempio di righe (preso da chatgpt, devo integrare con un sistema che prende quello che hai ordinato da solo)
        table.addCell("Servizio di consulenza");
        table.addCell("2");
        table.addCell("€ 100,00");
        table.addCell("€ 200,00");

        table.addCell("Sviluppo software");
        table.addCell("1");
        table.addCell("€ 500,00");
        table.addCell("€ 500,00");

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