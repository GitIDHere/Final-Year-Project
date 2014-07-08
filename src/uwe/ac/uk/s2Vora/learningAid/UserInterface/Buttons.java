package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Buttons extends JPanel{
    
    private final JButton playButton;
    private final JButton resetButton;

    private final int panelWidth = 170;
    private final int panelHeight = 185;
    
    private final int buttonWidth = 160;
    private final int buttonHeight = 70; 
    
    public Buttons(){
        
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        
        //This is used to add gaps to the side of this JPanel
        this.setBorder(BorderFactory.createEmptyBorder(25, 5, 0, 9));

        playButton = new JButton("Play");
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        playButton.setFont(buttonFont);
        playButton.setName("PlayButton");
        playButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        
        
        resetButton = new JButton("Reset");
        resetButton.setName("StopButton");
        resetButton.setFont(buttonFont);
        resetButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));  

        add(playButton);
        add(resetButton);
    }
    
    
    public JButton getPlayButton(){
        return playButton;
    }
    
    public JButton getResetButton(){
        return resetButton;
    }
    
    public void enableAllButtons(){
        playButton.setEnabled(true);
        resetButton.setEnabled(true);
    }
    
    public void setPlayButtonStates(){
        playButton.setEnabled(false);
        resetButton.setEnabled(true);
    }
 
}
