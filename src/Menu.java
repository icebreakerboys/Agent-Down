import javax.swing.*;
import java.awt.*;
import java.awt.Window;

public class Menu extends Canvas{

    public static final Color myColor = new Color(58, 68, 102, 255);

    public void render(Graphics g) {
        Color myColorSTP = new Color(58, 68, 102, 200);
        Color myColorTP = new Color(58, 68, 102, 150);
        Font font40 = new Font("Helvetica", Font.PLAIN, 40);
        Font font50 = new Font("Helvetica", Font.PLAIN, 50);
        if (Game.state == Game.STATE.StartMenu) {
            g.setColor(myColorSTP);
            //g.setFont(font1);
            //g.drawString("AGENT DOWN", Window.WIDTH/4 - 20, 100);
            //g.drawImage(new ImageIcon("Agent.png").getImage(), 100, 100, 200, 200, this);
            //g.drawImage(new ImageIcon("Down.png").getImage(), 350, 100, 200, 200, this);
            g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/Agent Down.png")).getImage(), 75, 75, 490, 189, this);
            g.fillRect(150, 270, 300, 100);
            g.fillRect(150, 410, 300, 100);
            g.fillRect(150, 550 , 300, 100);
            g.setColor(Color.WHITE);
            g.setFont(font50);
            g.drawString("Play!", 240, 335);
            g.drawString("Options", 220, 475);
            g.drawString("Help", 245, 615);
        }
        if (Game.state == Game.STATE.EndMenu) {
            g.setColor(myColorSTP);
            g.fillRect(100, 100, 425, 400);
            g.fillRect(150, 550, 300, 100);
            g.setColor(Color.WHITE);
            g.setFont(font50);
            g.drawString("Game Over!", 175, 200);
            g.drawString("Score:", 250, 300);
            g.drawString("" + HUD.points, 200, 400);
            g.setFont(font40);
            g.drawString("Back to Start", 190, 615);

        }
        if (Game.state == Game.STATE.HelpMenu) {
            g.setColor(myColorSTP);
            g.fillRect(150, 550, 300, 100);
            g.fillRect(100, 100, 425, 400);
            g.setColor(Color.white);
            g.setFont(font40);
            g.drawString("Try to avoid the bullets", 110,150);
            g.drawString("and shrapnel, while", 140, 205);
            g.drawString("falling from the sky.", 140, 260);
            g.drawString("Aliens won't shoot you", 110, 315);
            g.drawString("if you're right above", 135, 370);
            g.drawString("them. Good Luck!", 150, 425);
            g.drawString("Press P to Pause.", 150, 480);
            g.drawString("Back to Start", 190, 615);

        }
        if (Game.state == Game.STATE.OptionsMenu) {
            g.setColor(myColorSTP);
            g.fillRect(150, 550, 300, 100);
            g.setColor(Color.WHITE);
            g.setFont(font40);
            g.drawString("Back to Start", 190, 615);
        }
        if(Game.state == Game.STATE.PauseMenu){
            g.setColor(myColorTP);
            g.fillRect(100, 50, 440, 559);
            g.setColor(myColor);
            g.fillRect(400, 128, 120, 100);
            g.fillRect(400, 250, 120, 100);
            g.fillRect(400, 372, 120, 100);
            g.fillRect(400, 494, 120, 100);
            g.setColor(Color.white);
            g.setFont(font50);
            g.drawString("Paused", 230, 95);
            g.setFont(new Font("Helvetica", Font.PLAIN, 30));
            g.drawString("Current Score: " + HUD.score, 110, 125);
            g.drawString("Shotgun", 195, 180);
            g.drawString("Parachute Bounce", 130, 312);
            g.drawString("Better HealthPacks", 125, 434);
            g.drawString("Shock Resistance", 130, 556);





        }
    }

    public void tick(){
    }

}
