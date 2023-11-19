package comp3607_group_project;
import java.awt.event.*;
import javax.swing.*;

/**
* The ProcessFolderListener class is responsible for triggering the execution of folder processing tasks
* when the associated button is clicked in the GUIWindow.
*/
public class ProcessFolderListener implements ActionListener{
    private GUIWindow s;

    public ProcessFolderListener(GUIWindow s){
        this.s= s;
    }
    public void actionPerformed(ActionEvent e){
        s.processfolder();
    }
}
