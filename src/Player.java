
import java.awt.*;
import java.beans.ConstructorProperties;

/**
 * Player class
 */
public class Player {

	public static int X, Y;
	public static Boolean hasGun = true;

	private static Boolean justHit = false;
	private static int counter = 0;
	public static int velX = 0, velY = 0;
	private static int w, h;
	private static Color color;

	//constructor
	public Player(int x, int y, int w, int h, Color color) {
		X = x;
		Y = y;
		Player.w = w;
		Player.h = h;
		Player.color = color;
	}

	/**
	 * Tick class controls usr movement
	 * and keeps player within window bounds
	 */
	public static void tick() {
		X += velX;
		Y += velY;
		//x bounds for window
		X = Game.clamp(X, 0, Game.WIDTH - 45);
		//y bounds for window
		Y = Game.clamp(Y, 0, Game.HEIGHT/2 - 45);

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

	public static Rectangle getBounds() {
		return new Rectangle(X, Y, w, h);
	}

	/**
	 * Render controls the visual components of the player
	 * @param g Graphics
	 */
	public static void render(Graphics g){
		if(justHit){
			g.setColor(color);
			if(counter % 5 != 0)
				g.fillRect(X, Y, w, h);
		} else {
			g.setColor(color);
			g.fillRect(X, Y, w, h);
		}
	}

	/**
	 * Handles player collision with
	 * enemies
	 */
	private static void collision(){
		for(int i = 0; i < Game.handler.object.size(); i++) {
			
			GameObject tempObject = Game.handler.object.get(i);
			//enemy collision
			if(tempObject.getId() == ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 1;
					justHit = true;
				}
			}
			//bullet collision
			if(tempObject.getId() == ID.Bullet){
				if(getBounds().intersects(tempObject.getBounds())) {
					if(!tempObject.getIsFriendly()) {
						HUD.HEALTH -= 1;
						Game.handler.removeObject(tempObject);
						justHit = true;
					}
				}
			}
			//gunItem collision
			if(tempObject.getId() == ID.GunItem){
				if(getBounds().intersects(tempObject.getBounds())) {
					Game.handler.removeObject(tempObject);
					hasGun = true;
				}
			}
		}
	}

	public static void shoot(int x, int y, boolean isFriendly){
		Game.handler.addObject(new Bullet(Player.X, Player.Y, x, y, true, ID.Bullet, 12, 12, Color.black));
	}

}
