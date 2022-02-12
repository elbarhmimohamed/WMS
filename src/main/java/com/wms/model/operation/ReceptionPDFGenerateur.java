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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Data
public class ReceptionPDFGenerateur {

    private String date;
    private Commande cmd;
    private String ref;
   // private Users user;
    private  List<ControleQualite> controleQualiteList;



    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Article ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prix", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qté demandée", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qté reçue", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("échantillon", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("defectueux", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Accepter", font));
        table.addCell(cell);
    }


    private void writeTableData(PdfPTable table) {
        for (ControleQualite controleQualite : controleQualiteList) {
            table.addCell(String.valueOf(controleQualite.getLigneCommande().getComposante().getName()));
            table.addCell(String.valueOf(controleQualite.getLigneCommande().getPrix()));
            table.addCell(String.valueOf(controleQualite.getLigneCommande().getQuantite()));
            table.addCell(String.valueOf(controleQualite.getQuantiteReel()));
            table.addCell(String.valueOf(controleQualite.getEchantillon()));
            table.addCell(String.valueOf(controleQualite.getDefectueux()));
            if (controleQualite.isAccepter()) {
                table.addCell(String.valueOf("OUI"));
            }
            else {
                table.addCell(String.valueOf("NON"));
            }
        }
    }
    private void writeTableDetail(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(8);
        com.lowagie.text.Font fontt = FontFactory.getFont(FontFactory.HELVETICA);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font fonttitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        fonttitle.setSize(20);
        font.setColor(Color.darkGray);
        fonttitle.setColor(Color.darkGray);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String datecmd = dateFormatter.format(cmd.getDate());

        double totale = 0;
        for (int i = 0 ; i < cmd.getLigneCommande().size();i++){
            double tt = cmd.getLigneCommande().get(i).getPrix() * cmd.getLigneCommande().get(i).getQuantite();
            totale +=  tt;
        }

        cell.setPhrase(new Phrase("Référence             :  BR"+String.valueOf(ref), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Référence de commande :  BC"+String.valueOf(cmd.getReference()), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date de Réception     :  "+String.valueOf(date), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date de commande      :  "+String.valueOf(datecmd), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Reçu de               :  "+String.valueOf(cmd.getFournisseur().getName()), font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Montant de la facture :  "+String.valueOf(totale), font));
        table.addCell(cell);

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fonttitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fonttitle.setColor(Color.darkGray);
        fonttitle.setSize(22);
        Paragraph p = new Paragraph("BON DE RECEPTION DE COMMANDE", fonttitle);
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
        PdfPTable table1 = new PdfPTable(7);
        table1.setWidthPercentage(100f);
        table1.setWidths(new float[] {3.5f, 3.5f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f});
        table1.setSpacingBefore(10);
        writeTableHeader(table1);
        writeTableData(table1);

        document.add(table);
        document.add(new Paragraph(" "));
        document.add(table1);
        document.add(new Paragraph(" "));
        Paragraph p1 = new Paragraph("Commentaires", font);
        document.add(p1);
        PdfPTable table2 = new PdfPTable(1);
        table2.setWidthPercentage(100f);
        table2.setWidths(new float[] {20f});
        table2.setSpacingBefore(10);
        PdfPCell cell = new PdfPCell();
        cell.setPaddingBottom(20);
        cell.setPaddingTop(20);
        cell.setPhrase(new Phrase(String.valueOf( "  ")));
        table2.addCell(cell);


        document.add(table2);


        document.close();

    }
}
