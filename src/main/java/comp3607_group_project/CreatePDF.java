package comp3607_group_project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
* CreatePDF class responsible for creating PDF documents and merging multiple PDFs.
*/
public class CreatePDF {

    /**
     * Default constructor for the CreatePDF class.
     */
    public CreatePDF() {

    }

    /**
     * Writes text to a PDF file.
     *
     * @param filename The name of the PDF file to be created.
     * @param text     An ArrayList of strings representing the text content to be written.
     * @throws IOException If an I/O error occurs while writing to the PDF file.
     */
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

    /**
     * Merges multiple PDFs into a single PDF document.
     *
     * @param folder The folder containing the PDF files to be merged.
     * @throws IOException If an I/O error occurs while merging the PDFs or saving the new document.
     */
    public void mergePDFs(String folder) throws IOException {
        PDDocument newDoc = new PDDocument();
        File fileAttributes = new File("MarkedAttributes.pdf");
        File fileMethods = new File("MarkedMethods.pdf");
        File fileConstructors = new File("MarkedConstructors.pdf");
        PDDocument f1 = PDDocument.load(fileAttributes);
        PDDocument f2 = PDDocument.load(fileMethods);
        PDDocument f3 = PDDocument.load(fileConstructors);

        for (int i = 0; i < f1.getNumberOfPages(); i++) {
            newDoc.addPage(f1.getPage(i));
        }
        for (int i = 0; i < f2.getNumberOfPages(); i++) {
            newDoc.addPage(f2.getPage(i));
        }
        for (int i = 0; i < f3.getNumberOfPages(); i++) {
            newDoc.addPage(f3.getPage(i));
        }

        newDoc.save(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder + "\\Marked Assignment.pdf");
        newDoc.close();
    }
}
