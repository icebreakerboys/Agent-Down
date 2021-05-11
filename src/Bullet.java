
import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject{

	public Bullet(int x, int y, int tX, int tY, boolean isFriendly, ID id, int w, int h, Color color) {
		super(x, y, id, w, h, color);
		target(x, y, tX, tY);
		super.isFriendly = isFriendly;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		removeGameObject();
	}
	
	public void target (int x, int y, int tX, int tY) {
		int xD = tX - x;
		int yD = tY - y;
		
		velY = ((7*yD)/(int) (Math.sqrt(xD*xD + yD*yD)));
		velX = ((7*xD)/(int) (Math.sqrt(xD*xD + yD*yD)));
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, w, h);
	}
	public boolean getIsFriendly(){
		return isFriendly;
	}

}
