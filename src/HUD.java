
import java.awt.Color;
import java.awt.Graphics;

public class HUD {
  private int points = 0;
  private long counter = 0L;
  /**
   * this method is called repeatedly to update the HUD
   */
  public void tick() {
    counter++;
    if(counter%5==0){
      points++;
    }
    Player.HEALTH = Game.clamp(Player.HEALTH, 0, Player.HEALTH);
    if (Player.HEALTH == 0) {
      Game.running = false;
    }
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int x){
    points+=x;
  }

  /**
   * renders the hud and health of player
   *
   * @param g
   */
  public void render(Graphics g) {
    g.setColor(Color.darkGray);
    g.fillRect(15, 15, 200, 16);
    g.setColor(Color.green);
    g.fillRect(15, 15, Player.HEALTH * 2, 16);
    g.setColor(Color.black);
    g.drawRect(15, 15, 200, 16);
    g.drawString("Ammo: " + Player.AMMO, 500, 55);
    g.drawString("Points: " + points, 500, 80);

  }
}
