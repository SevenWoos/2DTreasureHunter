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
	
	/* Modern PCs have much larger resolutions than the original NES, so 16x16 may look very small on a modern PC
	 * NES resolution was (256 x 224), so (16 x 16) was fine
	 * */
	
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
	
	// @ 60FPS, the screen updates itself 60 times per second; we perceive as movement/animation.
	// To enable this animation, we need to add a time component; use Thread class()
	// We will need "GamePanel" to implement "Runnable".
	Thread gameThread;

	@Override
	public void run() {
		// We need to override this method for "Runnable" interface
		 /* An object implementing interface "Runnable" is used to create a thread.
		  * Starting the thread causes the object's run() method to be called.
		 */
		
		// We will use this to create a game loop.
		
		
	}
	
}
