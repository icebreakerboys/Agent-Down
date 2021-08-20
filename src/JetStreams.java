import java.awt.*;

public class JetStreams extends GameObject {

  public JetStreams() {
    super(Player.X, Player.Y - 20, ID.JetStream, 2, 50, Color.white);
  }

  public void tick() {
    int yAdjust = 20;
    if (Player.velY > 0) {
      h = 70;
      yAdjust = 40;
    } else if (Player.velY < 0) {
      h = 30;
      yAdjust = 0;
    } else h = 50;
    x = Player.X;
    y = Player.Y - yAdjust;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int) x, (int) y, w, h);
    g.fillRect((int) x + 67, (int) y, w, h);
  }
}
