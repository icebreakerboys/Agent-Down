package realSkyDive.src;

import java.awt.*;

public class GunItem extends GameObject{

    public GunItem(int x, int y, ID id, int w, int h, Color color, int speed) {
        super(x, y, id, w, h, color);
        this.speed = speed;
        velY = -(r.nextInt(speed)) - 3;
    }

    public void tick(){
        y += velY;
        resetPositionAndSpeed(h, speed);
    }
}
