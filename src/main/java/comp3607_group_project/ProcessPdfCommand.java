package comp3607_group_project;

public class ProcessPdfCommand implements Command{
    FileHandler handler = new FileHandler();
    private String folder;

    public ProcessPdfCommand(String folder) {
        this.folder = folder;

    }

    public void execute() {
        handler.getRubicText(folder);

    }
}
