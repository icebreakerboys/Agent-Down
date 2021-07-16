import javax.swing.*;
import java.awt.*;
import java.awt.Window;

public class Menu extends Canvas{

    public void render(Graphics g) {
        if (Game.state == Game.STATE.StartMenu) {
            Font font1 = new Font("Arial", Font.BOLD, 50);
            g.setColor(Color.gray);
            //g.setFont(font1);
            //g.drawString("AGENT DOWN", Window.WIDTH/4 - 20, 100);
            //g.drawImage(new ImageIcon("Agent.png").getImage(), 100, 100, 200, 200, this);
            //g.drawImage(new ImageIcon("Down.png").getImage(), 350, 100, 200, 200, this);
            g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/Agent Down.png")).getImage(), 75, 75, 490, 189, this);
            g.fillRect(150, 270, 300, 100);
            g.fillRect(150, 410, 300, 100);
            g.fillRect(150, 550 , 300, 100);


        }
        if (Game.state == Game.STATE.EndMenu) {

        }
        if (Game.state == Game.STATE.HelpMenu) {
            g.setColor(Color.gray);
            g.fillRect(150, 550, 300, 100);

        }
        if (Game.state == Game.STATE.OptionsMenu) {
            g.setColor(Color.gray);
            g.fillRect(150, 550, 300, 100);

        }
    }

  public void pauseRender(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(Window.WIDTH / 4, Window.HEIGHT / 4, Window.WIDTH / 2, Window.HEIGHT / 2);
  }
  public void tick(){

  }
}
