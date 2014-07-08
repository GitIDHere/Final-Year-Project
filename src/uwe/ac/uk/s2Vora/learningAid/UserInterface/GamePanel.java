package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import java.awt.BorderLayout;
import uwe.ac.uk.s2Vora.learningAid.GamePackage.WindowThread;
import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private WindowThread mainThread;

    public GamePanel() {
        setLayout(new BorderLayout());
        mainThread = new WindowThread();
        setBackground(Color.WHITE);

        add(mainThread);
    }

}
