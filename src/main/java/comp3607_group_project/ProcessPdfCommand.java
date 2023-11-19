package comp3607_group_project;

/**
* The ProcessPdfCommand class is responsible for executing the necessary steps to process a marking PDF,
* including extracting rubric text and parsing the extracted text to generate formatted data.
*/
public class ProcessPdfCommand implements Command{
    private FileHandler handler = new FileHandler();
    private String folder;

    public ProcessPdfCommand(String folder) {
        this.folder = folder;

    }

    public void execute() {
        handler.getRubicText(folder);
        handler.parseRubricText(folder);

    }
}
