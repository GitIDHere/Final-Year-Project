package uwe.ac.uk.s2Vora.learningAid.GamePackage;

import java.awt.Point;
import java.util.HashMap;

public class Levels {

    private final String[] gameLevels;
    private final HashMap levelHashMap;
    
    public Levels(){
        gameLevels = new String[25];
        createGameLevels();
        levelHashMap = new HashMap();
        initialisePlayerCoords();
    }
    
    //Store the location of where robot will be displayed within a HasMap.
    private void initialisePlayerCoords(){
        levelHashMap.put("level_1_coord", new Point(75, 195));
        levelHashMap.put("level_2_coord", new Point(75, 195));
        levelHashMap.put("level_3_coord", new Point(75, 195));
        levelHashMap.put("level_4_coord", new Point(75, 195));
        levelHashMap.put("level_5_coord", new Point(75, 195));
        levelHashMap.put("level_6_coord", new Point(75, 195));
        levelHashMap.put("level_7_coord", new Point(75, 195));
        levelHashMap.put("level_8_coord", new Point(75, 195));
        levelHashMap.put("level_9_coord", new Point(75, 195));
        levelHashMap.put("level_10_coord", new Point(75, 195));
    }
    
    //Creates an array of path directories of the level.
    private void createGameLevels(){
        for (int i = 0; i < 10; i++) {
            gameLevels[i] = "/Resources/Maps/level_"+(i+1)+".map";
        }
    }
    
    protected String getGameLevelResourcePath(int level){
        return gameLevels[(level-1)];
    }
    
    protected Point getPlayerCoords(int level){
        return (Point) levelHashMap.get("level_"+Integer.toString(level)+"_coord");
    }
 
}
