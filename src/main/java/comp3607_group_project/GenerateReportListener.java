package comp3607_group_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* ActionListener implementation for the "Generate Report" button in the GUIWindow class.
*/
public class GenerateReportListener implements ActionListener {
    private GUIWindow s;
    public GenerateReportListener(GUIWindow s){
        this.s= s;
    }

    public void actionPerformed(ActionEvent e){
        s.generateReport();
    }
}
