
import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject{

	public Bullet(int x, int y, int tX, int tY, ID id) {
		super(x, y, id, 12, 12, Color.black);
		target(x, y, tX, tY);
	}

	public void tick() {
		x += velX;
		y += velY;
		collision();
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
	@Override
	public void collision(){
		for(int i = 0; i < Game.handler.object.size(); i++) {
			GameObject tempObject = Game.handler.object.get(i);
			if(tempObject.getId() == ID.BulletFriendly && id == ID.BulletEnemy && getBounds().intersects(tempObject.getBounds()))
				Game.handler.removeObject(this);
		}
	}
}
