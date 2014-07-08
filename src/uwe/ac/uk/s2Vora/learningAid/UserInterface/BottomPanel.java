package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import uwe.ac.uk.s2Vora.learningAid.Main.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import uwe.ac.uk.s2Vora.learningAid.GamePackage.GameLevel;
import uwe.ac.uk.s2Vora.learningAid.Player.Player;

public class BottomPanel extends JPanel implements ActionListener {

    private final Buttons buttonPane;
    private final ScriptEditor scriptEditor;

    private final ErrorPanel errorPane;
    private final TabPanels tabPanels;
    private GameLevel gameWindow;
    private Player playerInst;
    
    private static BottomPanel getUserInputPanelInst;
    
    public static BottomPanel getInstructionPanel() {

        if (getUserInputPanelInst == null) {
            getUserInputPanelInst = new BottomPanel();
        }
        return getUserInputPanelInst;
    }

    private BottomPanel() {

        setBackground(Color.WHITE);

        ((FlowLayout) this.getLayout()).setVgap(0);
        ((FlowLayout) this.getLayout()).setHgap(0);

        errorPane = ErrorPanel.getErrorPanelInstance();

        gameWindow = GameLevel.getGameLevelInstance();
        playerInst = Player.getPlayerInstance();
        
        scriptEditor = ScriptEditor.getScriptEditorInstance();

        tabPanels = TabPanels.getTabsPanel();
        buttonPane = new Buttons();

        buttonPane.getPlayButton().addActionListener(this);
        buttonPane.getResetButton().addActionListener(this);
        
        add(tabPanels);
        add(buttonPane);
        add(errorPane);
        
        ActivateComponent.ComponentActivate(this, false);
    }
    
    //This is the action event that is initated when the user clicks on the Play button.
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonPane.getPlayButton()) {
            
            errorPane.clearErrors();
            buttonPane.setPlayButtonStates();

            if (scriptEditor.isScriptEmpty() == false) {
                
                try {
                    playerInst.canPlayerMove(true);
                    
                    //Within this method, the whole process of creating UserClass.java is created.
                    
                    final LearningConfig config = LearningConfig.getInstance();

                    CreateUserFile writeToFile = new CreateUserFile();
                    writeToFile.createFile(config.getProperty("userCodeDirectory"), scriptEditor.getUserInput());

                    CompileUserCode compileUserClass = new CompileUserCode();

                    boolean hasClassCompiled = compileUserClass.compileClass(config.getProperty("userCodeDirectory"), "UserClass");

                    if (hasClassCompiled == true) {
                        ExecuteUserCode executeUserCode = new ExecuteUserCode();
                        executeUserCode.runMethod(config.getProperty("userCodeDirectory"), "UserClass");
                    } else {
                        String error = compileUserClass.getErrors();
                        errorPane.setErrorMessage(error);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }

        if (e.getSource() == buttonPane.getResetButton()) {
            buttonPane.enableAllButtons();
            gameWindow.resetLevel();
        }
    }
}
