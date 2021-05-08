package realSkyDive.src.com.realSkyDive.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public abstract class GameObject {
	
	protected Random r = new Random();
	
	//variables
	protected int x, y;
	public static int PX, PY;
	protected int velX, velY;
	protected ID id;
	protected int w, h;
	protected int speed;
	protected Color color;
	
	//constructor
	public GameObject(int x, int y, ID id, int w, int h, Color color) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.w = w;
		this.h = h;
		this.color = color;
	}
	
	//tick
	public abstract void tick();
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	
	public void resetPositionAndSpeed(int h, int speed) {
		if(y <= -h) {
			y = Game.HEIGHT;
			x = r.nextInt(Game.WIDTH - w);
			velY = -(r.nextInt(speed)) - 3;
		}
	}
	public void removeGameObject() {
		if(x <= -w || x >= Game.WIDTH + w || y <= -h || y >= Game.HEIGHT + h)
			Game.handler.removeObject(this);
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
	}
	
	//getters and setters
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public int getVelX() {
		return velX;
	}
	public int getVelY() {
		return velY;
	}
}
