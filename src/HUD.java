
import java.awt.*;

public class HUD {
  public static int points = 0;
  public static int score = 0;
  private long counter = 0L;
  /**
   * this method is called repeatedly to update the HUD
   */
  public void tick() {
    counter++;
    if(counter % 5 == 0 ){
      score++;
      points++;
    }
    Player.HEALTH = Game.clamp(Player.HEALTH, 0, 100);
    if (Player.HEALTH == 0) {
      Game.state = Game.STATE.EndMenu;
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
    g.setFont(new Font("Helvetica", Font.PLAIN, 30));
    g.setColor(Menu.myColor);
    g.drawString("Ammo: " + Player.AMMO, 15, 660);
    g.drawString("Points: " + points, 15, 690);
    //g.drawString("Score: " + score, 15, 690);

  }
}
