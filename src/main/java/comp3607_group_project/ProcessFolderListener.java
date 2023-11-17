package comp3607_group_project;
import java.awt.event.*;
import javax.swing.*;

public class ProcessFolderListener implements ActionListener{
    private GUIWindow s;

    public ProcessFolderListener(GUIWindow s){
        this.s= s;
    }
    public void actionPerformed(ActionEvent e){
        s.processfolder();
    }
}