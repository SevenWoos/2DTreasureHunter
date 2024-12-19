package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	
	// Tile Array
	public Tile[] tile;
	
	public int mapTileNum[][];
	
	
	
	public TileManager(GamePanel gp) {
		
		this.gp= gp;
		
		// An array of 10 tiles
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		
		loadMap("/maps/world01.txt");
	}
	
	
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadMap(String filePath) {
		
		try {
			// Imports text file
			InputStream is = getClass().getResourceAsStream(filePath);
			// Reads content of text file
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				// We will read the text file in this loop
				
				// reads a single line of text and saves into a variable
				String line = br.readLine();
				 
				while(col < gp.maxWorldCol) {
					// Split line to get each number
					String numbers[] = line.split(" "); 
					// Convert from string to integer
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					
					col++;	
				}
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
			
		}
		catch(Exception e) {
			
		}
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			// Tile starts at (0, 0) in our map txt file. (If 0, will draw grass, 1 will draw wall, 2 will draw water)
			int tileNum = mapTileNum[worldCol][worldRow];
			
			// "worldX" and "worldY" = positions on the MAP.
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			// "screenX" and "screenY" = where on the screen we draw the world tiles.
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			
			// We want to draw only the tiles we CAN SEE. It is less efficient to draw tiles we cannot see.
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
		
			worldCol++;
			
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
	}
	
}
