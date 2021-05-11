
import java.awt.Color;

public class ShooterEnemy extends GameObject{
	
	private int counter = 20;
	
	public ShooterEnemy(int x, int y, ID id, int w, int h, Color color, int speed) {
		super(x, y, id, w, h, color);
		this.speed = speed;
		velY = -(r.nextInt(speed)) - 3;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(onScreen() && counter % 50 == 0) {
				if((Player.X < x - 50 && Player.Y < y - 50) || (Player.X > x + 50 && Player.Y < y - 50) || Player.Y > y + 50) {
					shoot();
				} else counter = 49;
		}
		
		resetPositionAndSpeed(h, speed);
		counter++;
		
	}
	
	public boolean onScreen() {
		return x > 0 && x < Game.WIDTH - w && y > 0 && y < Game.HEIGHT;
	}
	
	public void shoot() {
		Game.handler.addObject(new Bullet(x, y, ID.Bullet, 12, 12, Color.black));
	}
}
