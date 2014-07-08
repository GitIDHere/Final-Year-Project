package uwe.ac.uk.s2Vora.learningAid.Bag;

import uwe.ac.uk.s2Vora.learningAid.Items.Item;
import uwe.ac.uk.s2Vora.learningAid.Items.ItemCollect;
import uwe.ac.uk.s2Vora.learningAid.UserInterface.ErrorPanel;

public class Bag {

    public static int instanceCount = 0;
    private final ItemCollect itemsToCollect;
    private final ErrorPanel errorPanel;

    public Bag() {
        errorPanel = ErrorPanel.getErrorPanelInstance();
        checkInstanceCount();
        instanceCount++;
        itemsToCollect = ItemCollect.getItemsCollectInstance();
        
    }

    //The only method which the users will be able to invoke is the Collect method.
    // This method checks if the item being picked up is one of the required items or not.
    public void collect(Item item) {
        
        if (item != null) {
            
            //Check if the item to be collected is one of the required items.
            if (itemsToCollect.isItemRequired(item.getName())) {
                
                //If the item is to be collected then this is set to true.
                item.setPickedUp(true);
                
                itemsToCollect.checkItemCount();

            } else {
                //Send error to notify the users that they have collected the wrong item.
                errorPanel.setErrorMessage("ERROR: Collecting wrong item");
            }

        } else {
            //send error to tell users that there is no item in front of robot.
            errorPanel.setErrorMessage("ERROR: There is no item in front");
        }
    }

    //Keep track of the amount of instances of the Bag class.
    private void checkInstanceCount() {
        if (instanceCount >= 1) {
            errorPanel.setErrorMessage("ERROR: You are creating two instances of the Bag class. There should be only one");
        }
    }
}
