import java.awt.*;
import java.net.CookieHandler;
import java.util.Random;

public class BossEnemy extends GameObject{

    public boolean movingWeakPoints = false;
    public static int numWeakPoints = 0;
    private static final double Y = 740;
    private static Random R = new Random();
    private static int counter = 0;

    public BossEnemy(int speed){
       super(0, 740, ID.BossEnemy, 640, 40, new Color(255, 128, 128));
       this.speed = speed;
       if(speed >= 3){
           movingWeakPoints = true;
       }
        numWeakPoints = speed + 1;
   }

    public void tick() {
        if(counter % 11 - speed == 0)
            y--;
        counter++;
        if(numWeakPoints == 0)
            Game.handler.removeObject(this);

    }

    public static void spawnWeakPoints(){
       for(int i = 0; i < numWeakPoints; i++){
           Game.handler.addObject(new BossWeakPoint(R.nextInt(15)*40, Y, numWeakPoints, counter));
        }
    }
}
