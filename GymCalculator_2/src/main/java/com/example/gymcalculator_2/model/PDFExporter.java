package com.example.gymcalculator_2.model;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class PDFExporter {
    private User user;

    public PDFExporter(User user) {
        this.user = user;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Exercise Name", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Category Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Exercise Type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Weight", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Reps", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (LoggedExercise exercise : user.findMostRecentLoggedLift().getLoggedExercises()) {
            table.addCell(exercise.getExerciseName());
            table.addCell(exercise.getCategoryName());
            table.addCell(exercise.getType().toString());
            table.addCell((exercise.getWeight()) + user.getUnitValue());
            table.addCell(String.valueOf(exercise.getReps()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        LoggedLifts loggedLift = user.findMostRecentLoggedLift();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph(String.format("Logged Exercises for " + user.getUsername() + " on: " + loggedLift.getLoggedDate()), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.0f, 3.5f, 3.0f, 1.5f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
