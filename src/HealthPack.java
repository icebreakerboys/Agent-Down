import java.awt.*;

public class HealthPack extends GameObject {

  public HealthPack(int x, int y, int w, int h) {
    super(x, y, ID.HealthPack, w, h, Color.green);
    velY = -1;
  }

  public void tick() {
    y += velY;
    removeGameObject(true);
  }
}
