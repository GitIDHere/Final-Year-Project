package uwe.ac.uk.s2Vora.learningAid.Items;

import uwe.ac.uk.s2Vora.learningAid.Animation.Animation;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import uwe.ac.uk.s2Vora.learningAid.Entity.Entity;
import uwe.ac.uk.s2Vora.learningAid.Main.LearningConfig;

public abstract class Item {

    protected int tileSize;
    protected int tileType;

    protected int width;
    protected int height;

    protected double x;
    protected double y;

    protected boolean pickedUp = false;

    protected Entity entityType;

    //Animation
    protected Animation animation;
    protected final int[] numFrames = {1, 7, 1};
    public static final int BLOCK = 0;
    protected static final int REVEAL = 1;
    protected static final int IDLE = 2;

    public static final int GEM = 1;
    public static final int MONEY = 2;
    public static final int MAP = 3;
    public static final int BATTERY = 4;
    public static final int OIL = 5;
    
    protected int itemType;

    //test
    protected boolean reveal = false;

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract void setCurrentAnimation(int animation);
    
    public synchronized void reveal() {
        reveal = true;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Need reconsider
    public void setItemType(int type) {
        itemType = type;
    }

    public int getItemType() {
        return itemType;
    }

    protected void loadSprites(ArrayList<BufferedImage[]> spriteArray, String spriteResourceName) {
        try {
            final LearningConfig config = LearningConfig.getInstance();

            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(config.getProperty(spriteResourceName)));

            for (int i = 0; i < 3; i++) {
                BufferedImage[] sprites = new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {
                    sprites[j] = spriteSheet.getSubimage(j * width, i * height, width, height);
                }
                spriteArray.add(sprites);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateItem() {}
    public void drawItem(Graphics2D g) {}
    
    public abstract int getType();
    public abstract String getName();
    public abstract boolean isPickedUp();
    public abstract void setPickedUp(boolean isPicked);

}
