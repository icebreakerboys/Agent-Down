package realSkyDive.src;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    public void mouseClicked(MouseEvent e){
        //FIXME Not every click fires a bullet
        // my best guess is that it has to do with the MouseInput Class
        int x = e.getX();
        int y = e.getY();
        System.out.println(x + ", " + y);
        if(Player.hasGun){
            Player.shoot(x, y, true);
        }
    }

}
