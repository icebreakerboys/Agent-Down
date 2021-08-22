import javax.swing.*;
import java.awt.*;

public class Background extends Canvas {
  private static int Y = 0, Y2 = 1472, yVel = 3;
  public static boolean isPlaying = false;
  int difference = 0;

  public void tick() {
    Y -= yVel;
    Y2 -= yVel;
    if (Y <= -1472 || Y2 <= -1472) {
      resetYValues();
    }
    if(isPlaying){
      if(difference <= 47)
        difference++;
    } else if(difference >= 0){
      difference = 0;
    }
  }

  private void resetYValues() {
    if (Y < Y2) {
      Y = Y2 + 1472;
    } else {
      Y2 = Y + 1472;
    }
  }

  public void render(Graphics g) {
    g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/background.png")).getImage(), 0, Y, 640, 1472, this);
    g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/background.png")).getImage(), 0, Y2, 640, 1472, this);
    g.setColor(Menu.myColorSTP);
    g.fillRect(-48 + difference, 0, 48, 740);
    g.fillRect(624 - difference, 0, 48, 740);
    //g.setColor(Color.black);
    //g.fillRect(626, 0, 48, 48);
  }
}
