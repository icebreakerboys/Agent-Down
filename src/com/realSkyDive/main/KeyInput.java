package realSkyDive.src.com.realSkyDive.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	//constructor
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	//allows for keys to do things
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//test code
		//System.out.println(key);
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//Key Events for Player
				if(key == KeyEvent.VK_D) 
					tempObject.setVelX(5);
				if(key == KeyEvent.VK_A) 
					tempObject.setVelX(-5);
			}
		}
		//ends game
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	//stops keys from doing things
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				//Key Events for Player
				if(key == KeyEvent.VK_D) 
					tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) 
					tempObject.setVelX(0);
			}
		}
	}
}
