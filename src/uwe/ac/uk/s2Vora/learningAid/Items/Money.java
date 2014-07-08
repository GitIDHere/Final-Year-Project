package uwe.ac.uk.s2Vora.learningAid.Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import uwe.ac.uk.s2Vora.learningAid.Animation.Animation;
import uwe.ac.uk.s2Vora.learningAid.Entity.Entity;

public class Money extends Item {

    private ArrayList<BufferedImage[]> sprites;
    private int currentAnimation;
    
    public Money() {

        width = 30;
        height = 30;

        entityType = Entity.getEntityInstance();

        animation = new Animation();

        sprites = new ArrayList<BufferedImage[]>();
        loadSprites(sprites, "MoneySpriteSheet");
        
        currentAnimation = BLOCK;
        animation.setFrames(sprites.get(currentAnimation));
    }

    @Override
    public int getType() {
        return Item.MONEY;
    }
    
    @Override
    public String getName() {
        return "Money";
    }

    @Override
    public boolean equals(Object otherObject) {
        return otherObject instanceof Gem;
    }    
    
    @Override
    public void setPickedUp(boolean isPicked) {
        if(isPicked == true){
           entityType.setEntityType((int)x, (int)y, Entity.NOITEM); 
        }else{
            entityType.setEntityType((int)x, (int)y, Entity.HIDDEN); 
        }
        pickedUp = isPicked;
    }
    
    @Override
    public void setCurrentAnimation(int animation){
        currentAnimation = animation;
        this.animation.setFrames(sprites.get(currentAnimation));
    }    
    
    @Override
    public boolean isPickedUp(){
        return pickedUp;
    }

    @Override
    public synchronized void updateItem() {
        
        if (currentAnimation == REVEAL) {
            if (animation.hasPlayedOnce()) {
                currentAnimation = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(500);
                reveal = false;
                notifyAll();
            }
        }

        if (reveal == true) {
            if (currentAnimation != REVEAL) {
                currentAnimation = REVEAL;
                animation.setFrames(sprites.get(REVEAL));
                animation.setDelay(100);
                entityType.setEntityType((int) x, (int) y, Entity.GEM);
                itemType = Entity.GEM;
            }
        }

        animation.update();
    }

    @Override
    public void drawItem(Graphics2D g) {
        g.drawImage(animation.getImage(),
                (int) (x - width / 2),
                (int) (y - height / 2),
                null);
    }

}
