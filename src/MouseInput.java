
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
      if (mouseOver(mx, my, 150, 270, 300, 100)) {
        Game.state = Game.STATE.PlayScreen;
        Player.HEALTH = 100;
        Player.AMMO = 0;
        Player.X = Window.WIDTH/2;
        Player.Y = 100;
        HUD.points = 0;
        HUD.score = 0;

        while(Game.handler.object.size() != 0){
          Game.handler.object.remove(0);
        }
        Game.handler.addObject(new JetStreams());
      }
      if (mouseOver(mx, my, 150, 410, 300, 100))
        Game.state = Game.STATE.OptionsMenu;
      if(mouseOver(mx, my, 150, 550, 300, 100))
        Game.state = Game.STATE.HelpMenu;
    } else if (Game.state == Game.STATE.OptionsMenu || Game.state == Game.STATE.HelpMenu || Game.state == Game.STATE.EndMenu){
      if (mouseOver(mx, my, 150, 550, 300, 100))
        Game.state = Game.STATE.StartMenu;
    } else if (Game.state == Game.STATE.PauseMenu){
      if(mouseOver(mx, my, 400, 120, 120, 100) && HUD.points == 5000)
        Player.hasShotgun = true;
      if(mouseOver(mx, my, 400, 253, 120, 100) && HUD.points == 5000)
        Player.speedBuff = true;
      if(mouseOver(mx, my, 400, 386, 120, 100) && HUD.points == 5000)
        Player.healthBuff = true;
      if(mouseOver(mx, my, 400, 519, 120, 100) && HUD.points == 5000)
        Player.resistanceBuff = true;
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
