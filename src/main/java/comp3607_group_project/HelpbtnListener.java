package comp3607_group_project;

/**
 * The HelpbtnListener class implements the ActionListener to respond to user interaction
 * specifically designed to handle help button events in a GUIWindow.
 */


public class HelpbtnListener implements ActionListener{
    private GUIWindow s;

    public GUIWindow(GUIWindow s){
        this.s= s;
    }
    private void actionPerformed(ActionEvent e){
        s.help();
    }
}
