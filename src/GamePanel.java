import javax.swing.JPanel;

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
	final int tileSize = originalTileSize * scale;
	
	// How many tiles do we want to display on the screen horizontally and vertically? (16x12) = (4x3) ratio.
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels(48x16) horizontal
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels(48x12) vertical
	
	
	// FPS
	int FPS = 60;
	
	
	// Instantiate the KeyHandler
	KeyHandler keyH = new KeyHandler();
	
	
	Thread gameThread;
	
	// Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
	
	
	// Constructor for GamePanel
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		
		/* setDoubleBuffered() -> if true, all the drawing from this component will be done in an offscreen painting buffer
		 * Enabling can improve game's rendering performance.
		 */
		this.setDoubleBuffered(true);
		
		// add KeyListener
		this.addKeyListener(keyH);
		
		// GamePanel "focused" to receive key input
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		// auto calls the run() method
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1000000000/ FPS;
		// When we hit "nextDrawTime", draw the screen again.
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
//			System.out.println("The game loop is running.");
			
			
			 long currentTime = System.nanoTime();
			 System.out.println("Current time: " + currentTime);
			 
			
			update();
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();

				remainingTime = remainingTime / 1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				// Set NEW "nextDrawTime"
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	// Update player position
	public void update() {

		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		else if(keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		else if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
			
	}
	
	
	// Java built-in method for drawing on JPanel
	// Use the "Graphics" class -. provides functions to draw objects on the screen. Like a pencil or paint brush
	public void paintComponent(Graphics g) {
		// Whenever we use paintComponent(), we NEED to type super.paintComponent().
		// "super" means the parent class(JPanel) of this class(GamePanel)
		super.paintComponent(g);
		
		/* Graphics2D extends Graphics
		 * Convert Graphics to Graphics2D(more functionality)
		 */
		Graphics g2 = (Graphics2D)g;
		
		// Set color to use for drawing objects.
		g2.setColor(Color.white);
		
		// Draws a rectangle and fills it w/ the specified color.(x, y, width, height)
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		// When done drawing, use dispose() to release any system resources in use for this graphic context.
		g2.dispose();
		
	}
	
}
