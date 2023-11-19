package comp3607_group_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* The ProcessMarkingpdfListener class is responsible for triggering the execution of marking PDF processing tasks
* when the associated button is clicked in the GUIWindow.
*/
public class ProcessMarkingpdfListener implements ActionListener {
    private GUIWindow s;
    
    public ProcessMarkingpdfListener(GUIWindow s){
        this.s= s;
    }

    public void actionPerformed(ActionEvent e){
        s.processMarkingpdf();
    }
}
