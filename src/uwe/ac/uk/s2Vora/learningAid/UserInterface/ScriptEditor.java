package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class ScriptEditor extends JPanel {

    private final JTextArea scriptArea;
    private JTextArea lines;
    private static ScriptEditor scriptEditorInstance;

    public static ScriptEditor getScriptEditorInstance() {
        if (scriptEditorInstance == null) {
            scriptEditorInstance = new ScriptEditor();
        }
        return scriptEditorInstance;
    }

    /*
     The script editor code has been acquired and adapted from this website:
     http://www.javaprogrammingforums.com/java-swing-tutorials/915-how-add-line-numbers-your-jtextarea.html
         
     Author: Freaky Chris
     Last Accessed: 05/04/2014
     */
    private ScriptEditor() {

        ((FlowLayout) this.getLayout()).setVgap(0);
        ((FlowLayout) this.getLayout()).setHgap(0);

        scriptArea = new JTextArea();
        lines = new JTextArea("1");

        JScrollPane jsp = new JScrollPane();
        jsp.setPreferredSize(new Dimension(290, 475));

        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);
        lines.setMargin(new Insets(3, 10, 0, 10));

        scriptArea.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = scriptArea.getDocument().getLength();
                Element root = scriptArea.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

        });

        jsp.getViewport().add(scriptArea);
        jsp.setRowHeaderView(lines);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(jsp);

        ActivateComponent.ComponentActivate(this, false);
    }

    public String getUserInput() {
        return scriptArea.getText();
    }

    public boolean isScriptEmpty() {
        return scriptArea.getText().equals("");
    }

}
