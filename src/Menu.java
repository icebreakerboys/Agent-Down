import java.awt.*;

public class Menu {

    public static void render(Graphics g) {
        if(Game.state == Game.STATE.StartMenu){
            Font font1 = new Font("Arial", Font.BOLD, 50);
            g.setColor(Color.GRAY);
            g.setFont(font1);
            g.drawString("AGENT DOWN", Window.WIDTH/4 - 20, 100);

        }
        if(Game.state == Game.STATE.EndMenu){

        }
        if(Game.state == Game.STATE.HelpMenu){

        }
        if(Game.state == Game.STATE.OptionsMenu){

        }
    }

    public static void pauseRender(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(Window.WIDTH/4, Window.HEIGHT/4, Window.WIDTH/2, Window.HEIGHT/2);
    }
}
