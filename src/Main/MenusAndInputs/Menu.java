package Main.MenusAndInputs;

import GameObjects.GameObject;
import GameObjects.Player.JetStreams;
import GameObjects.Player.Player;
import Main.Game;
import Main.HUD;
import Main.Window;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Menu extends Canvas{

    public static final Color navyBlue = new Color(58, 68, 102, 255);
    public static final Color nvyBluSemiTransprnt = new Color(58, 68, 102, 200);
    private static final Color nvyBluTransprnt = new Color(58, 68, 102, 150);
    public static final Color bluishGray = new Color(90, 105, 136);
    public static final Font font30 = new Font("Monospaced", Font.PLAIN, 30);
    public static final Font font40 = new Font("Monospaced", Font.PLAIN, 40);
    private static final Font font50 = new Font("Monospaced", Font.PLAIN, 50);

    public static int x = 0, y = 900;
    public static boolean restarted = false, unPaused = false, paused = true, foundEE = false;
    private static int seconds = 4, counter = 0;

    public static MSTATE menuState = MSTATE.StartMenu;

    public enum MSTATE {
        StartMenu(),
        HelpMenu(),
        OptionsMenu(),
        EndMenu(),
    }

    /**
     * Handles the movement of the Main.MenusAndInputs.Menu
     */
    public void tick() {
        if((menuState == MSTATE.StartMenu || menuState == MSTATE.HelpMenu || menuState == MSTATE.OptionsMenu) && y > 0){
            y -= 4;
        }
        if(menuState == MSTATE.StartMenu){
            if(x > 0)
                x -= 5;
            if(x < 0)
                x += 5;
        }
        if(menuState == MSTATE.OptionsMenu && x < 640) {
            x += 5;
        }
        if(menuState == MSTATE.HelpMenu && x > -640) {
            x -= 5;
        }
        if(menuState == MSTATE.EndMenu && y > 740)
            y -= 4;

        if(Game.isPlaying)
            y -= 4;
        if(unPaused){
            if(counter % 60 == 0)
                seconds--;
            counter++;
        }
    }
    /**
     * Handles the visual components of the Menus
     *
     * @param g Graphics
     */
    public void render(Graphics g) {
        if (Game.state == Game.STATE.MainMenu) {
            Game.handler.buttonRender(g);
            renderStartMenu(g);
            renderOptionsMenu(g);
            renderHelpMenu(g);
            if(restarted)
                renderEndMenu(g);
        }
        if(Game.state == Game.STATE.PauseMenu){
            if(unPaused){
                g.setFont(font50);
                g.setColor(nvyBluSemiTransprnt);
                g.drawString("" + seconds, 320, 370);
            } else {
                Game.handler.buttonRender(g);
                renderPauseMenu(g);
            }
        }
        if(Game.state == Game.STATE.ShopMenu){
            Game.handler.buttonRender(g);
            renderShopMenu(g);
        }
    }
    /**
     * Handles the visual components of the Start Main.MenusAndInputs.Menu
     *
     * @param g Graphics
     */
    private void renderStartMenu(Graphics g) {
        g.drawImage(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Agent Down.png"))).getImage(), x + 67, y + 75, 490, 188, this);
        g.setColor(Color.black);
        g.setFont(font50);
        g.drawString("Play!", x + 242, y + 353);
        g.drawString("Options", x + 214, y + 433);
        g.setColor(navyBlue);
        g.drawRect(0, 400, 225, 50);
        g.drawString("Help", x + 258, y + 513);
        g.drawString("Quit", x + 258, y + 593);
    }
    /**
     * Handles the visual components of the Options Main.MenusAndInputs.Menu
     *
     * @param g Graphics
     */
    private void renderOptionsMenu(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(font40);
        g.drawString("Return", x + 190 - 640, y + 615);

    }
    /**
     * Handles the visual components of the Help Main.MenusAndInputs.Menu
     *
     * @param g Graphics
     */
    private void renderHelpMenu(Graphics g){
        g.setColor(nvyBluSemiTransprnt);
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
        g.drawString("Return", x + 190 + 640, y + 615);
    }
    /**
     * Handles the visual components of the End Main.MenusAndInputs.Menu
     *
     * @param g Graphics
     */
    private void renderEndMenu(Graphics g){
        g.setColor(nvyBluSemiTransprnt);
        g.fillRect(x + 100, y + 100 - 740, 425, 400);
        g.setColor(Color.WHITE);
        g.setFont(font50);
        g.drawString("Main.Game Over!", x + 175, y + 200 - 740);
        g.drawString("Score:", x + 250, y + 300 - 740);
        g.drawString("" + HUD.getScore(), x + 200, y + 400 - 740);
        g.drawString("Time:", x + 250, y + 300 - 740);
        g.drawString("" + Game.timeRunning / 10, x + 200, y + 400 - 740);
        g.setFont(font40);
        g.drawString("Back to Start", x + 190, y + 615 - 740);
    }
    /**
     * Handles the visual components of the Pause Main.MenusAndInputs.Menu
     *
     * @param g Graphics
     */
    private void renderPauseMenu(Graphics g){
        g.setColor(nvyBluTransprnt);
        g.fillRect(0, 0, 640, 740);
        g.setColor(Color.white);
        g.setFont(font50);
        g.drawString("Paused", 48, 95);
        g.setFont(font30);
        g.drawString("Resume", 60, 140);
        g.drawString("Options", 64, 190);
        g.drawString("Quit", 64, 240);
        g.drawString("Upgrades", 64, 290);
    }
    /**
     * Handles the visual components of the Shop Main.MenusAndInputs.Menu
     *
     * @param g Graphics
     */
    private void renderShopMenu(Graphics g) {
        g.setColor(nvyBluTransprnt);
        g.fillRect(0, 0, 640, 740);
        MouseInput.midPerkBtn.render(g);
        g.setColor(Color.white);
        g.setFont(font50);
        g.drawString("Shop", 48, 95);
        g.setFont(font40);
        g.drawString("Sell", 274, 660);
        g.setFont(font30);
        g.drawString("Return", 64, 140);
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));
        g.drawString("Perk Pts: " + Player.getPerkPoints(), 242, 128);
        if(Player.hasBetterPowerUps){
            MouseInput.topPerkBtn.render(g);
            MouseInput.botPerkBtn.render(g);
            g.setColor(navyBlue);
            g.drawRect(332, 329, 103, 103);
            g.setColor(Color.white);
            if(Player.hasFasterBullets){
                g.setColor(navyBlue);
                g.drawRect(332, 179, 103, 103);
                g.setColor(Color.white);
                if(Player.hasShotgun) {
                    MouseInput.topPerkBtn.setBtnColor(bluishGray);
                    g.drawString("Faster Bullets", 186, 179);
                    g.drawString("Shotgun", 332,179);
                    g.drawString("Max Upgrades", 478, 179);
                    g.setColor(navyBlue);
                    g.drawRect(186, 179, 103,103);
                } else {
                    g.drawString("Faster Bullets", 332, 179);
                    g.drawString("Shotgun", 478, 179);
                }
            } else {
                g.setColor(Color.white);
                g.drawString("Faster Bullets", 478, 179);
            }
            if(Player.hasShockerHacker){
                g.setColor(navyBlue);
                g.drawRect(332, 479, 103, 103);
                g.setColor(Color.white);
                if(Player.hasElectricBoogie) {
                    MouseInput.botPerkBtn.setBtnColor(bluishGray);
                    g.drawString("Shocker Hacker", 186, 479);
                    g.drawString("Electric Boogie", 332, 479);
                    g.drawString("Max Upgrades", 478, 479);
                    g.setColor(navyBlue);
                    g.drawRect(186, 479, 103,103);
                } else {
                    g.drawString("Electric Boogie", 478, 479);
                    g.drawString("Shocker Hacker", 332, 479);
                }
            } else {
                g.setColor(Color.white);
                g.drawString("Shocker Hacker", 478, 479);
            }
            if(Player.hasSuperPowerUps){
                g.setColor(Color.white);
                if(Player.hasSuperDuperPowerUps) {
                    MouseInput.midPerkBtn.setBtnColor(bluishGray);
                    g.drawString("Better Power Ups", 40, 329);
                    g.drawString("Super Power Ups", 186, 329);
                    g.drawString("Super Duper Power Ups", 332, 329);
                    g.drawString("Max Upgrades", 478, 329);
                    g.setColor(navyBlue);
                    g.drawRect(186, 329, 103,103);
                    g.drawRect(40, 329, 103, 103);
                } else {
                    g.drawString("Better Power Ups", 186, 329);
                    g.drawString("Super Power Ups", 332, 329);
                    g.drawString("Super Duper Power Ups", 478, 329);
                    g.setColor(navyBlue);
                    g.drawRect(186, 329, 103,103);
                }
            } else {
                g.setColor(Color.white);
                g.drawString("Super Power Ups", 478, 329);
                g.drawString("Better Power Ups", 332, 329);
            }
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Better Power Ups", 478, 329);
        }
    }
    /**
     * Starts a timer to delay the start of the Main.Game
     */
    public static void startGame() {
        java.util.Timer timer = new Timer();
        TimerTask startTransition = new TimerTask() {
            @Override
            public void run() {
                Game.state = Game.STATE.PlayScreen;
                Game.challengeVar = 1;
                Game.timeRunning = 1;
                HUD.setScore(-HUD.getScore());
                Player.X = Window.WIDTH/2;
                Player.Y = 100;
                while(Game.handler.objects.size() != 0){
                    GameObject tempObject = Game.handler.objects.get(0);
                    System.out.println(tempObject.getId());
                    Game.handler.objects.remove(0);
                }
                Game.handler.addObject(new JetStreams());
            }
        };
        int d = (y + 740)/180*1000;
        Game.isPlaying = true;
        timer.schedule(startTransition, d);
    }
    /**
     * Starts a timer to delay unPausing the Main.Game
     */
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
