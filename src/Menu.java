import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Menu extends Canvas{

    public static final Color myColor = new Color(58, 68, 102, 255);
    public static final Color myColorSTP = new Color(58, 68, 102, 200);
    Color myColorTP = new Color(58, 68, 102, 150);
    Color myColor2 = new Color(90, 105, 136);
    public static Font font30 = new Font("Helvetica", Font.PLAIN, 30);
    public static Font font40 = new Font("Helvetica", Font.PLAIN, 40);
    Font font50 = new Font("Helvetica", Font.PLAIN, 50);
    public static int x = 0;
    public static int y = 940;
    public static boolean restarted = false;
    public static boolean started = false;
    public static boolean unPaused = false;
    private static int seconds = 4;
    private static int counter = 0;

    public void render(Graphics g) {

        if (Game.state == Game.STATE.StartMenu || Game.state == Game.STATE.OptionsMenu
                || Game.state == Game.STATE.HelpMenu || Game.state == Game.STATE.EndMenu) {
            renderStartMenu(g);
            renderOptionsMenu(g);
            renderHelpMenu(g);
            if(restarted)
                renderEndMenu(g);
        }
        if(Game.state == Game.STATE.PauseMenu){
            if(unPaused){
                g.setFont(font50);
                g.setColor(myColorSTP);
                g.drawString("" + seconds, 320, 370);
            } else {
                renderPauseMenu(g);
            }
        }
    }

    private void renderStartMenu(Graphics g){
        g.setColor(myColorSTP);
        g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("images/Agent Down.png")).getImage(), x + 75, y + 75, 490, 189, this);
        g.fillRect(x + 150, y + 270, 300, 100);
        g.fillRect(x + 150, y + 410, 300, 100);
        g.fillRect(x + 150, y + 550 , 300, 100);
        g.setColor(Color.WHITE);
        g.setFont(font50);
        g.drawString("Play!", x + 240, y + 335);
        g.drawString("Options", x + 220, y + 475);
        g.drawString("Help", x + 245, y + 615);
    }
    private void renderOptionsMenu(Graphics g){
        g.setColor(myColorSTP);
        g.fillRect(x + 150 - 640, y + 550, 300, 100);
        g.setColor(Color.WHITE);
        g.setFont(font40);
        g.drawString("Back to Start", x + 190 - 640, y + 615);
    }
    private void renderHelpMenu(Graphics g){
        g.setColor(myColorSTP);
        g.fillRect(x + 150 + 640, y + 550, 300, 100);
        g.fillRect(x + 100 + 640, y + 100, 425, 400);
        g.setColor(Color.white);
        g.setFont(font40);
        g.drawString("Try to avoid the bullets", x + 110 + 640, y + 150);
        g.drawString("and shrapnel, while", x + 140 + 640, y + 205);
        g.drawString("falling from the sky.", x + 140 + 640, y + 260);
        g.drawString("Aliens won't shoot you", x + 110 + 640, y + 315);
        g.drawString("if you're right above", x + 135 + 640, y + 370);
        g.drawString("them. Good Luck!", x + 150 + 640, y + 425);
        g.drawString("Press P to Pause.", x + 150 + 640, y + 480);
        g.drawString("Back to Start", x + 190 + 640, y + 615);
    }
    private void renderEndMenu(Graphics g){
        g.setColor(myColorSTP);
        g.fillRect(x + 100, y + 100 - 740, 425, 400);
        g.fillRect(x + 150, y + 550 - 740, 300, 100);
        g.setColor(Color.WHITE);
        g.setFont(font50);
        g.drawString("Game Over!", x + 175, y + 200 - 740);
        g.drawString("Score:", x + 250, y + 300 - 740);
        g.drawString("" + HUD.points, x + 200, y + 400 - 740);
        g.setFont(font40);
        g.drawString("Back to Start", x + 190, y + 615 - 740);
    }
    private void renderPauseMenu(Graphics g){
        g.setColor(myColorTP);
        g.fillRect(0, 0, 640, 740);
        if(Player.hasShotgun){
            g.setColor(myColor2);
        } else {
            g.setColor(myColor);
        }
        g.fillRect(400, 128, 120, 100);
        if(Player.hasSpeedBuff) {
            g.setColor(myColor2);
        } else {
            g.setColor(myColor);
        }
        g.fillRect(400, 250, 120, 100);
        if(Player.hasHealthBuff) {
            g.setColor(myColor2);
        } else {
            g.setColor(myColor);
        }
        g.fillRect(400, 372, 120, 100);
        if(Player.hasResistanceBuff) {
            g.setColor(myColor2);
        } else {
            g.setColor(myColor);
        }
        g.fillRect(400, 494, 120, 100);
        g.setColor(myColor);
        g.fillRect(580, 4, 40, 40);
        g.setColor(Color.white);
        g.setFont(font50);
        g.drawString("Paused", 230, 95);
        g.setFont(font30);
        g.drawString("Current Score: " + HUD.score, 110, 125);
        g.drawString("Shotgun", 195, 180);
        g.drawString("Parachute Bounce", 130, 312);
        g.drawString("Better HealthPacks", 125, 434);
        g.drawString("Shock Resistance", 130, 556);
        g.drawString("5000", 427, 173);
        g.drawString("points", 420, 203);
        g.drawString("5000", 427, 295);
        g.drawString("points", 420, 325);
        g.drawString("5000", 427, 417);
        g.drawString("points", 420, 447);
        g.drawString("5000", 427, 539);
        g.drawString("points", 420, 569);
        g.setFont(font40);
        g.drawString("x", 590, 34);
    }

    public void tick(){
        if(Game.state == Game.STATE.StartMenu || Game.state == Game.STATE.OptionsMenu || Game.state == Game.STATE.HelpMenu){
            if(y > 0)
                y -= 2;
        }
        if(Game.state == Game.STATE.StartMenu){
            if(x > 0)
                x -= 2;
            if(x < 0)
                x += 2;
        }
        if(Game.state == Game.STATE.OptionsMenu && x < 640) {
            x += 2;
        }
        if(Game.state == Game.STATE.HelpMenu && x > -640) {
            x -= 2;
        }
        if(Game.state == Game.STATE.EndMenu && y > 740)
            y -= 2;
        if(started)
            y -= 2;
        if(unPaused){
            if(counter % 60 == 0)
                seconds--;
            counter++;
        }
    }

    public static void startGame() {
        java.util.Timer timer = new Timer();
        TimerTask startTransition = new TimerTask() {
            @Override
            public void run() {
                Game.state = Game.STATE.PlayScreen;
                Game.challengeVar = 1;
                Player.HEALTH = 100;
                Player.AMMO = 0;
                Player.X = Window.WIDTH/2;
                Player.Y = 100;
                HUD.points = 0;
                HUD.score = 0;
                while(Game.handler.object.size() != 0){
                    GameObject tempObject = Game.handler.object.get(0);
                    System.out.println(tempObject.getId());
                    Game.handler.object.remove(0);
                }
                Game.handler.addObject(new JetStreams());
            }
        };
        int d = (y + 740)/120*1000;
        started = true;
        Background.isPlaying = true;
        timer.schedule(startTransition, d);
    }

    public static void unPauseGame() {
        Timer pauseTimer = new Timer();
        TimerTask pauseTransition = new TimerTask() {
            @Override
            public void run() {
                Game.state = Game.STATE.PlayScreen;
                unPaused = false;
            }
        };
        pauseTimer.schedule(pauseTransition, 3000);
        unPaused = true;
        counter = 0;
        seconds = 4;
    }

}
