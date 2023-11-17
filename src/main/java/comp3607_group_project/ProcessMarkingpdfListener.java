package comp3607_group_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProcessMarkingpdfListener implements ActionListener {
    private GUIWindow s;
    
    public ProcessMarkingpdfListener(GUIWindow s){
        this.s= s;
    }

    public void actionPerformed(ActionEvent e){
        s.processMarkingpdf();
    }
}
