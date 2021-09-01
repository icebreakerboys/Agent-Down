package Main.MenusAndInputs;

import GameObjects.Player.Player;
import Main.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

  public static final Main.MenusAndInputs.Button pauseBtn = new Main.MenusAndInputs.Button(580, 4, 40, 40, Game.STATE.PlayScreen, Main.MenusAndInputs.Menu.navyBlue);

  private static final Main.MenusAndInputs.Button playBtn = new Main.MenusAndInputs.Button(156, 306, 312, 68, Game.STATE.MainMenu, Main.MenusAndInputs.Menu.nvyBluSemiTransprnt);
  private static final Main.MenusAndInputs.Button optionsBtn = new Main.MenusAndInputs.Button(156, 386, 312, 68, Game.STATE.MainMenu, Color.white);
  private static final Main.MenusAndInputs.Button helpBtn = new Main.MenusAndInputs.Button(156, 466 , 312, 68, Game.STATE.MainMenu, Main.MenusAndInputs.Menu.nvyBluSemiTransprnt);
  private static final Main.MenusAndInputs.Button quitBtn = new Main.MenusAndInputs.Button(156, 546, 312, 68, Game.STATE.MainMenu, Main.MenusAndInputs.Menu.nvyBluSemiTransprnt);
  private static final Main.MenusAndInputs.Button returnBtn1 = new Main.MenusAndInputs.Button(156 + 638, 546, 312, 68, Game.STATE.MainMenu, Main.MenusAndInputs.Menu.nvyBluSemiTransprnt);
  private static final Main.MenusAndInputs.Button returnBtn2 = new Main.MenusAndInputs.Button(156 - 638, 546, 312, 68, Game.STATE.MainMenu, Main.MenusAndInputs.Menu.nvyBluSemiTransprnt);
  private static final Main.MenusAndInputs.Button returnBtn3 = new Main.MenusAndInputs.Button(156, 546 - 740, 312, 68, Game.STATE.MainMenu, Main.MenusAndInputs.Menu.nvyBluSemiTransprnt);

  private static final Main.MenusAndInputs.Button resumeBtn = new Main.MenusAndInputs.Button(56, 110, 129, 40, Game.STATE.PauseMenu, Menu.navyBlue);
  private static final Main.MenusAndInputs.Button optionsBtn2 = new Main.MenusAndInputs.Button(56, 160, 121, 40, Game.STATE.PauseMenu, Main.MenusAndInputs.Menu.navyBlue);
  private static final Main.MenusAndInputs.Button menuBtn = new Main.MenusAndInputs.Button(56, 210, 77, 40, Game.STATE.PauseMenu, Main.MenusAndInputs.Menu.navyBlue);
  private static final Main.MenusAndInputs.Button shopBtn = new Main.MenusAndInputs.Button(56, 260, 146, 40, Game.STATE.PauseMenu, Main.MenusAndInputs.Menu.navyBlue);

  private static final Main.MenusAndInputs.Button sellBtn = new Main.MenusAndInputs.Button(234, 620, 156, 50, Game.STATE.ShopMenu, Color.red);
  private static final Main.MenusAndInputs.Button returnBtn4 = new Main.MenusAndInputs.Button(56, 110, 105, 40, Game.STATE.ShopMenu, Main.MenusAndInputs.Menu.navyBlue);
  public static final Main.MenusAndInputs.Button topPerkBtn = new Main.MenusAndInputs.Button(478, 179, 104, 104, Game.STATE.ShopMenu, Main.MenusAndInputs.Menu.navyBlue);
  public static final Main.MenusAndInputs.Button midPerkBtn = new Main.MenusAndInputs.Button(478, 329, 104, 104, Game.STATE.ShopMenu, Main.MenusAndInputs.Menu.navyBlue);
  public static final Main.MenusAndInputs.Button botPerkBtn = new Button(478, 479, 104, 104, Game.STATE.ShopMenu, Main.MenusAndInputs.Menu.navyBlue);


  public MouseInput(){
    Game.handler.addButton(playBtn);
    Game.handler.addButton(optionsBtn);
    Game.handler.addButton(helpBtn);
    Game.handler.addButton(quitBtn);
    Game.handler.addButton(returnBtn1);
    Game.handler.addButton(returnBtn2);
    Game.handler.addButton(returnBtn3);

    Game.handler.addButton(resumeBtn);
    Game.handler.addButton(optionsBtn2);
    Game.handler.addButton(menuBtn);
    Game.handler.addButton(shopBtn);

    Game.handler.addButton(sellBtn);
    Game.handler.addButton(returnBtn4);
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
      if (Player.getAmmo() != 0) {
        Player.shoot(Player.X + 24, Player.Y + 24, mx, my);
      }
      if(mouseOver(mx,my, new Rectangle(580, 4, 40, 40)))
        Game.state = Game.STATE.PauseMenu;
        Main.MenusAndInputs.Menu.paused = true;
        Game.isPlaying = false;

    } else if (Game.state == Game.STATE.MainMenu) {
      if (mouseOver(mx, my, playBtn.getRect()))
        Main.MenusAndInputs.Menu.startGame();
      if(mouseOver(mx, my, quitBtn.getRect()))
        System.exit(1);
      if (mouseOver(mx, my, optionsBtn.getRect()))
        Main.MenusAndInputs.Menu.menuState = Main.MenusAndInputs.Menu.MSTATE.OptionsMenu;
      if(mouseOver(mx, my, helpBtn.getRect()))
        Main.MenusAndInputs.Menu.menuState = Main.MenusAndInputs.Menu.MSTATE.HelpMenu;
      if (mouseOver(mx, my, returnBtn1.getRect()) || mouseOver(mx, my, returnBtn2.getRect()) || mouseOver(mx, my, returnBtn3.getRect())) {
        if(!Main.MenusAndInputs.Menu.paused) {
          Main.MenusAndInputs.Menu.menuState = Main.MenusAndInputs.Menu.MSTATE.StartMenu;
        } else {
          Game.state = Game.STATE.PauseMenu;
        }
      }

    } else if (Game.state == Game.STATE.PauseMenu){
      if(mouseOver(mx, my, resumeBtn.getRect())){
        Main.MenusAndInputs.Menu.unPauseGame();
        Game.isPlaying = true;
      }
      if(mouseOver(mx, my, optionsBtn2.getRect())){
        Game.state = Game.STATE.MainMenu;
        Main.MenusAndInputs.Menu.menuState = Main.MenusAndInputs.Menu.MSTATE.OptionsMenu;
        Main.MenusAndInputs.Menu.y = 0;
        Main.MenusAndInputs.Menu.x = 638;
      }
      if(mouseOver(mx, my, menuBtn.getRect())){
        Game.state = Game.STATE.MainMenu;
        Main.MenusAndInputs.Menu.menuState = Main.MenusAndInputs.Menu.MSTATE.StartMenu;
        Main.MenusAndInputs.Menu.y = 900;
        Main.MenusAndInputs.Menu.x = 0;
      }
      if (mouseOver(mx, my, shopBtn.getRect())){
        Game.state = Game.STATE.ShopMenu;
      }
    } else if(Game.state == Game.STATE.ShopMenu){
      if(mouseOver(mx, my, sellBtn.getRect())){
        Player.removePerks();
        Player.resetPerkPoints();
        midPerkBtn.setBtnColor(Main.MenusAndInputs.Menu.navyBlue);
        topPerkBtn.setBtnColor(Main.MenusAndInputs.Menu.navyBlue);
        botPerkBtn.setBtnColor(Main.MenusAndInputs.Menu.navyBlue);
      }
      if(mouseOver(mx, my, returnBtn4.getRect()))
        Game.state = Game.STATE.PauseMenu;
      if(mouseOver(mx, my, topPerkBtn.getRect())) {
        Player.buyTopPerk();
      }
      if(mouseOver(mx, my, midPerkBtn.getRect())) {
        Player.buyMidPerk();
      }
      if(mouseOver(mx, my, botPerkBtn.getRect())) {
        Player.buyBotPerk();
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
