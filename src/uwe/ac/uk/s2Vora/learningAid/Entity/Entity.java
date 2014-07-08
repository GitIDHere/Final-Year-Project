package uwe.ac.uk.s2Vora.learningAid.Entity;

import uwe.ac.uk.s2Vora.learningAid.Items.Item;
import java.awt.Point;
import java.util.ArrayList;

public class Entity {

    public static int NOITEM = 0;
    public static int PLAYER = 1;
    public static int ENDZONE = 2;
    public static int HIDDEN = 3;
    public static int GEM = 4;
    public static int KNIFE = 5;

    private ArrayList<ArrayList<Object>> entityList;
    private static Entity entityInstance;

    /*
     The Entitiy class was created so that other classes would be able to retrieve instances of
     the items that are on the level.
     */
    public static Entity getEntityInstance() {

        if (entityInstance == null) {
            entityInstance = new Entity();
        }
        return entityInstance;
    }

    private Entity() {
        entityList = new ArrayList<ArrayList<Object>>();
    }

    //Adds an entitiy to ArrayList which contains all the items.
    public void addEntity(int x, int y, int entitiyType, Object entity) {

        ArrayList<Object> list = new ArrayList<Object>();

        Point coordinates = new Point(x, y);

        list.add(coordinates);
        list.add(entitiyType);
        list.add(entity);

        entityList.add(list);
    }

    //Delete all the entities.
    public void deleteAllEntities() {

        int entityListSize = entityList.size();

        for (int i = entityListSize; i > 1; i--) {
            entityList.remove(i - 1);
        }
    }

    //Remove an entity from the ArrayList.
    public void removeEntity(int x, int y) {

        for (int i = 0; i < entityList.size(); i++) {

            Point coordinate = (Point) entityList.get(i).get(0);

            if (coordinate.getX() == x && coordinate.getY() == y) {
                entityList.remove(i);
            }
        }
    }

    //get an Entity contained within the ArrayList.
    public Item getEntity(int x, int y) {

        for (int i = 0; i < entityList.size(); i++) {

            Point coordinate = (Point) entityList.get(i).get(0);

            if (coordinate.getX() == x && coordinate.getY() == y) {
                return (Item) entityList.get(i).get(2);
            }
        }

        return null;
    }

    //Checks whether a tile at a particular coordinate is an Entity or not.
    public boolean isTileEntity(int x, int y) {

        for (int i = 0; i < entityList.size(); i++) {

            Point coordinate = (Point) entityList.get(i).get(0);

            if (coordinate.getX() == x && coordinate.getY() == y) {
                return true;
            }
        }
        return false;
    }
    
    //returns the type of entity which is positioned at the X and Y values passed in.
    public int getEntityType(int x, int y) {

        for (int i = 0; i < entityList.size(); i++) {

            Point coordinate = (Point) entityList.get(i).get(0);

            if (coordinate.getX() == x && coordinate.getY() == y) {
                return (Integer) entityList.get(i).get(1);
            }
        }

        return 0;
    }
    
    //Sets the Entity type.
    public void setEntityType(int x, int y, int newEntity) {

        for (int i = 0; i < entityList.size(); i++) {

            Point coordinate = (Point) entityList.get(i).get(0);

            if (coordinate.getX() == x && coordinate.getY() == y) {
                entityList.get(i).remove(1);
                entityList.get(i).add(1, newEntity);
            }
        }

    }
    
    //Sets the entity map position.
    public void setEntityMapPos(int x, int y, int newX, int newY, int entityType) {

        for (int i = 0; i < entityList.size(); i++) {

            Point coordinate = (Point) entityList.get(i).get(0);
            int entType = (Integer) entityList.get(i).get(1);

            if (coordinate.getX() == x && coordinate.getY() == y && entType == entityType) {
                Point point = (Point) entityList.get(i).get(0);
                point.setLocation(newX, newY);
            }
        }
    }
    
    //Retreive the robot's position on the level.
    public Point getPlayerMapPos() {

        Point tempPoint = new Point();

        for (int i = 0; i < entityList.size(); i++) {
            if ((Integer) entityList.get(i).get(1) == Entity.PLAYER) {
                tempPoint = (Point) entityList.get(i).get(0);
            }
        }

        return tempPoint;
    }

}
