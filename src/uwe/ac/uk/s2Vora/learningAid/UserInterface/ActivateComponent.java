
package uwe.ac.uk.s2Vora.learningAid.UserInterface;

import java.awt.Component;
import java.awt.Container;

public class ActivateComponent {
    
    public static void ComponentActivate(Container container, boolean activate){
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(activate);
            if (component instanceof Container) {
                ComponentActivate((Container)component, activate);
            }
        }
    }  
    
}
