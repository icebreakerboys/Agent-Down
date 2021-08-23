import java.awt.*;

public class ShockEnemy extends GameObject {
    boolean notShot = true;
    public ShockEnemy(int x, int y, int speed, ID id){
        super(x, y, id, 48, 48, new Color(138, 43, 226));
        if(id == ID.HackedShockEnemy){
            this.color = new Color(160, 82, 45);
        }
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
            if(id == ID.HackedShockEnemy){
                Game.handler.addObject(new Bullet((int) x + 24, (int) y + 24, Player.X, Player.Y, 0, (int) velY, ID.ShockBulletFriendly, color));
            } else {
                Game.handler.addObject(new Bullet((int) x + 24, (int) y + 24, Player.X, Player.Y, 0, (int) velY, ID.ShockBulletEnemy, color));
            }
        notShot = false;
    }
}
