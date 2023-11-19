package comp3607_group_project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class CreatePDF {

    public CreatePDF() {

    }

    public void writeText(String filename, ArrayList<String> text) throws IOException {
        PDDocument file = new PDDocument();
        PDPage page = new PDPage();
        PDPageContentStream contentStream = new PDPageContentStream(file, page);
        file.addPage(page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.COURIER_BOLD, 12);
        contentStream.newLineAtOffset(25, page.getTrimBox().getHeight() - 25);
        for (String s : text) {
            contentStream.showText(s);
            contentStream.newLineAtOffset(0, -25);
        }
        contentStream.endText();
        contentStream.close();
        file.save(filename);
        file.close();
    }

    public void mergePDFs() throws IOException {
        PDDocument newDoc = new PDDocument();
        File fa = new File("MarkedAttributes.pdf");
        File fm = new File("MarkedMethods.pdf");
        File fc = new File("MarkedConstructors.pdf");
        PDDocument f1 = PDDocument.load(fa);
        PDDocument f2 = PDDocument.load(fm);
        PDDocument f3 = PDDocument.load(fc);

        for (int i = 0; i < f1.getNumberOfPages(); i++) {
            newDoc.addPage(f1.getPage(i));
        }
        for (int i = 0; i < f2.getNumberOfPages(); i++) {
            newDoc.addPage(f2.getPage(i));
        }
        for (int i = 0; i < f3.getNumberOfPages(); i++) {
            newDoc.addPage(f3.getPage(i));
        }
        newDoc.save("Marked Assignment.pdf");
        newDoc.close();

    }
}