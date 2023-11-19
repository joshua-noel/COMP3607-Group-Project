package comp3607_group_project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * The CreatePDF class provides functionality for creating and manipulating PDF documents.
 * It includes methods for writing text to a PDF and merging multiple PDFs into a single document.
 */
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
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, page.getBBox().getHeight() - 25);
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
        File fileAttributes = new File("MarkedAttributes.pdf");
        File fileMethods = new File("MarkedMethods.pdf");
        File fileConstructors = new File("MarkedConstructors.pdf");
        PDDocument f1 = Loader.loadPDF(fileAttributes);
        PDDocument f2 = Loader.loadPDF(fileMethods);
        PDDocument f3 = Loader.loadPDF(fileConstructors);

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
