package realSkyDive.src;

import java.awt.Color;

/**
 * Player class
 */
public class Player extends GameObject{

	//constructor
	public Player(int x, int y, ID id, int w, int h, Color color) {
		super(x, y, id, w, h, color);
	}

	/**
	 * Tick class controls usr movement
	 * and keeps player within window bounds
	 */
	public void tick() {
		x += velX;
		y += velY;
		PX = x;
		PY = y;
		//x bounds for window
		x = Game.clamp(x, 0, Game.WIDTH-30 );
		//y bounds for window
		y = Game.clamp(y, 0, Game.HEIGHT-60);
		
		collision();
		
	}

	/**
	 * Handles player collision with
	 * red enemy blocks
	 */
	private void collision(){
		for(int i = 0; i < Game.handler.object.size(); i++) {
			
			GameObject tempObject = Game.handler.object.get(i);
			//red enemy math
			if(tempObject.getId() == ID.Enemy) {
				//collision code
				if(getBounds().intersects(tempObject.getBounds())) {
					//FIXME: This bug is happening because your game loop is going too
					// Fast so its calling this collision method several times each time
					// The player hits the red block, taking away like 8 hitpoints at a time
					// One way you could fix this is to either add in a delay every time the player hits
					// a red block to prevent this method from being called again, or you can change how fast the game loop goes
					// Another way you can fix this is to rewrite this method into the enemy class instead of here
					// that way you can control its behavior better and allow it to only hit the player once
					// check the Game.java file to see the problem area
					HUD.HEALTH -= 1;

				}
			}
			//shooter bullet math
			if(tempObject.getId() == ID.Bullet){
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 10;
					Game.handler.removeObject(tempObject);
					
				}
			}
		}
	}
}
