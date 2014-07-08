package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    private final ContainerPanel panels;

    public MainFrame() {
        panels = new ContainerPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panels);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
