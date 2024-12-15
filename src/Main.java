import javax.swing.JFrame;

public class Main {
	/* In 2D games, once you start the program, the program keeps running. 
	 * It doesn't stop unless we do something special, like closing the game.*/
	

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Cannot resize window
		window.setResizable(false);
		window.setTitle("2D Adventure");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		// Causes window to be sized to fit the preferred size + layouts of its subcomponent(GamePanel)
		window.pack();
		
		// Window will be displayed in center of screen if "null"
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();

	}

}
