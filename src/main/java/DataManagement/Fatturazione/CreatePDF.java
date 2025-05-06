package DataManagement.Fatturazione;

import DataManagement.OrdineDAOImplement;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

public class CreatePDF {
    public static void main(String[] args) throws IOException {
        String path="invoice.pdf";
        String logoFile="pdflogo.png";
        ImageData logodata = ImageDataFactory.create(logoFile);
        Image img = new Image(logodata);
        PdfWriter pdf_writer = new PdfWriter(path);
        PdfDocument pdf_document = new PdfDocument(pdf_writer);
        pdf_document.setDefaultPageSize(PageSize.A4);
        img.scaleAbsolute(100,100);
        Document document = new Document(pdf_document);
        document.add(img);
        document.add(new Paragraph("Indirizzo di fatturazione: "));
        document.add(new Paragraph());
        document.close();
        pdf_writer.close();
    }
}
