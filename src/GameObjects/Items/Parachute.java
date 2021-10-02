package GameObjects.Items;

import GameObjects.Enemies.ShooterEnemy;
import GameObjects.*;
import Main.*;

import java.awt.*;

public class Parachute extends GameObject {

    private final ShooterEnemy shooter;

    public Parachute(double velX, double velY, ShooterEnemy shooter) {
        super(ID.Parachute, Color.yellow, true);
        this.shooter = shooter;
        x = shooter.getX();
        y = shooter.getY() - shooter.getH();
        w = shooter.getW();
        h = (shooter.getH()/2);
        this.velX = velX;
        this.velY = velY;
    }

    public void tick() {
        x += velX;
        y += velY;
        collision();
        removeGameObject(true);
    }

    @Override
    public void collision(){
        for (int i = 0; i < Game.handler.objects.size(); i++) {
            GameObject tempObject = Game.handler.objects.get(i);
            if ((tempObject.getId() == ID.Bullet && tempObject.getFriendly()) && getBounds().intersects(tempObject.getBounds())) {
                shooter.startFalling();
                Game.handler.removeObject(this);
            }
        }
    }
}
