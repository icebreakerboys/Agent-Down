import java.awt.*;

public class JetStreams extends GameObject {

  public JetStreams() {
    super(Player.X, Player.Y - 50, ID.JetStream, 2, 50, Color.white);
  }

  public void tick() {
    if (Player.velY > 0) {
      h = 64;
    } else if (Player.velY < 0) {
      h = 30;
    } else h = 48;
    x = Player.X;
    y = Player.Y - h;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int) x, (int) y, w, h);
    g.fillRect((int) x + 46, (int) y, w, h);
  }
}
