import java.awt.*;

public class HealthPack extends GameObject {
  public HealthPack(int x, int y, ID id, int w, int h, Color color) {
    super(x, y, id, w, h, color);
    velY = -1;
    markedForDelete = false;
  }

  public void tick() {
    y += velY;
    resetPositionAndSpeed(h, 1);
  }
}
