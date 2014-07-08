package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class ErrorPanel extends JPanel {

    private JTextArea errorTextArea;
    private TitledBorder border;
    private static ErrorPanel errorPanelInstance;
    
    public static ErrorPanel getErrorPanelInstance() {
        if (errorPanelInstance == null) {
            errorPanelInstance = new ErrorPanel();
        }
        return errorPanelInstance;
    }
    
    
    private ErrorPanel() {

        setBackground(Color.WHITE);

        setPreferredSize(new Dimension(300, 185));
        setLayout(new GridLayout(1, 1));
        
        //The font style for the text within the text area.
        Font textAreaFont = new Font("Arial", Font.BOLD, 12);
        
        //Create the text area which will hold the error. This surrounds within the whole panel.
        errorTextArea = new JTextArea();
        errorTextArea.setLineWrap(true);
        errorTextArea.setWrapStyleWord(true);
        errorTextArea.setEditable(false);
        errorTextArea.setMargin(new Insets(10,10,10,10));
        errorTextArea.setFont(textAreaFont);
        
        //Setup the error panel border
        border = BorderFactory.createTitledBorder("Errors");
        Font titleFont = new Font("Arial", Font.CENTER_BASELINE, 16);
        border.setTitleFont(titleFont);
        border.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(border);
        
        //Create a scroll panel so that multiple errors can be seen.
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(errorTextArea);
        
        add(scrollPane);
    }
    
    //Clear the contents of the JTextArea upon clicking Stop button.
    public void clearErrors(){
        errorTextArea.setText("");
    }
    
    //Sets the text to be displayed within the error panel.
    public void setErrorMessage(String error){
        errorTextArea.setText(error);
    }
}
