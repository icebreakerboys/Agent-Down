package GameObjects.Player;

import GameObjects.GameObject;
import GameObjects.ID;
import Main.Game;

import java.awt.*;

public class JetStreams extends GameObject {

  public JetStreams() {
    super(ID.JetStream, Color.white, true);
    x = Game.player.getX();
    y = Game.player.getY() - 48;
    w = 2;
    h = 48;
  }

  public void tick() {
    if (Game.player.getVelY() > 0) {
      h = 64;
    } else if (Game.player.getVelY() < 0) {
      h = 32;
    } else h = 48;
    x = Game.player.getX();
    y = Game.player.getY() - h;
  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    g.fillRect((int) x, (int) y, w, h);
    g.fillRect((int) x + 46, (int) y, w, h);
  }
}
