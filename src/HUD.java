import java.awt.*;

public class HUD {
  public static int points = 0;
  public static int score = 0;
  private long counter = 0L;
  /**
   * Handles the Math of the HUD
   */
  public void tick() {
    counter++;
    if(counter % 5 == 0 ){
      score++;
      points++;
    }
    //points = 5000;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int x){
    points+=x;
  }

  /**
   * Handles the visual components of the HUD
   *
   * @param g Graphics
   */
  public void render(Graphics g) {
    g.setColor(Color.darkGray);
    g.fillRect(254, 680, 200, 16);
    g.setColor(Color.green);
    g.fillRect(254, 680, Player.HEALTH * 2, 16);
    g.setColor(Color.black);
    g.drawRect(254, 680, 200, 16);
    g.fillRect(474, 680, 20, 8);
    g.fillRect(488, 680, 6, 16);
    g.setFont(Menu.font30);
    g.setColor(Menu.myColor);
    g.drawString("" + Player.AMMO, 504, 695);
    g.drawString("" + points, 64, 690);
    g.fillRect(580, 4, 40, 40);
    g.setColor(Color.WHITE);
    g.fillRect(590, 14, 6, 20);
    g.fillRect(604, 14, 6, 20);
    //g.drawString("Score: " + score, 15, 690);
  }
}
