package Main;
import Main.MenusAndInputs.Menu;
import javax.swing.*;
import java.awt.*;

public class Background extends Canvas {

  private static int Y = 0, Y2 = 1472, yVel = 3;
  int difference = 0;
  /**
   * Handles the movement of the Main.Background
   */
  public void tick() {
    Y -= yVel;
    Y2 -= yVel;
    if (Y <= -1472 || Y2 <= -1472) {
      resetYValues();
    }
    if(Game.isPlaying){
      if(difference <= 47)
        difference++;
    } else if(difference >= 0){
      difference = 0;
    }
  }
  /**
   * Resets the y values to keep the background on the Screen
   */
  private void resetYValues() {
    if (Y < Y2) {
      Y = Y2 + 1472;
    } else {
      Y2 = Y + 1472;
    }
  }
  /**
   * Handles the visual components of the Main.Background
   *
   * @param g Graphics
   */
  public void render(Graphics g) {
    g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/background.png")).getImage(), 0, Y, 640, 1472, this);
    g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/background.png")).getImage(), 0, Y2, 640, 1472, this);
    g.setColor(Menu.nvyBluSemiTransprnt);
    g.fillRect(-48 + difference, 0, 48, 740);
    g.fillRect(624 - difference, 0, 48, 740);
    //g.setColor(Color.black);
    //g.fillRect(626, 0, 48, 48);
  }
}
