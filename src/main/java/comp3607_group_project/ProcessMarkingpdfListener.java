package comp3607_group_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* The ProcessMarkingpdfListener class is responsible for triggering the execution of marking PDF processing tasks
* when the associated button is clicked in the GUIWindow.
*/
public class ProcessMarkingpdfListener implements ActionListener {

    /**
     * The GUIWindow instance associated with this listener.
     */
    private GUIWindow s;

    /**
     * Constructs a ProcessMarkingpdfListener with the specified GUIWindow instance.
     *
     * @param s The GUIWindow instance associated with this listener.
     */
    public ProcessMarkingpdfListener(GUIWindow s){
        this.s= s;
    }

    /**
     * Invoked when the associated button is clicked, triggering the execution of marking PDF processing tasks.
     *
     * @param e The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent e){
        s.processMarkingpdf();
    }
}
