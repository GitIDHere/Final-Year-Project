package uwe.ac.uk.s2Vora.learningAid.GamePackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import uwe.ac.uk.s2Vora.learningAid.Main.LearningConfig;
import uwe.ac.uk.s2Vora.learningAid.UserInterface.ActivateComponent;
import uwe.ac.uk.s2Vora.learningAid.UserInterface.ScriptEditor;
import uwe.ac.uk.s2Vora.learningAid.UserInterface.BottomPanel;

public final class MainMenu extends GameState{

	private Background bg;
	private int currentChoice = 0;
	private final String[] options = {"Play", "Exit"};
	private Font regularFont;
        
        /*
        This class was created based on the tutorial I followed to implement the graohics.
        Author ForeignGuyMike
        Available from: https://www.youtube.com/watch?v=9dzhgsVaiSo
        Accessed: 15 November 2013.
        */
        
	public MainMenu(GameStateManager gsm){
		this.gsm = gsm;
                init();
	}
        
        //Initialise the required objects.
        @Override
	public void init(){
            try{
                final LearningConfig config = LearningConfig.getInstance();
                
                bg = new Background(config.getProperty("MenuBackground"));
                bg.setBackgroundSize(WindowThread.WIDTH, WindowThread.HEIGHT);

                regularFont = new Font("Arial", Font.BOLD, 22);

            }catch(Exception e){
                    e.printStackTrace();
            }
            
        }
        
        @Override
	public void update(){}
        
        //Draw the main menu options to the GamePanel GUI.
	public void draw(Graphics2D g){
            bg.draw(g);

            g.setFont(regularFont);

            for (int i = 0; i < options.length; i++) {
                if(i == currentChoice){
                        g.setColor(Color.BLUE);
                }else{
                        g.setColor(Color.LIGHT_GRAY);
                }

                g.drawString(options[i], 145, 140 + i * 25);
            }
	}
	
        //This method activates the GUI components when the first level starts.
	private void select(){
		
            if(currentChoice == 0){
                ActivateComponent.ComponentActivate(ScriptEditor.getScriptEditorInstance(), true);
                ActivateComponent.ComponentActivate(BottomPanel.getInstructionPanel(), true);
                gsm.setState(GameStateManager.PLAYSTATE);
            }

            if(currentChoice == 1){
                System.exit(0);
            }
	}
        
	
        //This method updates the current option the user is on within the main menu.
        @Override
	public void keyPressed(int k){
		
            if(k == KeyEvent.VK_ENTER){
                select();
            }

            if(k == KeyEvent.VK_UP){
                currentChoice--;
                if(currentChoice == -1){
                    currentChoice = options.length - 1;
                }
            }

            if(k == KeyEvent.VK_DOWN){
                currentChoice++;
                if(currentChoice == options.length){
                    currentChoice = 0;
                }
            }
	}
        
        @Override
	public void keyReleased(int k){}
}