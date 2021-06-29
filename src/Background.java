import javax.swing.*;
import java.awt.*;

public class Background extends Canvas {

    private static int Y = 0, Y2 = 1472, yVel = 3;

    public Background(){

    }
    public void tick(){
        Y -= yVel;
        Y2 -= yVel;
        if(Y<= -1472 ||Y2<=-1472){
            resetYValues();
        }

    }

    private void resetYValues() {
        if(Y<Y2){
            Y = Y2 + 1472;
        } else {
            Y2 = Y + 1472;
        }
    }

    public void render(Graphics g){
        g.drawImage(new ImageIcon("background.png").getImage(), 0, Y,640, 1472, this);
        g.drawImage(new ImageIcon("background.png").getImage(), 0, Y2, 640, 1472, this);

    }

}
