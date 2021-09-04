package Main.MenusAndInputs;

import GameObjects.Player.Player;
import Main.Game;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

  public static final Button pauseBtn = new Button(580, 4, 40, 40,
          "", Menu.font30, Game.STATE.PlayScreen, Menu.navyBlue, Color.white);

  private static final Button playBtn = new Button(156, 306, 312, 68,
          "Play", Menu.font50, Game.STATE.MainMenu, Menu.nvyBluSemiTransprnt, Color.white);
  private static final Button optionsBtn = new Button(156, 386, 312, 68,
          "Options", Menu.font50, Game.STATE.MainMenu, Menu.nvyBluSemiTransprnt, Color.white);
  private static final Button helpBtn = new Button(156, 466 , 312, 68,
          "Help", Menu.font50, Game.STATE.MainMenu, Menu.nvyBluSemiTransprnt, Color.white);
  private static final Button quitBtn = new Button(156, 546, 312, 68,
          "Quit", Menu.font50, Game.STATE.MainMenu, Menu.nvyBluSemiTransprnt, Color.white);
  private static final Button returnBtn1 = new Button(156 + 638, 546, 312, 68,
          "Return", Menu.font40, Game.STATE.MainMenu, Menu.nvyBluSemiTransprnt, Color.white);
  private static final Button returnBtn2 = new Button(156 - 638, 546, 312, 68,
          "Return", Menu.font40, Game.STATE.MainMenu, Menu.nvyBluSemiTransprnt, Color.white);
  private static final Button returnBtn3 = new Button(156, 546 - 740, 312, 68,
          "Return", Menu.font40, Game.STATE.MainMenu, Menu.nvyBluSemiTransprnt, Color.white);

  private static final Button resumeBtn = new Button(56, 110, 129, 40,
          "Resume", Menu.font30, Game.STATE.PauseMenu, Menu.navyBlue, Color.white);
  private static final Button optionsBtn2 = new Button(56, 160, 121, 40,
          "Options", Menu.font30, Game.STATE.PauseMenu, Menu.navyBlue, Color.white);
  private static final Button menuBtn = new Button(56, 210, 77, 40,
          "Quit", Menu.font30, Game.STATE.PauseMenu, Menu.navyBlue, Color.white);
  private static final Button shopBtn = new Button(56, 260, 146, 40,
          "Upgrades", Menu.font30, Game.STATE.PauseMenu, Menu.navyBlue, Color.white);

  private static final Button sellBtn = new Button(234, 620, 156, 50,
          "Sell", Menu.font40, Game.STATE.ShopMenu, Color.red, Color.white);
  private static final Button returnBtn4 = new Button(56, 110, 105, 40,
          "Return", Menu.font30, Game.STATE.ShopMenu, Menu.navyBlue, Color.white);
  public static final UpgradeButton topPerkBtn = new UpgradeButton(1, 1, "Better Bullets", false);
  public static final UpgradeButton midPerkBtn = new UpgradeButton(1, 2, "Better Power Ups", false);
  public static final UpgradeButton botPerkBtn = new UpgradeButton(1, 3, "Shocker Hacker", false);

  public MouseInput(){
    Game.handler.addButton(playBtn); Game.handler.addButton(optionsBtn);
    Game.handler.addButton(helpBtn); Game.handler.addButton(quitBtn);
    Game.handler.addButton(returnBtn1); Game.handler.addButton(returnBtn2); Game.handler.addButton(returnBtn3);
    Game.handler.addButton(resumeBtn); Game.handler.addButton(optionsBtn2); Game.handler.addButton(menuBtn); Game.handler.addButton(shopBtn);
    Game.handler.addButton(sellBtn); Game.handler.addButton(returnBtn4); Game.handler.addButton(midPerkBtn);
  }

  /**
   * Runs when GameObjects.PlayerAndMore.Player clicks the Screen
   *
   * @param e MouseEvent
   */
  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();
    // System.out.println(x + ", " + y);
    if (Game.state == Game.STATE.PlayScreen) {
      if(mouseOver(mx,my, pauseBtn.getRect())) {
        Game.state = Game.STATE.PauseMenu;
        Menu.paused = true;
        Game.isPlaying = false;
      } else {
        if (Player.getAmmo() != 0) {
          Player.shoot(Player.X + 24, Player.Y + 24, mx, my);
        }
      }
    } else if (Game.state == Game.STATE.MainMenu) {
      if (mouseOver(mx, my, playBtn.getRect()))
        Menu.startGame();
      if(mouseOver(mx, my, quitBtn.getRect()))
        System.exit(1);
      if (mouseOver(mx, my, optionsBtn.getRect()))
        Menu.menuState = Menu.MSTATE.OptionsMenu;
      if(mouseOver(mx, my, helpBtn.getRect()))
       Menu.menuState = Menu.MSTATE.HelpMenu;
      if (mouseOver(mx, my, returnBtn1.getRect()) || mouseOver(mx, my, returnBtn2.getRect()) || mouseOver(mx, my, returnBtn3.getRect())) {
        if(!Menu.paused) {
          Menu.menuState = Menu.MSTATE.StartMenu;
        } else {
          Game.state = Game.STATE.PauseMenu;
        }
      }
    } else if (Game.state == Game.STATE.PauseMenu){
      if(mouseOver(mx, my, resumeBtn.getRect())){
        Menu.unPauseGame();
        Game.isPlaying = true;
        Menu.paused = false;
      }
      if(mouseOver(mx, my, optionsBtn2.getRect())){
        Game.state = Game.STATE.MainMenu;
        Menu.menuState = Menu.MSTATE.OptionsMenu;
        Menu.y = 0;
        Menu.x = 638;
      }
      if(mouseOver(mx, my, menuBtn.getRect())){
        Game.state = Game.STATE.MainMenu;
        Menu.menuState = Menu.MSTATE.StartMenu;
        Menu.paused = false;
        Menu.y = 900;
        Menu.x = 0;
      }
      if (mouseOver(mx, my, shopBtn.getRect())){
        Game.state = Game.STATE.ShopMenu;
      }
    } else if(Game.state == Game.STATE.ShopMenu){
      if(mouseOver(mx, my, sellBtn.getRect())){
        Player.removePerks();
        Player.resetPerkPoints();
        Game.handler.buttons.remove(topPerkBtn);
        Game.handler.buttons.remove(botPerkBtn);
        topPerkBtn.resetBtn();
        botPerkBtn.resetBtn();
        midPerkBtn.resetBtn();
      }
      if(mouseOver(mx, my, returnBtn4.getRect()))
        Game.state = Game.STATE.PauseMenu;
      if(mouseOver(mx, my, topPerkBtn.getRect())) {
        topPerkBtn.perkBought();
      }
      if(mouseOver(mx, my, midPerkBtn.getRect())) {
        midPerkBtn.perkBought();
      }
      if(mouseOver(mx, my, botPerkBtn.getRect())) {
        botPerkBtn.perkBought();
      }
    }
  }
  public void mouseReleased(MouseEvent e) {

  }
  /**
   * Determines if the Mouse is over something
   *
   * @param mx Mouse's x
   * @param my Mouse's y
   * @param rect Rectangle of Buttons
   */
  private boolean mouseOver(int mx, int my, Rectangle rect) {
    int x = (int) rect.getX();
    int y = (int) rect.getY();
    int w = (int) rect.getWidth();
    int h = (int) rect.getHeight();

    if (mx > x && mx < x + w) {
      return my > y && my < y + h;
    } else return false;
  }
}
