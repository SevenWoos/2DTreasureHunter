package main;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

//GamePanel works as a game screen.

//GamePanel = essentially a JPanel instance w/ extra components we added

public class GamePanel extends JPanel implements Runnable {
	//SCREEN SETTINGS
	
	// 16x16 tile (default size of the player character, NPCs, map tiles, etc).
	final int originalTileSize = 16;
	
	
	// We can scale our 16x16 tile by 3 -> (16x3 = 48)
	final int scale = 3;
	
	// 48x48 tile(single tile is 48 pixels)
	public final int tileSize = originalTileSize * scale;
	
	// How many tiles do we want to display on the screen horizontally and vertically? (16x12) = (4x3) ratio.
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels(48x16) horizontal
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels(48x12) vertical
	
	
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	// FPS
	int FPS = 60;
	
	
	// Instantiate TileManager
	TileManager tileM = new TileManager(this);
	
	// Instantiate the KeyHandler
	KeyHandler keyH = new KeyHandler();
	
	// Instantiate gameThread
	Thread gameThread;
	
	// Instantiate CollisionChecker
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	// Instantiate AssetSetter
	public AssetSetter aSetter = new AssetSetter(this);
	
	// Instantiate Player
	public Player player = new Player(this, keyH);
	
	// Instantiate SuperObject array
	public SuperObject obj[] = new SuperObject[10];
	
	
	// Constructor for GamePanel
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		
		/* setDoubleBuffered() -> if true, all the drawing from this component will be done in an offscreen painting buffer.
		 * Enabling can improve game's rendering performance.
		 */
		this.setDoubleBuffered(true);
		
		// add KeyListener
		this.addKeyListener(keyH);
		
		// GamePanel "focused" to receive key input
		this.setFocusable(true);
		
	}
	
	public void setUpGame() {
		aSetter.setObject();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		// auto calls the run() method
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		
		while(gameThread != null) {
			
			// Check the current time
			currentTime = System.nanoTime();
			
			// Subtract "lastTime" from "currentTime" to get the time elapsed.
			// We then divide the time elapsed by drawInterval
			// Increase delta by this amount
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			// When timer reaches 1 second(1 billion nanoseconds)
			if(timer >= 1000000000) {
//				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
					
			
		}
	}
	
	// Update player position
	public void update() {
		// Call Player update method
		player.update();	
	}
	
	
	// Java built-in method for drawing on JPanel
	// Use the "Graphics" class -> provides functions to draw objects on the screen. Like a pencil or paint brush
	public void paintComponent(Graphics g) {
		// Whenever we use paintComponent(), we NEED to type super.paintComponent().
		super.paintComponent(g);
		
		Graphics g2 = (Graphics2D)g;
		
		// Call Tile draw method. Draw tiles BEFORE player
		tileM.draw((Graphics2D) g2);
		
		//OBJECT
		// Loop through our object array
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw((Graphics2D) g2, this);
			}
		}
		
		// Call Player draw method
		player.draw(g2);
		
		// When done drawing, use dispose() to release any system resources in use for this graphic context.
		g2.dispose();
		
	}
	
}
