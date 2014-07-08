package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import uwe.ac.uk.s2Vora.learningAid.Main.LearningConfig;

public class LoadCodeExamples {

    private JPanel panel;
    
    public JComponent getSampleCode(int level){
        
        panel = new JPanel();
        
        try{
            LearningConfig conf = LearningConfig.getInstance();
            String instructions = conf.getProperty("sampCode_"+level);
            
            JLabel codeExampleLabel = new JLabel(instructions);
            codeExampleLabel.setHorizontalAlignment(JLabel.LEFT);
            codeExampleLabel.setVerticalAlignment(JLabel.TOP);
            
            JScrollPane jsp = new JScrollPane(codeExampleLabel);
            
            panel.setLayout(new GridLayout(1, 1));
            panel.add(jsp);
        }catch(Exception ex){
            ex.getStackTrace();
        }
        
        return panel;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
