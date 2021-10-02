package GameObjects.Player;

import GameObjects.GameObject;
import GameObjects.ID;

import java.awt.*;

public class JetStreams extends GameObject {

  public JetStreams() {
    super(ID.JetStream, Color.white, true);
    x = Player.getXPos();
    y = Player.getYPos() - 48;
    w = 2;
    h = 48;
  }

  public void tick() {
    if (Player.getVelY() > 0) {
      h = 64;
    } else if (Player.getVelY() < 0) {
      h = 32;
    } else h = 48;
    x = Player.getXPos();
    y = Player.getYPos() - h;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int) x, (int) y, w, h);
    g.fillRect((int) x + 46, (int) y, w, h);
  }
}
