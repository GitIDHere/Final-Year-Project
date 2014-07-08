package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class ContainerPanel extends JPanel{
    
    private final GamePanel window;
    private final BottomPanel bottomPanel;
    private ScriptEditor scriptEditor;

    public ContainerPanel(){
        
        setBackground(Color.WHITE);
        
        setPreferredSize(new Dimension(1090, 683));
        
        window = new GamePanel();
        scriptEditor = ScriptEditor.getScriptEditorInstance();
        bottomPanel = BottomPanel.getInstructionPanel();
        
        add(window, BorderLayout.NORTH);
        add(scriptEditor, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.WEST);
    }

}
