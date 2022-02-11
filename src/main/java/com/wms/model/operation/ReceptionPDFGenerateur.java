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
import java.util.List;

@Data
public class ReceptionPDFGenerateur {

    private String date;
    private Commande cmd;
    private String ref;
    private  List<ControleQualite> controleQualiteList;



    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.PINK);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Article ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prix", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qté demandée", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qté réelle", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("échantillon", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("defectueux", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Accepter", font));
        table.addCell(cell);
    }

    /*
    private void writeTableData(PdfPTable table) {
        for (ControleQualite controleQualite : controleQualiteList) {
            table.addCell(String.valueOf(controleQualite.getLigneCommande().get));
            table.addCell(String.valueOf(inventaire_composante.getComposante().getQuantity()));
            table.addCell(String.valueOf(inventaire_composante.getQuantityInReality()));
            table.addCell(String.valueOf(inventaire_composante.getEcart()));
        }
    }
    */


    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        font.setColor(Color.darkGray);

        Paragraph p1 = new Paragraph("Référence :  "+ref, font);
        Paragraph p2 = new Paragraph("Ustilisateur :  "+date, font);
        Paragraph p3 = new Paragraph("Date de l'inventaire :  "+cmd.getReference(), font);

        document.add(p1);
        document.add(p2);
        document.add(p3);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.5f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        //writeTableData(table);

        document.add(table);

        document.close();

    }
}
