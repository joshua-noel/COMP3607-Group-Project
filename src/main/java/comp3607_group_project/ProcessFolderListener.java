package comp3607_group_project;
import java.awt.event.*;
import javax.swing.*;

/**
* The ProcessFolderListener class is responsible for triggering the execution of folder processing tasks
* when the associated button is clicked in the GUIWindow.
*/
public class ProcessFolderListener implements ActionListener{
     /**
     * The GUIWindow instance associated with this listener.
     */
    private GUIWindow s;

    /**
     * Constructs a ProcessFolderListener with the specified GUIWindow instance.
     *
     * @param s The GUIWindow instance associated with this listener.
     */
    public ProcessFolderListener(GUIWindow s){
        this.s= s;
    }

    /**
     * Invoked when the associated button is clicked, triggering the execution of folder processing tasks.
     *
     * @param e The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent e){
        s.processfolder();
    }
}
