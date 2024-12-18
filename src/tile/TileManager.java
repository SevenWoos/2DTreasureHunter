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
	Tile[] tile;
	
	int mapTileNum[][];
	
	
	
	public TileManager(GamePanel gp) {
		
		this.gp= gp;
		
		// An array of 10 tiles
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenCol];
		
		getTileImage();
		
		loadMap("/maps/map01.txt");
	}
	
	
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			
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
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				// We will read the text file in this loop
				
				// reads a single line of text and saves into a variable
				String line = br.readLine();
				 
				while(col < gp.maxScreenCol) {
					// Split line to get each number
					String numbers[] = line.split(" "); 
					// Convert from string to integer
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					
					col++;	
				}
				
				if(col == gp.maxScreenCol) {
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
		//g2.drawImage(tile[1].image, 0, 0, gp.tileSize, gp.tileSize, null);
		//g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
		
		// We can also create a map data with a text file
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenCol) {
			
			// Tile starts at (0, 0) in our map txt file. (If 0, will draw grass, 1 will draw wall, 2 will draw water)
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
		
	}
	
}
