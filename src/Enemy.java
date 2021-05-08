package realSkyDive.src;

import java.awt.Color;

public class Enemy extends GameObject{
	
	//constructor
	public Enemy(int x, int y, ID id, int w, int h, Color color, int speed) {
		super(x, y, id, w, h, color);
		this.speed = speed;
		velY = -(r.nextInt(speed)) - 3;
	}
	
	//enemy math
	public void tick() {
		x += velX;
		y += velY;
		
		resetPositionAndSpeed(h, speed);
	}
}
