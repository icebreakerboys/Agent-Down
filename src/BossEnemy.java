import java.awt.*;

public class BossEnemy extends GameObject{
    private int counter = 0;
    public boolean movingWeakPoints = false;

   public BossEnemy(int speed){
       super(0, 640, ID.BossEnemy, 640, 40, new Color(255, 0 , 0, 100));
       this.speed = speed;
       if(speed >= 3){
           movingWeakPoints = true;
       }
       spawnWeakPoints();
   }

    public void tick() {
        if(counter % 11 - speed == 0)
            y--;
        counter++;
    }

    private void spawnWeakPoints(){
       int numWeakPoints = speed;
       for(int i = 0; i < numWeakPoints; i++){
           Game.handler.addObject(new BossWeakPoint(r.nextInt(640), y, speed));
        }
    }

}
