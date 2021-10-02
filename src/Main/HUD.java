package Main;

import GameObjects.Player.Player;
import Main.MenusAndInputs.Menu;
import java.awt.*;

public class HUD {
  private static int score = 0;
  private long counter = 0L;
  /**
   * Handles the Math of the Main.HUD
   */
  public void tick() {
    counter++;
    if(counter % 5 == 0 ){
      score++;
    }
  }

  public static int getScore() {
    return score;
  }

  public static void setScore(int x){
    score+=x;
  }

  /**
   * Handles the visual components of the Main.HUD
   *
   * @param g Graphics
   */
  public void render(Graphics g) {
    g.setColor(Color.darkGray);
    g.fillRect(254, 680, 200, 16);
    g.setColor(Color.green);
    g.fillRect(254, 680, Game.player.getHealth() * 2, 16);
    g.setColor(Color.black);
    g.drawRect(254, 680, 200, 16);
    g.fillRect(474, 680, 20, 8);
    g.fillRect(488, 680, 6, 16);
    g.setFont(Menu.font30);
    g.setColor(Menu.navyBlue);
    g.drawString("" + Player.getAmmo(), 504, 695);
    g.drawString("" + score, 64, 690);
  }
}
