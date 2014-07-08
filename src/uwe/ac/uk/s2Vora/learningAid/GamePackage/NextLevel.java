package uwe.ac.uk.s2Vora.learningAid.GamePackage;

import java.awt.Point;
import javax.swing.JOptionPane;
import uwe.ac.uk.s2Vora.learningAid.UserInterface.ActivateComponent;
import uwe.ac.uk.s2Vora.learningAid.UserInterface.BottomPanel;
import uwe.ac.uk.s2Vora.learningAid.UserInterface.ErrorPanel;

public class NextLevel {

    private final GameLevel gameLevelInstance;
    private final Levels levels;
    private final ErrorPanel errorPanel;
    private static NextLevel nextLevelInstance;

    public static NextLevel getNextLevelInstance() {

        if (nextLevelInstance == null) {
            nextLevelInstance = new NextLevel();
        }
        return nextLevelInstance;
    }

    private NextLevel() {
        gameLevelInstance = GameLevel.getGameLevelInstance();
        errorPanel = ErrorPanel.getErrorPanelInstance();
        levels = new Levels();
    }

    //This is the main function which is called when it is required to load the next level.
    public void goToNextLevel(int level) {

        int nextLevel = level;

        //If the next level is 11, then give users the choice of wheter to restart or exit out of the system.
        if (level == 11) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Congratulations you completed all the levels! \n Would you like to play again?",
                    "An Inane Question",
                    JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                nextLevel = 1;
            } else if (choice == 1) {
                System.exit(0);
            } else {
                nextLevel = 1;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Congratulations you completed the level");
        }

        //This is used to re-activate the Play button.
        ActivateComponent.ComponentActivate(BottomPanel.getInstructionPanel(), true);

        //Get the next level map directory and the robot's coordinates.
        String mapResoucePath = levels.getGameLevelResourcePath(nextLevel);
        Point playerCoords = levels.getPlayerCoords(nextLevel);

        //Initate loadMap of the GameLevel instance.
        gameLevelInstance.loadMap(mapResoucePath, nextLevel, (int) playerCoords.getX(), (int) playerCoords.getY());

        errorPanel.clearErrors();
    }

}
