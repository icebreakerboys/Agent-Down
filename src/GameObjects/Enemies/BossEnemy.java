package GameObjects.Enemies;

import GameObjects.*;
import Main.*;
import java.awt.*;

public class BossEnemy extends GameObject {

    private boolean hasNotSpawnedWeakPoints;
    private int numWeakPoints;
    private static int counter = 0;
    private static int counter2 = 0;

    public BossEnemy(int speed){
       super(0, 740, ID.BossEnemy, 624, 48, new Color(255, 128, 128));
       this.speed = speed;
       numWeakPoints = speed + 1;
       hasNotSpawnedWeakPoints = true;
   }

    public void tick() {
        if(hasNotSpawnedWeakPoints){
            spawnWeakPoints();
            hasNotSpawnedWeakPoints = false;
        }
        if(counter % 11 - speed == 0 && !stunned) {
            y--;
        }
        if (stunned) {
            counter2++;
            if (counter2 % 30 == 0) {
                counter2 = 0;
                stunned = false;
            }
        } else {
            counter++;
        }
        if(numWeakPoints <= 0)
            Game.handler.removeObject(this);
        collision();
    }
    /**
     * Spawns the correct amount of WeakPoints
     */
    public void spawnWeakPoints(){
       for(int i = 0; i < numWeakPoints; i++){
           Game.handler.addObject(new BossWeakPoint(r.nextInt(13) * 48, y, numWeakPoints, this));
        }
    }
    /**
     * Removes one WeakPoint
     */
    public void killWeakPoint(BossEnemy boss) {
        boss.numWeakPoints--;
    }
}
