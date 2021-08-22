import java.awt.*;

public class ShockEnemy extends GameObject {
    boolean notShot = true;
    public ShockEnemy(int x, int y, int speed){
        super(x, y, ID.ShockEnemy, 48, 48, new Color(138, 43, 226));
        this.speed = speed;
        velY = -(r.nextInt(speed)) - 3;
    }

    @Override
    public void tick() {
        y += velY;
        if(Player.X >= 576/3) {
            if (y <= Player.Y + (Player.X) / (7 + Game.challengeVar) + 40)
                shoot();
        } else {
            if (y <= Player.Y + ((Player.X - 48) / (7 + Game.challengeVar) + 24))
                shoot();
        }
        removeGameObject(true);
    }

    private void shoot() {
        if(notShot)
            Game.handler.addObject(new Bullet((int) x + 24, (int) y + 24, Player.X, Player.Y, 0, (int) velY, ID.ShockBulletEnemy));
        notShot = false;
    }
}
