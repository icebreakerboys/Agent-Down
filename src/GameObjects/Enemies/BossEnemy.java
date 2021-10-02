package GameObjects.Enemies;

import GameObjects.*;
import GameObjects.Items.PowerUp;
import GameObjects.Player.Effect;
import Main.*;
import java.awt.*;

public class BossEnemy extends GameObject {

    private boolean hasNotSpawnedWeakPoints;
    private int numWeakPoints;
    private static int counter = 0;
    private Effect effect = new Effect(30, false);

    public BossEnemy(int speed){
       super(ID.BossEnemy, new Color(255, 128, 128), false);
       x = 0;
       y = 740;
       w = 624;
       h = 48;
       health = 99999;
       this.speed = speed;
       numWeakPoints = speed + 1;
       hasNotSpawnedWeakPoints = true;
   }

    public void tick() {
        if(hasNotSpawnedWeakPoints){
            spawnWeakPoints();
            hasNotSpawnedWeakPoints = false;
        }
        stunned = effect.coolDown();
        if(!stunned){
            if(counter % 11 - speed == 0) {
                y--;
            } else {
                counter++;
            }
        }
        collision();
        if(numWeakPoints <= 0) {
            GameObject perkPoint = new PowerUp(ID.PerkPoint, Color.magenta);
            perkPoint.setX(304);
            perkPoint.setY((int) y);
            Game.handler.addObject(perkPoint);
            Game.handler.removeObject(this);
            Game.challengeVar++;
        }
    }
    /**
     * Spawns the correct amount of WeakPoints
     */
    public void spawnWeakPoints(){
       for(int i = 0; i < numWeakPoints; i++){
           Game.handler.addObject(new BossWeakPoint(numWeakPoints, this));
        }
    }
    /**
     * Removes one WeakPoint
     */
    public void killWeakPoint(BossEnemy boss) {
        boss.numWeakPoints--;
    }
}
