
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	/**
	 * moves player from moving after key is pressed
	 * @param e KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//test code
		//System.out.println(key);
		//Key Events for Player
		switch (key){
			case KeyEvent.VK_D:
			case  KeyEvent.VK_RIGHT: {
				Player.velX = 5;
				break;
			}
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT: {
				Player.velX = -5;
				break;
			}
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP: {
				Player.velY = -2;
				break;
			}
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN: {
				Player.velY = 2;
				break;
			}



			//ends game
			case KeyEvent.VK_ESCAPE:
				System.exit(1);
				break;
		}

	}

	/**
	 * Stops player from moving after key is released
	 * @param e KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		//Key Events for Player
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_A || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT)
			Player.velX = 0;
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_S || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN)
			Player.velY = 0;
	}
}
