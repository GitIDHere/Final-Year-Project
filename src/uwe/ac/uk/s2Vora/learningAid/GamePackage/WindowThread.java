package uwe.ac.uk.s2Vora.learningAid.GamePackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class WindowThread extends JPanel implements Runnable, KeyListener{

	//I CANT RENAME THE WIDTH OR HEIGHT, USED IT TOO MANY TIMES i THINK
	public static final int WIDTH = 390;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	//Game Thread
	private Thread mainThread;
	private boolean running;
	private final int FPS = 60;
	private final long targetTime = 1000 / FPS;
	
	//Image
	private BufferedImage image;
	private Graphics2D graphics;
        
	//GameStateManager
	private GameStateManager gsm;
        
	public WindowThread(){
            setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
            setFocusable(true);
            addKeyListener(this);
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            graphics = image.createGraphics();
            gsm = GameStateManager.getgameStateManagerInstance();
	}

        //This is called when this class is instantiated.
        @Override
	public void addNotify(){
            super.addNotify();
            if(mainThread == null){
                mainThread = new Thread(this);
                running = true;
                mainThread.start();
            }
	}

	
        @Override
	public void run(){
		
		long start;
		long elapsed;
		long wait;
		
		while(running){
                    start = System.nanoTime();
                    update();
                    draw(graphics);
                    drawToScreen();

                    elapsed = System.nanoTime() - start;
                    wait = targetTime - elapsed / 1000000;

                    if(wait < 0) wait = 5;

                    try{
                        Thread.sleep(wait);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
		}
	}
	
	
	
	private void update(){
		gsm.update();
	}
	
	private void draw(Graphics2D graphics){
		gsm.draw(graphics);
	}
	
	private void drawToScreen(){
                Graphics g = getGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g.dispose();
	}
        
        @Override
	public void keyPressed(KeyEvent key){
            gsm.keyPressed(key.getKeyCode());
	}
	
        @Override
	public void keyReleased(KeyEvent key){
            gsm.keyReleased(key.getKeyCode());
	}

        @Override
        public void keyTyped(KeyEvent e) {}

}