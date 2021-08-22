import java.awt.*;
import java.net.CookieHandler;
import java.util.Random;

public class BossEnemy extends GameObject{

    private int numWeakPoints;
    private static Random R = new Random();
    private static int counter = 0;
    private boolean hasNotSpawnedWeakPoints;

    public BossEnemy(int speed){
       super(0, 740, ID.BossEnemy, 624, 48, new Color(255, 128, 128));
       this.speed = speed;
        numWeakPoints = speed + 1;
       hasNotSpawnedWeakPoints = true;
   }

    public void killWeakPoint(BossEnemy boss) {
        boss.numWeakPoints--;
    }

    public void tick() {
        if(counter % 11 - speed == 0)
            y--;
        counter++;
        if(hasNotSpawnedWeakPoints){
            spawnWeakPoints();
            hasNotSpawnedWeakPoints = false;
        }
        if(numWeakPoints <= 0)
            Game.handler.removeObject(this);
        collision();
    }

    @Override
    public void collision(){
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject tempObject = Game.handler.object.get(i);
            if (tempObject.getId() == ID.BulletFriendly && getBoundsBoss().intersects(tempObject.getBounds())) {
                Game.handler.removeObject(tempObject);
            }
        }
    }

    public Rectangle getBoundsBoss(){
        return new Rectangle((int) x, (int) y + h, w, h);
    }

    public void spawnWeakPoints(){
       for(int i = 0; i < numWeakPoints; i++){
           Game.handler.addObject(new BossWeakPoint(R.nextInt(13) * 48, y, numWeakPoints, counter, this));
        }
    }

}
