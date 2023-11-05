package comp3607_group_project;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;


import java.io.*;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileHandler {
    public void unzip(String folder) {
        String folderPath = System.getProperty("user.dir") + "\\demo\\src\\main\\resources\\" + folder + ".zip";
        String destFolder = System.getProperty("user.dir") + "\\demo\\src\\main\\resources\\";//should override zip and replace it with unzipped ver

        try {
            ZipFile zipFile = new ZipFile(folderPath);
            zipFile.extractAll(destFolder);

        } catch (ZipException e) {
            System.out.println("Error unzipping file\n");
            e.printStackTrace();
        }

    }

    private String getPdfPath(String folder) { //helper function
        File dir = new File(System.getProperty("user.dir") + "\\demo\\src\\main\\resources\\" + folder); //looks for pdfs in the extracted zip folder
		String[] extensions = new String[] { "pdf" };

        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true); //list of files with pdf extension in current directory

		for (File file : files) { //should only have 1 file
            try {
                return file.getCanonicalPath();

            } catch (IOException e) {
                System.out.println("Error finding assignment pdf\n");
                e.printStackTrace();
            }
			
		}

        return "";
    }

    public void getRubicText(String folder) {
        PdfDocument pdf = new PdfDocument(getPdfPath(folder));
        StringBuilder builder = new StringBuilder();
        PdfTableExtractor extractor = new PdfTableExtractor(pdf);
        
        //Loop through the pages in the PDF
        for (int pageIndex = 0; pageIndex < pdf.getPages().getCount(); pageIndex++) {
        //Extract tables from the current page into a PdfTable array
        PdfTable[] tableLists = extractor.extractTable(pageIndex);

        //If any tables are found
        if (tableLists != null && tableLists.length > 0) {
            //Loop through the tables in the array
            for (PdfTable table : tableLists) {
                //Loop through the rows in the current table
                for (int i = 0; i < table.getRowCount(); i++) {
                    //Loop through the columns in the current table
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        //Extract data from the current table cell and append to the StringBuilder
                        String text = table.getText(i, j);
                        builder.append(text + " | ");

                    }
                    builder.append("\r\n");
                }
            }
        }
    }

    //Write data into a .txt document
    try {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\demo\\src\\main\\resources\\" + folder + "\\pdfText.txt");
        fw.write(builder.toString());
        fw.flush();
        fw.close();

    } catch (IOException e) {
        e.printStackTrace();

    }

}
    
}
