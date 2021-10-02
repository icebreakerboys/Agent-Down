package GameObjects.Items;

import GameObjects.GameObject;
import GameObjects.ID;
import Main.Game;

import java.awt.*;

public class ShockBullet extends GameObject {
    public ShockBullet(int x, int y, int velY, ID id, Color color, boolean friendly){
        super(id, color, friendly);
        this.x = x;
        this.y = y;
        w = 12;
        h = 12;
        this.velY = velY;
        this.velX = Game.challengeVar + 5;
        if(x == 600)
            this.velX = -this.velX;
    }

    public void tick() {
        x += velX;
        y += velY;
        collision();
        removeGameObject(false);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, w, h);
    }
}
