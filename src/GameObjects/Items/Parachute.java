package GameObjects.Items;

import GameObjects.Enemies.ShooterEnemy;
import GameObjects.*;
import Main.*;

import java.awt.*;

public class Parachute extends GameObject {

    private final ShooterEnemy shooter;

    public Parachute(double x, double y, double velX, double velY, ShooterEnemy shooter) {
        super(x, y - 48, ID.Parachute, 48, 24, Color.yellow);
        this.shooter = shooter;
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
            if (tempObject.getId() == ID.FriendlyBullet && getBounds().intersects(tempObject.getBounds())) {
                shooter.startFalling();
                Game.handler.removeObject(this);
            }
        }
    }
}
