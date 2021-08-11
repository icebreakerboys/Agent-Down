import java.awt.*;

public class BossWeakPoint extends GameObject {

    private int counter = 0;

    public BossWeakPoint(double x, double y, int speed, int counter){
        super(x, y, ID.BossWeakPoint, 40, 40, new Color(255, 100, 100));
        this.speed = speed;
        this.counter = counter;
        this.health = 25 + (speed - 1) * 5;
        if(speed >= 3){
            velX = r.nextInt(speed) + 1;
        }
    }

    public void tick(){
        x += velX;
        x = Game.clamp((int) x, 0, 600);
        if(x == 0 || x == 600)
            velX = -velX;
        if(counter % 11 - speed == 0)
            y--;
        counter++;
        collision();
        removeEnemy();
    }

    @Override
    public void render(Graphics g){
        g.setColor(color);
        g.fillRect((int) x, (int) y, w, h);
        g.setColor(new Color(255, 128, 128));
        g.setFont(Menu.font30);
        g.drawString("" + health, (int) x + 3, (int) y + 30);
        g.drawRect((int) x, (int) y, w, h);
    }

}
