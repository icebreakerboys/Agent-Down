package realSkyDive.src;

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
		for(int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject = Game.handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//Key Events for Player
				if(key == KeyEvent.VK_D) 
					tempObject.setVelX(5);
				if(key == KeyEvent.VK_A) 
					tempObject.setVelX(-5);
				if(key == KeyEvent.VK_W)
					tempObject.setVelY(-5);
				if(key == KeyEvent.VK_S)
					tempObject.setVelY(5);
				//Up and down keys
				if(key == KeyEvent.VK_RIGHT)
					tempObject.setVelX(5);
				if(key == KeyEvent.VK_LEFT)
					tempObject.setVelX(-5);
				if(key == KeyEvent.VK_UP)
					tempObject.setVelY(-5);
				if(key == KeyEvent.VK_DOWN)
					tempObject.setVelY(5);
			}
		}
		//ends game
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	/**
	 * Stops player from moving after key is released
	 * @param e KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject =Game.handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//Key Events for Player
				if(key == KeyEvent.VK_D) 
					tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) 
					tempObject.setVelX(0);
				if(key == KeyEvent.VK_W)
					tempObject.setVelY(0);
				if(key == KeyEvent.VK_S)
					tempObject.setVelY(0);
				//Up and down keys
				if(key == KeyEvent.VK_RIGHT)
					tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT)
					tempObject.setVelX(0);
				if(key == KeyEvent.VK_UP)
					tempObject.setVelY(0);
				if(key == KeyEvent.VK_DOWN)
					tempObject.setVelY(0);
			}
		}
	}
}
