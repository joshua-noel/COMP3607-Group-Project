package comp3607_group_project;

public class HelpbtnListener implements ActionListener{
    private GUIWindow s;

    public GUIWindow(GUIWindow s){
        this.s= s;
    }
    private void actionPerformed(ActionEvent e){
        s.help();
    }
}
