package realSkyDive.src.com.realSkyDive.main;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject{

	public Bullet(int x, int y, ID id, int w, int h, Color color) {
		super(x, y, id, w, h, color);
		pT(x, y);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		removeGameObject();
	}
	
	public void pT(int x, int y) {
		int xD = PX - x;
		int yD = PY - y;
		
		velY = ((yD)/50);
		velX = ((xD)/50);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, w, h);
	}

}
