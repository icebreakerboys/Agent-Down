
import javax.swing.*;
import java.awt.*;
import java.net.URL;

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
		Game.handler.addObject(new Bullet(x + 13, y + 13, Player.X + 24, Player.Y + 24, ID.BulletEnemy));
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

  @Override
  public void render(Graphics g) {
    g.drawImage(image, x, y, 90, 60, this);
  }


}
