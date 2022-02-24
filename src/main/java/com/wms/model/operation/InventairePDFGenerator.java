package com.wms.model.operation;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.wms.model.personne.Users;
import com.wms.model.stock.Inventaire_composante;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Data
public class InventairePDFGenerator {
    private List<Inventaire_composante> listInvcomp;
    private String date;
    private Users user;
    private String ref;



    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Article ", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantité système", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantité réelle", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ecart", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Inventaire_composante inventaire_composante : listInvcomp) {
            table.addCell(String.valueOf(inventaire_composante.getComposante().getName()));
            table.addCell(String.valueOf(inventaire_composante.getComposante().getQuantity()));
            table.addCell(String.valueOf(inventaire_composante.getQuantityInReality()));
            table.addCell(String.valueOf(inventaire_composante.getEcart()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        font.setColor(Color.darkGray);

        Paragraph p3 = new Paragraph("Référence :  "+ref, font);
        Paragraph p = new Paragraph("Ustilisateur :  "+user.getName(), font);
        Paragraph p1 = new Paragraph("Date de l'inventaire :  "+this.date, font);

        document.add(p);
        document.add(p1);
        document.add(p3);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
