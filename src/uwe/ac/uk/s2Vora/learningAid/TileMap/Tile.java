package uwe.ac.uk.s2Vora.learningAid.TileMap;

import java.awt.image.BufferedImage;

public class Tile{
	
	private BufferedImage image;
	private int type;
	
	//Tile Types
        public static final int GOAL = 1;
        public static final int EMPTY = 0;
	public static int OBSTACLE = 3;

	/*
        Author: ForeignGuyMike
        Availble from: https://www.youtube.com/watch?v=qJpdRFvSj1A
        Acessed: 20 November 2013.
        */
        
	public Tile(BufferedImage Image, int Type){
		this.image = Image;
		this.type = Type;
	}
        
	public Tile(int Type){
		this.type = Type;
	}
        
	public BufferedImage getImage(){
		return image;
	}
        
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public void setType(int tileType){
            this.type = tileType;
	}
        
	public int getType(){
		return type;
	}
	
	
}