package GameObjects.Enemies;

import GameObjects.*;
import Main.*;
import Main.MenusAndInputs.Menu;

import java.awt.*;

public class BossWeakPoint extends GameObject {

    public BossWeakPoint(int speed, BossEnemy boss){
        super(ID.BossWeakPoint, new Color(255, 100, 100), false);
        this.boss = boss;
        x = r.nextInt(13) * 48;
        y = boss.getY();
        w = 48;
        h = 48;
        this.speed = speed;
        this.health = (speed - 1) * 4;
        if(speed >= 3){
            velX = r.nextInt(speed) + 1;
        }
    }

    public void tick(){
        if(!boss.getStunned()) {
            y = boss.getY();
            x += velX;
            x = Game.clamp((int) x, 0, 576);
        }
        if(x == 0 || x == 576)
            velX = -velX;
        collision();
    }

    @Override
    public void render(Graphics g){
        g.setColor(color);
        g.fillRect((int) x, (int) y, w, h);
        g.setColor(new Color(255, 128, 128));
        g.setFont(Menu.font30);
        g.drawString("" + health, (int) x + 7, (int) y + 34);
        g.drawRect((int) x, (int) y, w, h);
    }
}
