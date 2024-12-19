package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	// We will place character in center of screen and scroll the background as he moves.
	// screenX and screenY do NOT change throughout the game(final)
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		// Half of the screen width and half the screen height so we can draw character in the center...
		// ...Subtract a half tile length to adjust for the drawing starting after the TOP LEFT CORNER of the image.
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		// args = (x, y, width, height)
		// We want the rectangle to be SMALLER than our character
//		solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		
		setDefaultValues();
		getPlayerImage();
		
//		System.out.println(worldX + solidArea.x);
//		System.out.println(worldX + solidArea.x + solidArea.width);
//		System.out.println(worldY + solidArea.y);
//		System.out.println(worldY + solidArea.y + solidArea.height);
	}
	
	public void setDefaultValues() {
		//Roughly the center of our map. The player's spawn location.
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
//		worldX = gp.tileSize * 0;
//		worldY = gp.tileSize * 0;
//		worldX = gp.tileSize * 49;
//		worldY = gp.tileSize * 49;
		speed = 4;
		direction = "down";
		
	}
	
	public void getPlayerImage() {
		
		// Load the images
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// update() is called 60 times per second
	public void update() {
		// spriteCounter only increases if we press a key, else we have a static image
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
				
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
				
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
				
			}
			
			
			// CHECK TILE COLLISION 
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			// Player image updates every 12 frames
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
	}
	
	public void draw(Graphics g2) {
		// Set color to use for drawing objects.
//		g2.setColor(Color.white);
//		
		// Draws a rectangle and fills it w/ the specified color.(x, y, width, height)
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		// Set ImageObserver to null
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
}
