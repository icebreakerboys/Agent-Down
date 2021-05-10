package realSkyDive.src;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject{

	public Bullet(int x, int y, ID id, int w, int h, Color color) {
		super(x, y, id, w, h, color);
		targetPlayer(x, y);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		removeGameObject();
	}
	
	public void targetPlayer (int x, int y) {
		int xD = Player.PX - x;
		int yD = Player.PY - y;
		
		velY = ((7*yD)/(int) (Math.sqrt(xD*xD + yD*yD)));
		velX = ((7*xD)/(int) (Math.sqrt(xD*xD + yD*yD)));
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, w, h);
	}

}
