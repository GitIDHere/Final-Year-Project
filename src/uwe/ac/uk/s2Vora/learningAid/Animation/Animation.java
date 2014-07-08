package uwe.ac.uk.s2Vora.learningAid.Animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import uwe.ac.uk.s2Vora.learningAid.Main.LearningConfig;


public class Animation{
    
    private BufferedImage[] frames;
    private int[] numberOfFrames;
    private int currentFrame;
    
    private int width;
    private int height;
    
    private long startTime;
    private long delay;
    
    private boolean playedOnce;

    /*
    This class was adapted from the turial I for implementing graphics within the GUI.
    Author: ForeignGuyMike
    Available from: https://www.youtube.com/watch?v=ar0hTsb9sxM
    Accessed: 29 November 2013.
    */
    
    public Animation(){
        playedOnce = false;
        width = 30;
        height = 30;
    }
    
    //Loads a spritesheet and breaks it up into a BufferedImage array.
    public void loadSprites(ArrayList<BufferedImage[]> spriteArray, String spriteResourceName, int[] numFrames, int rows) {
        
        numberOfFrames = numFrames;

        try {

            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream(spriteResourceName));

            for (int i = 0; i < rows; i++) {
                BufferedImage[] sprites = new BufferedImage[numberOfFrames[i]];

                for (int j = 0; j < numberOfFrames[i]; j++) {
                    sprites[j] = spriteSheet.getSubimage(j * 30, i * 30, 30, 30);
                }
                spriteArray.add(sprites);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    //load the animation frames;
    public void setFrames(BufferedImage[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }
    
    public void setDelay(long d){delay = d;}
    public void setFrame(int f){currentFrame = f;}
    
    public void update(){

        if(delay == -1){return;}
        
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        
        //if the elapsed time has passed then move on to the next frame.
        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        
        //Check to see if the currentFrame does not go out of bounds
        // with the amount of frames.
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
        
    }
    
    
    public int getFrame(){return currentFrame;}
    
    //Retreive the current frame of the animation.
    public BufferedImage getImage(){return frames[currentFrame];}
    
    public boolean hasPlayedOnce(){return playedOnce;}
    
}
