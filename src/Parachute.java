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
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject tempObject = Game.handler.object.get(i);
            if (tempObject.getId() == ID.BulletFriendly && getBounds().intersects(tempObject.getBounds())) {
                shooter.startFalling();
                Game.handler.removeObject(this);
            }
        }
    }
}
