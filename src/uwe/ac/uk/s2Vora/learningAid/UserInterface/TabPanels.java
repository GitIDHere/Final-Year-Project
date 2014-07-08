package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabPanels extends JPanel {

    private final JTabbedPane tabbedPane;

    private LoadInstructions instructions;
    private LoadCodeExamples sampleCodes;

    private static TabPanels tabPanelInst;
    
    private JPanel instructionsTab;
    private JPanel codeExampleTab;

    public static TabPanels getTabsPanel() {
        if (tabPanelInst == null) {
            tabPanelInst = new TabPanels();
        }
        return tabPanelInst;
    }

    private TabPanels() {

        setBackground(Color.WHITE);

        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(600, 185));

        instructions = new LoadInstructions();
        sampleCodes = new LoadCodeExamples();

        instructionsTab = new JPanel();
        tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Instructions</body></html>", instructionsTab);

        codeExampleTab = new JPanel();
        tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Code Example</body></html>", codeExampleTab);

        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void setTabs(int level) {
        System.out.println("lEVEL: "+level);
        JComponent instrucTab = instructions.getInstructions(level);
        tabbedPane.setTitleAt(0, "<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Instructions</body></html>");
        tabbedPane.setComponentAt(0, instrucTab);

        JComponent sampleCodeTab = sampleCodes.getSampleCode(level);
        tabbedPane.setTitleAt(1, "<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Code Example</body></html>");
        tabbedPane.setComponentAt(1, sampleCodeTab);
    }

}
