
import java.awt.Color;

public class Enemy extends GameObject {
  //constructor
  public Enemy(int x, int y, int speed) {
    super(x, y, ID.Enemy, 16, 16, Color.red);
    this.speed = speed;
    velY = -(r.nextInt(speed)) - 3;
  }

  //enemy math
  public void tick() {
    x += velX;
    y += velY;
    collision();
    removeGameObject(true);
  }
}
