package realSkyDive.src;

import java.awt.*;

/**
 * Player class
 */
public class Player extends GameObject{

	public static int PX, PY;
	private Boolean justHit = false;
	private int counter = 0;

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
		x = Game.clamp(x, 0, Game.WIDTH - 45);
		//y bounds for window
		y = Game.clamp(y, 0, Game.HEIGHT/2 - 45);

		if(!justHit) {
			collision();
		} else {
			counter++;
			if(counter % 50 == 0){
				counter = 0;
				justHit = false;
			}
		}
	}

	/**
	 * Render controls the visual components of the player
	 * @param g Graphics
	 */
	@Override
	public void render(Graphics g){
		if(justHit){
			g.setColor(color);
			if(counter % 5 != 0)
				g.fillRect(x, y, w, h);
		} else {
			g.setColor(color);
			g.fillRect(x, y, w, h);
		}
	}

	/**
	 * Handles player collision with
	 * enemies
	 */
	private void collision(){
		for(int i = 0; i < Game.handler.object.size(); i++) {
			
			GameObject tempObject = Game.handler.object.get(i);
			//red enemy math
			if(tempObject.getId() == ID.Enemy) {
				//collision code
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 10;
					justHit = true;
				}
			}
			//shooter bullet math
			if(tempObject.getId() == ID.Bullet){
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 10;
					Game.handler.removeObject(tempObject);
					justHit = true;
				}
			}
		}
	}
}
