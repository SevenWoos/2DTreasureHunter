package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	// Each object(key, chest, door, etc) has its own default solidArea
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		// "screenX" and "screenY" = where on the screen we draw the object.
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		
		// We want to draw only the tiles we CAN SEE. It is less efficient to draw tiles we cannot see.
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	
	}
}
