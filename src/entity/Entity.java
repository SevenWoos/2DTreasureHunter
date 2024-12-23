package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// Stores variables that will be used in Player, Monster, and NPC classes.
public class Entity {

	public int worldX, worldY;
	public int speed;
	
	// BufferedImage describes an image with an accessible buffer of image data(used to store image files).
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	// We will use this class to set parts of our Player's character to be solid
	public Rectangle solidArea;
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}
