
import java.awt.*;

public class ShooterEnemy extends GameObject {
  private int counter = 20;

  public ShooterEnemy(int x, int y, int speed, Image filePath1, Image filePath2) {
    super(x, y, ID.ShooterEnemy, 26, 26, Color.orange);
    this.speed = speed;
    velY = -(r.nextInt(speed)) - 3;
    int picSelect = r.nextInt(2);
    if (picSelect == 0) {
      image = filePath1;
    } else {
      image = filePath2;
    }
  }

	public boolean onScreen() {
		return x > 0 && x < Window.WIDTH - w && y > 0 && y < Window.HEIGHT;
	}
	
	public void shoot() {
        int xC = Player.X - (int) x;
        int yC = Player.Y - (int) y;
        double angle = Math.atan2(yC,xC);
		Game.handler.addObject(new Bullet((int) x + 13, (int) y + 13, Player.X, Player.Y, angle, ID.BulletEnemy));
	}

  public void tick() {
    x += velX;
    y += velY;
    collision();
    if (onScreen() && counter % 50 == 0) {
      if ((Player.X < x - 50 && Player.Y < y - 50) || (Player.X > x + 50 && Player.Y < y - 50) || Player.Y > y + 50) {
        shoot();
      } else counter = 49;
    }
   removeEnemy();
    counter++;
  }

//  @Override
//  public void render(Graphics g) {
//    g.drawImage(image, x, y, 90, 60, this);
//  }


}
