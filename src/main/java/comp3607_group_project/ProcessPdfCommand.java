package comp3607_group_project;

/**
* The ProcessPdfCommand class is responsible for executing the necessary steps to process a marking PDF,
* including extracting rubric text and parsing the extracted text to generate formatted data.
*/
public class ProcessPdfCommand implements Command{
     /**
     * The FileHandler instance used for handling file-related tasks.
     */
    private FileHandler handler = new FileHandler();

    /**
     * The name of the folder associated with the marking PDF.
     */
    private String folder;

    /**
     * Constructs a ProcessPdfCommand with the specified folder name.
     *
     * @param folder The name of the folder associated with the marking PDF.
     */
    public ProcessPdfCommand(String folder) {
        this.folder = folder;

    }

    /**
     * Executes the command to extract rubric text and parse the extracted text to generate formatted data.
     */
    public void execute() {
        handler.getRubicText(folder);
        handler.parseRubricText(folder);

    }
}
