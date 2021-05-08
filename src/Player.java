
import java.awt.Color;

public class Player extends GameObject{
	
	//constructor
	public Player(int x, int y, ID id, int w, int h, Color color) {
		super(x, y, id, w, h, color);
	}

	//math for player
	public void tick() {
		x += velX;
		y += velY;
		PX = x;
		PY = y;
		
		x = Game.clamp(x, 0, Game.WIDTH - 45);
		
		collision();
		
	}
	
	//Block Collision
	private void collision() {
		for(int i = 0; i < Game.handler.object.size(); i++) {
			
			GameObject tempObject = Game.handler.object.get(i);
			if(tempObject.getId() == ID.Enemy) {
				//collision code
				if(getBounds().intersects(tempObject.getBounds())) {
					//BUG 
					//This will take away more than 1 health
					//should have 100 hits we have like 20
					HUD.HEALTH -= 1;
				}
			}
			if(tempObject.getId() == ID.Bullet){
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 10;
					Game.handler.removeObject(tempObject);
					
				}
			}
		}
	}
}
