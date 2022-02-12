package com.wms.model.operation;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;



@Data
public class LivraisonPDFGenerateur {
    private String date;
    private String descreption;
    private Transport transport;
    private String ref;
    private PreparationCommande preparationCommande;



    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Article ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qté disponible", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qté demandée", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prix U.", font));
        table.addCell(cell);
    }


    private void writeTableData(PdfPTable table) {
        for (Lignelivraison llv : preparationCommande.getLignelivraisons()) {
            table.addCell(String.valueOf(llv.getComposante().getName()));
            table.addCell(String.valueOf(llv.getComposante().getQuantity()));
            table.addCell(String.valueOf(llv.getQuantite()));
            table.addCell(String.valueOf(llv.getPrix()));
        }
    }
    private void writeTableDetail(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(8);
        com.lowagie.text.Font fontt = FontFactory.getFont(FontFactory.HELVETICA);
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        com.lowagie.text.Font fonttitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        fonttitle.setSize(20);
        font.setColor(Color.darkGray);
        fonttitle.setColor(Color.darkGray);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String datecmd = dateFormatter.format(preparationCommande.getDate());

        double totale = 0;
        for (int i = 0 ; i < preparationCommande.getLignelivraisons().size();i++){
            double tt = preparationCommande.getLignelivraisons().get(i).getPrix() * preparationCommande.getLignelivraisons().get(i).getQuantite();
            totale +=  tt;
        }

        cell.setPhrase(new Phrase("Référence             :  BL"+String.valueOf(ref), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Référence de commande :  BC"+String.valueOf(preparationCommande.getReference()), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date de Livraison     :  "+String.valueOf(date), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date de commande      :  "+String.valueOf(datecmd), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Envoyé à               :  "+String.valueOf(preparationCommande.getClient().getName()), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Envoyé par              :  "+String.valueOf(transport.getMatricule()), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Montant de la facture :  "+String.valueOf(totale), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("  ", font));
        table.addCell(cell);

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        com.lowagie.text.Font fonttitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fonttitle.setColor(Color.darkGray);
        fonttitle.setSize(22);
        Paragraph p = new Paragraph("BON DE LIVRAISON", fonttitle);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {7f, 7f});
        table.setSpacingBefore(10);
        writeTableDetail(table);
        //---------------
        PdfPTable table1 = new PdfPTable(4);
        table1.setWidthPercentage(100f);
        table1.setWidths(new float[] {3.5f, 3.5f, 3.5f, 3.5f});
        table1.setSpacingBefore(10);
        writeTableHeader(table1);
        writeTableData(table1);

        document.add(table);
        document.add(new Paragraph(" "));
        document.add(table1);
        document.add(new Paragraph(" "));



        document.close();

    }
}
