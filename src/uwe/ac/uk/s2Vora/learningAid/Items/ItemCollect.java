package uwe.ac.uk.s2Vora.learningAid.Items;

import uwe.ac.uk.s2Vora.learningAid.GamePackage.GameLevel;
import uwe.ac.uk.s2Vora.learningAid.GamePackage.NextLevel;

public class ItemCollect {

    private static ItemCollect itemsCollectInstance;

    public static ItemCollect getItemsCollectInstance() {
        if (itemsCollectInstance == null) {
            itemsCollectInstance = new ItemCollect();
        }
        return itemsCollectInstance;
    }

    private Object[][] itemsToCollect;
    private int targetsReached;

    private ItemCollect() {
        itemsToCollect = createItemsToCollect(GameLevel.currentLevel);
        targetsReached = 0;
    }
    
    //Reset the variables that are used to keep track of how many items the user has collected.
    // This is so that items do not stack up when the user resets.
    public void reset(){
        targetsReached = 0;
        itemsToCollect = createItemsToCollect(GameLevel.currentLevel);
    }
    
    //Based on the current level, retrieve an array containing the items that will be collected.
    private Object[][] createItemsToCollect(int currentLevel) {

        switch (currentLevel) {
            case 3:
                Object[][] level_3 = {{1}, {"Gem", 0, 2}};
                return level_3;
            case 4:
                Object[][] level_4 = {{2}, {"Gem", 0, 1}, {"Money", 0, 1}};
                return level_4;
            case 5:
                Object[][] level_5 = {{1}, {"Money", 0, 2}};
                return level_5;
            case 6:
                Object[][] level_6 = {{2}, {"Gem", 0, 1}, {"Battery", 0, 1}};
                return level_6;
            case 7:
                Object[][] level_7 = {{3}, {"Money", 0, 1}, {"Battery", 0, 1}, {"Gem", 0, 1}};
                return level_7;
            case 8:
                Object[][] level_8 = {{2}, {"Gem", 0, 2}, {"Map", 0, 1}};
                return level_8;
            case 9:
                Object[][] level_9 = {{3}, {"Map", 0, 1}, {"Money", 0, 1}, {"Battery", 0, 1}};
                return level_9;
            case 10:
                Object[][] level_10 = {{2}, {"Battery", 0, 2}, {"Gem", 0, 1}};
                return level_10;
        }
        return null;
    }
    
    
    //This method checks if an item is required or not.
    public boolean isItemRequired(String itemName) {

        boolean tempBool = false;

        for (int i = 1; i < itemsToCollect.length; i++) {

            if (itemsToCollect[i][0].equals(itemName)) {
                int currentCount = (Integer) itemsToCollect[i][1];
                itemsToCollect[i][1] = currentCount + 1;
                tempBool = true;
            }
        }

        return tempBool;
    }
    
    //Checks how many items that have been collected, and wether to move the next level.
    public void checkItemCount() {

        int totalToCollect = (Integer) itemsToCollect[0][0];

        for (int i = 1; i < itemsToCollect.length; i++) {
           
            if (itemsToCollect[i][1] == itemsToCollect[i][2]) {
                targetsReached++;
            }
            
            if (targetsReached == totalToCollect) {
                //Go to next level if all required items collected
                NextLevel nextLevel = NextLevel.getNextLevelInstance();
                nextLevel.goToNextLevel(GameLevel.currentLevel + 1);
            }
            
        }

    }

}
