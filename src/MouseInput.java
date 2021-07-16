
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
  public void mousePressed(MouseEvent e) {
    //FIXME Not every click fires a bullet
    // my best guess is that it has to do with the MouseInput Class
    int mx = e.getX();
    int my = e.getY();
    // System.out.println(x + ", " + y);
    if (Game.state == Game.STATE.PlayScreen) {
      if (Player.AMMO != 0) {
        Player.shoot(mx, my);
        Player.AMMO--;
      }
    } else if (Game.state == Game.STATE.StartMenu) {
      if (mouseOver(mx, my, 150, 270, 300, 100))
        Game.state = Game.STATE.PlayScreen;
      if (mouseOver(mx, my, 150, 410, 300, 100))
        Game.state = Game.STATE.HelpMenu;
      if(mouseOver(mx, my, 150, 550, 300, 100))
        Game.state = Game.STATE.OptionsMenu;
    } else if (Game.state == Game.STATE.OptionsMenu || Game.state == Game.STATE.HelpMenu){
      if (mouseOver(mx, my, 150, 550, 300, 100))
        Game.state = Game.STATE.StartMenu;
    }
  }

  public void mouseReleased(MouseEvent e) {

  }

  private boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
    if (mx > x && mx < x + w) {
      if (my > y && my < y + h) {
        return true;
      } else return false;
    } else return false;
  }
}
