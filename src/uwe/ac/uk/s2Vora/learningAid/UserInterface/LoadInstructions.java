package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import uwe.ac.uk.s2Vora.learningAid.Main.LearningConfig;


public class LoadInstructions {
    
    private JPanel panel;
    
    public JComponent getInstructions(int level){
        
        panel = new JPanel();
        
        try{
            LearningConfig conf = LearningConfig.getInstance();
            String instructions = conf.getProperty("inst_"+level);
            
            JLabel instructionsLabel = new JLabel(instructions);
            instructionsLabel.setHorizontalAlignment(JLabel.LEFT);
            instructionsLabel.setVerticalAlignment(JLabel.TOP);
     
            JScrollPane jsp = new JScrollPane(instructionsLabel);
            
            panel.setLayout(new GridLayout(1, 1));
            panel.add(jsp);
            
        }catch(Exception ex){
            ex.getStackTrace();
        }
        return panel;     
    }

}
