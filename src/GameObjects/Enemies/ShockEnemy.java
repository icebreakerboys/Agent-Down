package GameObjects.Enemies;

import GameObjects.*;
import GameObjects.Items.ShockBullet;
import GameObjects.Player.*;
import Main.*;

import java.awt.*;

public class ShockEnemy extends GameObject {

    boolean notShot = true;

    public ShockEnemy(int speed, boolean friendly) {
        super(ID.ShockerEnemy, new Color(138, 43, 226), friendly);
        if(friendly){
            this.color = new Color(160, 82, 45);
        }
        sizeNPosVar(false);
        this.speed = speed;
        velY = -(r.nextInt(speed)) - 3;
    }

    public void tick() {
        y += velY;
        if ((Game.player.getY() - y)/velY == ((Game.player.getX() - 48) / (5 + Game.challengeVar)))
            shoot();
        collision();
        removeGameObject(true);
    }
    /**
     * Shoots ShockBullets at an angle
     */
    private void shoot() {
        if(notShot) {
            Game.handler.addObject(new ShockBullet((int) x + 24, (int) y + 24, (int) velY, ID.ShockBullet, color, friendly));
            notShot = false;
        }
    }
}
