package comp3607_group_project;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;


import java.io.*;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

/**
 * The FileHandler class provides methods for handling files, including unzipping,
 * appending package names to Java files, extracting text from PDFs, and parsing
 * rubric text from formatted data.
 */
public class FileHandler {

    /**
     * Unzips a compressed folder to a specified destination folder.
     *
     * @param folder The name of the compressed folder to be unzipped.
     */
    public void unzip(String folder) {
        String folderPath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + folder + ".zip";
        String destFolder = System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\";

        try {
            ZipFile zipFile = new ZipFile(folderPath);
            zipFile.extractAll(destFolder);

        } catch (ZipException e) {
            System.out.println("Error unzipping file\n");
            e.printStackTrace();
        }

    }

    /**
     * Appends package names to Java files in a specified folder.
     *
     * @param folder The folder containing Java files.
     */
    public void appendFiles(String folder) {
        File dir = new File(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder); 
		String[] extensions = new String[] { "java" };

        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true); 

		for (File file : files) {
            StringBuilder builder = new StringBuilder(); 
            String contents = "";

            //get all file content
            try {
                BufferedReader br = new BufferedReader(new FileReader(file.getCanonicalPath()));
                String line;

                while ((line = br.readLine()) != null) {
                    builder.append(line).append("\n"); 

                }

                contents = builder.toString();
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file.getCanonicalPath(), false));
                bw.write("package comp3607_group_project." + folder + ";");
                bw.newLine();
                //write all file contents
                bw.write(contents);
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
			
		}        

    }

    /**
     * Gets the path of the PDF file in a specified folder.
     *
     * @param folder The folder containing PDF files.
     * @return The canonical path of the first PDF file found.
     */
    private String getPdfPath(String folder) { 
        File dir = new File(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder); 
		String[] extensions = new String[] { "pdf" };

        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true); 

		for (File file : files) { 
            try {
                return file.getCanonicalPath();

            } catch (IOException e) {
                System.out.println("Error finding file\n");
                e.printStackTrace();
            }
			
		}

        return "";
    }

     /**
     * Extracts rubric text from a PDF file and writes it to a text file.
     *
     * @param folder The folder containing PDF files.
     */
    public void getRubicText(String folder) {
        PdfDocument pdf = new PdfDocument(getPdfPath(folder));
        StringBuilder builder = new StringBuilder();
        PdfTableExtractor extractor = new PdfTableExtractor(pdf);
        

	    
        for (int pageIndex = 0; pageIndex < pdf.getPages().getCount(); pageIndex++) {
        PdfTable[] tableLists = extractor.extractTable(pageIndex);

        
        if (tableLists != null && tableLists.length > 0) {
            for (PdfTable table : tableLists) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        String text = table.getText(i, j);
                        builder.append(text + " | ");

                    }
                    builder.append("\r\n");
                }
            }
        }
    }

 
    try {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder + "\\pdfText.txt");
        fw.write(builder.toString());
        fw.flush();
        fw.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    /**
     * Parses rubric text from a formatted data file and writes it to another file.
     *
     * @param folder The folder containing the formatted data file.
     */
    public void parseRubricText(String folder) {
        BufferedReader reader;
        StringBuilder stringBuilder = new StringBuilder();

		try {
			reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder + "\\pdfText.txt"));
			String line = reader.readLine();

			while (line != null) {
				stringBuilder.append(line);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        String inputData = stringBuilder.toString();
        StringBuilder firstTable = new StringBuilder();
        StringBuilder otherTables = new StringBuilder();
        
        boolean flag = false;
        for(char c : inputData.toCharArray()) {
            if(c != 'A' && flag == false){
                firstTable.append(c);
            } else {
                otherTables.append(c);
                flag = true;
            }
        }
	    
        //Remove special characters
        String strippedData1 = firstTable.toString().replaceAll("\\|  \\|", "|").replaceAll("\r", "").replaceAll("\n", "").replaceAll(" (\\w),", ",").replaceAll(" (\\w)\\)", ")").replaceAll("\\|\\s+", "|");
        String strippedData2 = otherTables.toString().replaceAll(",\\s+", ",").replaceAll("\\,  ", ",").replaceAll("\\b(\\w+)\\s+\\w+\\b", "$1").replaceAll("\\( ","(").replaceAll(" \\)",")").replaceAll(" \\(", "(").replaceAll("\\?", "").replaceAll("\r", "").replaceAll("\n", "").replaceAll(" (\\w),", ",").replaceAll(" (\\w)\\)", ")").replaceAll("\\|\\s+", "|");
        String strippedData = strippedData1 + strippedData2;
        
        // Insert special char every third |
        StringBuilder processedData = new StringBuilder();
        int pipeCount = 0;
        for (char c : strippedData.toCharArray()) {
            if (c == '|') {
                pipeCount++;
                if (pipeCount % 4 == 0) {
                    processedData.append(c);
                    processedData.append("\n");
                } else {
                    processedData.append(c);
                }
            } else {
                processedData.append(c);
            }
        }

        String pdfText = processedData.toString(); 

        String[] lines = pdfText.split("\n");

        StringBuilder result = new StringBuilder();
        boolean insideMethod = false;

        for (String line : lines){
            if (line.contains("Attribute")) {
                insideMethod = true;
            }

            if (!insideMethod) {
                line = line.replaceAll("\\s*\\|", " ");
            } else {
                int thirdPipeIndex = line.indexOf("|", line.indexOf("|") + 1);
                thirdPipeIndex = line.indexOf("|", thirdPipeIndex + 1);

                line = (thirdPipeIndex != -1) ? line.substring(0, thirdPipeIndex + 1) : line;
                line = line.replaceAll("\\s*\\|", " ");
            }

            result.append(line).append("\n");
        }

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder + "\\formattedData.txt");
        try {

            Files.writeString(path, result.toString(),StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.print("Invalid Path");
        }
    }    
    
}
