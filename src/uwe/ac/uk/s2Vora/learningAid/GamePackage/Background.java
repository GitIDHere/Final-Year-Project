package uwe.ac.uk.s2Vora.learningAid.GamePackage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Background {

    private BufferedImage image;

    private double posX;
    private double posY;
    private int sizeX;
    private int sizeY;

    /*
     This class was also part of the tutorial which I used to implement the graphics.
     Author: ForeignGuyMike
     Available from: https://www.youtube.com/watch?v=9dzhgsVaiSo
     Accessed: 19 November 2013
     */
    public Background(String s) {

        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Set the position of the background.
    public void setPosition(double x, double y) {
        this.posX = x;
        this.posY = y;
    }

    //Set the size of the background.
    public void setBackgroundSize(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
    }

    public void update() {
    }

    //Draw the background.
    public void draw(Graphics2D g) {
        g.drawImage(image, (int) posX, (int) posY, sizeX, sizeY, null);
    }

}
