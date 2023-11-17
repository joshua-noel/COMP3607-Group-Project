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

public class FileHandler {

    public void unzip(String folder) {
        String folderPath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + folder + ".zip";
        String destFolder = System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\"; //extracts to the area with all other program files

        try {
            ZipFile zipFile = new ZipFile(folderPath);
            zipFile.extractAll(destFolder);

        } catch (ZipException e) {
            System.out.println("Error unzipping file\n");
            e.printStackTrace();
        }

    }

    public void appendFiles(String folder) {
        File dir = new File(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder); //looks for java files in the extracted zip folder
		String[] extensions = new String[] { "java" };

        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true); //list of files with java extension in current directory

		for (File file : files) {
            StringBuilder builder = new StringBuilder(); //new string builder obj for each file
            String contents = "";

            //get all file content
            try {
                BufferedReader br = new BufferedReader(new FileReader(file.getCanonicalPath()));
                String line;

                while ((line = br.readLine()) != null) {
                    builder.append(line).append("\n"); //current line content

                }

                contents = builder.toString(); //final product
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file.getCanonicalPath(), false));
                //write the package name to the top of each file
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

    private String getPdfPath(String folder) { //helper function
        File dir = new File(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder); //looks for pdfs in the extracted zip folder
		String[] extensions = new String[] { "pdf" };

        List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true); //list of files with pdf extension in current directory

		for (File file : files) { //should only have 1 file
            try {
                return file.getCanonicalPath();

            } catch (IOException e) {
                System.out.println("Error finding file\n");
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
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder + "\\pdfText.txt");
        fw.write(builder.toString());
        fw.flush();
        fw.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void parseRubricText(String folder) {
        // Read text from file
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

        //Split string by the lines
        String[] lines = pdfText.split("\n");

        StringBuilder result = new StringBuilder();
        boolean insideMethod = false;

        for (String line : lines){
            // Check if the line contains "Method Signature"
            if (line.contains("Attribute")) {
                insideMethod = true;
            }

            if (!insideMethod) {
                // remove all the 
                line = line.replaceAll("\\s*\\|", " ");
            } else {
                //Checks for third "|"
                int thirdPipeIndex = line.indexOf("|", line.indexOf("|") + 1);
                thirdPipeIndex = line.indexOf("|", thirdPipeIndex + 1);

                //Checks if third"|" was found and appens all the data before it"
                line = (thirdPipeIndex != -1) ? line.substring(0, thirdPipeIndex + 1) : line;
                line = line.replaceAll("\\s*\\|", " ");
            }

            result.append(line).append("\n");
        }

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\main\\java\\comp3607_group_project\\" + folder + "\\formattedData.txt");
        //Write to text file
        try {

            Files.writeString(path, result.toString(),StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.print("Invalid Path");
        }
    }    
    
}
