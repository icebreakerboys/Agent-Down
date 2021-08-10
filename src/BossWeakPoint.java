import java.awt.*;

public class BossWeakPoint extends GameObject {

    private int counter = 0;

    public BossWeakPoint(int x, int y, int speed){
        super(x, y, ID.BossWeakPoint, 40, 40, Color.gray);
        this.speed = speed;
        if(speed >= 3){
            velX = r.nextInt(speed) + 1;
        }
    }
    public void tick(){
        x += velX;
        x = Game.clamp(x, 0, 640);
        if(x == 0 || x == 640)
            velX = -velX;
        if(counter % 11 - speed == 0)
            y--;
        counter++;
    }


}
