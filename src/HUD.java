
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
    points = 5000;
    Player.HEALTH = Game.clamp(Player.HEALTH, 0, 100);
    if (Player.HEALTH == 0) {
      Game.state = Game.STATE.EndMenu;
      Menu.y = 1480;
      Menu.started = false;
      Menu.restarted = true;
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
    g.fillRect(300, 680, 200, 16);
    g.setColor(Color.green);
    g.fillRect(300, 680, Player.HEALTH * 2, 16);
    g.setColor(Color.black);
    g.drawRect(300, 680, 200, 16);
    g.fillRect(520, 680, 20, 8);
    g.fillRect(535, 680, 5, 16);
    g.setFont(Menu.font30);
    g.setColor(Menu.myColor);
    g.drawString("" + Player.AMMO, 550, 695);
    g.drawString("" + points, 15, 690);
    g.fillRect(580, 10, 40, 40);
    g.setColor(Color.WHITE);
    g.fillRect(590, 20, 5, 20);
    g.fillRect(605, 20, 5, 20);
    //g.drawString("Score: " + score, 15, 690);

  }
}
