package comp3607_group_project;
import java.awt.event.*;
import javax.swing.*;

/**
 * The HelpbtnListener class implements the ActionListener to respond to user interaction
 * specifically designed to handle help button events in a GUIWindow.
 */


public class HelpbtnListener implements ActionListener{

    /**
     * The GUIWindow instance associated with this listener.
     */
    private GUIWindow s;

    /**
     * Constructs a HelpbtnListener with a reference to the GUIWindow instance.
     *
     * @param s The GUIWindow instance to associate with this listener.
     */
    public HelpbtnListener(GUIWindow s){
        this.s= s;
    }

    /**
     * Invoked when the help button is clicked, triggering the display of help information.
     *
     * @param e The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent e){
        s.help();
    }
}
