package com.inmotion.githubuser.util;


import com.inmotion.githubuser.dao.UserDao;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class UserPDFExporter {
    private final List<UserDao> users;

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Username", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Github RL", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Avatar URL", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Followers URL", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Repos URL", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (UserDao user : users) {
            table.addCell(user.getLogin());
            table.addCell(user.getHtml_url());
            table.addCell(user.getAvatar_url());
            table.addCell(user.getFollowers_url());
            table.addCell(user.getRepos_url());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Github Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
