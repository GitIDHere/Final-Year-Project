package uwe.ac.uk.s2Vora.learningAid.TileMap;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import uwe.ac.uk.s2Vora.learningAid.GamePackage.WindowThread;

public class TileMap{
	
	//Position
	private double x;
	private double y;
        
	//map
	private int[][] map;
	private final int tileSize;
	private int numRows;
	private int numCols;

	//Tiles
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	//Drawing
	private final int numRowsToDraw;
	private final int numColsToDraw;
	
	/*
        Author: ForeignGuyMike
        Availble from: https://www.youtube.com/watch?v=qJpdRFvSj1A
        Acessed: 20 November 2013.
        */
        
        
	//This class is used to load the tiles which are displayed within every level.
	public TileMap(int tileSize){
            this.tileSize = tileSize;
            numRowsToDraw = WindowThread.HEIGHT / tileSize;
            numColsToDraw = WindowThread.WIDTH / tileSize;
	}
	
	//Loads the tiles which will be placed ontop of the map tiles.
	public void loadTiles(String s){
		
            try{
                tileset = ImageIO.read(getClass().getResourceAsStream(s));

                numTilesAcross = tileset.getWidth() / tileSize;

                tiles = new Tile[2][numTilesAcross];
                
                BufferedImage subImage;
                for(int col = 0; col < numTilesAcross; col++){
                        subImage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
                        tiles[0][col] = new Tile(subImage, Tile.EMPTY);

                        subImage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
                        tiles[1][col] = new Tile(subImage, Tile.OBSTACLE);
                }

            }catch(Exception e){
                    e.printStackTrace();
            }
		
	} 
        
	//Loads the map tiles of a level.
	public void loadMap(String s){
		
            try{

                InputStream ins = getClass().getResourceAsStream(s);
                BufferedReader br = new BufferedReader(new InputStreamReader(ins));

                numCols = Integer.parseInt(br.readLine());
                numRows = Integer.parseInt(br.readLine());

                map = new int[numRows][numCols];

                String delim = "\\s+";

                for(int row = 0; row < numRows; row++){

                    String line = br.readLine();
                    String[] tokens = line.split(delim);

                    for(int col = 0; col < numCols; col++){
                            map[row][col] = Integer.parseInt(tokens[col]);
                    }
                }

            }catch(Exception e){
                    e.printStackTrace();
            }
	}
	
	public int getTileSize() { return tileSize; }

	//From the ints passed within the parameter, this function retreives 
        // the type of tile which is at the row and col.
	public int getTileType(int row, int col){
            int rc = map[row][col];
            int r = rc / numTilesAcross;
            int c = rc % numTilesAcross;
            return tiles[r][c].getType();
	}
        
        //Draw the tiles which have been loaded.
	public void drawMap(Graphics2D g){
			
            for(int row = 0; row < numRowsToDraw; row++){

                if(row >= numRows) break;

                for(int col = 0; col < numColsToDraw; col++){

                    if(row >= numCols) break;

                    if(map[row][col] == 0) continue;

                    int rc = map[row][col];
                    int r = rc / numTilesAcross;
                    int c = rc % numTilesAcross;
                    g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);
                }
            }
	}

}