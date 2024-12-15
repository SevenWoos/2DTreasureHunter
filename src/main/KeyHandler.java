package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// KeyListener = listener interface for receiving keyboard events(keystrokes)
public class KeyHandler implements KeyListener {
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// getKeyCode() returns the integer keyCode associated w/ the key in this event.
		// (Returns the number of the key that was pressed.)
		int code = e.getKeyCode();
		
		// if user pressed the "w" key
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		
		// if user pressed the "s" key
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		
		// if user pressed the "a" key
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		
		// if user pressed the "d" key
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		// if key was released, then reverse boolean
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false ;
		}
		
	}

	
	
}
