package comp3607_group_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* ActionListener implementation for the "Generate Report" button in the GUIWindow class.
*/
public class GenerateReportListener implements ActionListener {

    /**
     * The GUIWindow instance associated with this listener.
     */
    private GUIWindow s;

     /**
     * Constructs a GenerateReportListener with a reference to the GUIWindow instance.
     *
     * @param s The GUIWindow instance to associate with this listener.
     */
    public GenerateReportListener(GUIWindow s){
        this.s= s;
    }

    /**
     * Invoked when the "Generate Report" button is clicked, triggering the generation of a report.
     *
     * @param e The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent e){
        s.generateReport();
    }
}
