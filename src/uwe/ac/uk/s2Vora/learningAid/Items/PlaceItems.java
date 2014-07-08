package uwe.ac.uk.s2Vora.learningAid.Items;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import uwe.ac.uk.s2Vora.learningAid.Entity.Entity;
import uwe.ac.uk.s2Vora.learningAid.GamePackage.GameLevel;

public class PlaceItems {

    private final Entity entity;

    private int numItemsToCreate;

    public PlaceItems() {
        entity = Entity.getEntityInstance();
        itemsToCreateHashMap = new HashMap();
        setNumberOfItemsToCreate();
        numItemsToCreate = getItemsToCreate(GameLevel.currentLevel);
    }

    //Retrieve the coordinates for the items that will be created.
    private Point[] getItemCoordinates() {

        switch (GameLevel.currentLevel) {
            case 3:
                Point[] level_3 = {new Point(195, 75), new Point(195, 135), new Point(255, 135)};
                return level_3;
            case 4:
                Point[] level_4 = {new Point(225, 45), new Point(225, 105), new Point(285, 45)};
                return level_4;
            case 5:
                Point[] level_5 = {new Point(225, 75), new Point(225, 135), new Point(285, 105)};
                return level_5;
            case 6:
                Point[] level_6 = {new Point(285, 105), new Point(165, 105), new Point(225, 135)};
                return level_6;
            case 7:
                Point[] level_7 = {new Point(135, 165), new Point(135, 105), new Point(135, 45), new Point(195, 45), new Point(255, 45), new Point(315, 45)};
                return level_7;
            case 8:
                Point[] level_8 = {new Point(135, 135), new Point(225, 135), new Point(315, 135), new Point(315, 75), new Point(225, 75), new Point(135, 75)};
                return level_8;
            case 9:
                Point[] level_9 = {new Point(165, 165), new Point(165, 105), new Point(165, 45), new Point(225, 45), new Point(285, 45), new Point(345, 45)};
                return level_9;
            case 10:
                Point[] level_10 = {new Point(165, 195), new Point(195, 195), new Point(225, 195), new Point(225, 165), new Point(225, 135), new Point(225, 105)};
                return level_10;
        }
        return null;
    }
    
    //Retreive an array based on the current level of the hidden item that will be created.
    private Item[] getItemsToCreate() {

        switch (GameLevel.currentLevel) {
            case 3:
                Item[] level_3 = {new Gem(), new Money(), new Gem()};
                return level_3;
            case 4:
                Item[] level_4 = {new Map(), new Gem(), new Money()};
                return level_4;
            case 5:
                Item[] level_5 = {new Money(), new Battery(), new Money()};
                return level_5;
            case 6:
                Item[] level_6 = {new Money(), new Battery(), new Gem()};
                return level_6;
            case 7:
                Item[] level_7 = {new Map(), new Battery(), new Oil(), new Map(), new Gem(), new Money()};
                return level_7;
            case 8:
                Item[] level_8 = {new Battery(), new Map(), new Gem(), new Oil(), new Battery(), new Gem()};
                return level_8;
            case 9:
                Item[] level_9 = {new Money(), new Gem(), new Battery(), new Oil(), new Map(), new Gem()};
                return level_9;
            case 10:
                Item[] level_10 = {new Oil(), new Battery(), new Gem(), new Map(), new Battery(), new Oil()};
                return level_10;
        }
        return null;
    }

    private final HashMap itemsToCreateHashMap;

    //A method that is used to return the amount of items that are to be created.
    private int getItemsToCreate(int level) {
        return (Integer) itemsToCreateHashMap.get("level_" + Integer.toString(level) + "_items");
    }

    //Create a HashMap of how many items will be created.
    private void setNumberOfItemsToCreate() {
        itemsToCreateHashMap.put("level_1_items", 0);
        itemsToCreateHashMap.put("level_2_items", 0);
        itemsToCreateHashMap.put("level_3_items", 3);
        itemsToCreateHashMap.put("level_4_items", 3);
        itemsToCreateHashMap.put("level_5_items", 3);
        itemsToCreateHashMap.put("level_6_items", 3);
        itemsToCreateHashMap.put("level_7_items", 6);
        itemsToCreateHashMap.put("level_8_items", 6);
        itemsToCreateHashMap.put("level_9_items", 6);
        itemsToCreateHashMap.put("level_10_items", 6);
    }
    
    //This method is used to retreive the items that are displayed on the level.
    public ArrayList<Item> getItems() {

        ArrayList<Item> itemsArray = new ArrayList<Item>(numItemsToCreate);

        //Invoke the two private methods of this class to get the hidden items and thier coordinates.
        Item[] itemsList = getItemsToCreate();
        Point[] coordinates = getItemCoordinates();

        for (int i = 0; i < numItemsToCreate; i++) {

            Item item = itemsList[i];
            item.setPosition(coordinates[i].getX(), coordinates[i].getY());
            item.setItemType(Entity.HIDDEN);
            
            //For every item that is to be created, add it to the Entity class.
            entity.addEntity((int) coordinates[i].getX(), (int) coordinates[i].getY(), Entity.HIDDEN, item);

            itemsArray.add(item);
        }

        return itemsArray;
    }

}
