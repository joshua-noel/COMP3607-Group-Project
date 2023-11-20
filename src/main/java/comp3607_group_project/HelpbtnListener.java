package comp3607_group_project;
import java.awt.event.*;
import javax.swing.*;

public class HelpbtnListener implements ActionListener{
    private GUIWindow s;

    public HelpbtnListener(GUIWindow s){
        this.s= s;
    }
    private void actionPerformed(ActionEvent e){
        s.help();
    }
}
