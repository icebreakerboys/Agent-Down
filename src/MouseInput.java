import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class MouseInput extends MouseAdapter {
  /**
   * Runs when Player clicks the Screen
   *
   * @param e MouseEvent
   */
  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();
    // System.out.println(x + ", " + y);
    if (Game.state == Game.STATE.PlayScreen) {
      if (Player.AMMO != 0) {
        Player.shoot(Player.X + 24, Player.Y + 24, mx, my);
        Player.AMMO--;
      }
      if(mouseOver(mx,my, 580, 4, 40, 40))
        Game.state = Game.STATE.PauseMenu;

    } else if (Game.state == Game.STATE.StartMenu) {
      if (mouseOver(mx, my, Menu.x + 150, Menu.y + 270, 300, 100)) {
        Menu.startGame();
      }
      if (mouseOver(mx, my, Menu.x + 150, Menu.y + 410, 300, 100))
        Game.state = Game.STATE.OptionsMenu;
      if(mouseOver(mx, my, Menu.x + 150, Menu.y + 550, 300, 100))
        Game.state = Game.STATE.HelpMenu;

    } else if (Game.state == Game.STATE.OptionsMenu || Game.state == Game.STATE.HelpMenu || Game.state == Game.STATE.EndMenu){
      if (mouseOver(mx, my, 150, 550, 300, 100)) {
        Game.state = Game.STATE.StartMenu;
        Background.isPlaying = false;
      }
    } else if (Game.state == Game.STATE.PauseMenu){
      if(mouseOver(mx, my, 400, 120, 120, 100) && HUD.points >= 5000)
        Player.hasShotgun = true;
      if(mouseOver(mx, my, 400, 253, 120, 100) && HUD.points >= 5000)
        Player.hasShockHacker = true;
      if(mouseOver(mx, my, 400, 386, 120, 100) && HUD.points >= 5000)
        Player.hasBetterHealthPacks = true;
      if(mouseOver(mx, my, 400, 519, 120, 100) && HUD.points >= 5000)
        Player.hasBetterBullets = true;
      if(mouseOver(mx, my, 580, 4, 40, 40)){
        Menu.unPauseGame();
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
   * @param x X value of object
   * @param y Y value of object
   * @param w width of object
   * @param h height of object
   */
  private boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
    if (mx > x && mx < x + w) {
      return my > y && my < y + h;
    } else return false;
  }
}
