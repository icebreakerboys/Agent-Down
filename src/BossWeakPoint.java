import java.awt.*;

public class BossWeakPoint extends GameObject {

    private final BossEnemy boss;
    private int counter = 0;

    public BossWeakPoint(double x, double y, int speed, int counter, BossEnemy boss){
        super(x, y, ID.BossWeakPoint, 40, 40, new Color(255, 100, 100));
        this.speed = speed;
        this.counter = counter;
        this.health = 25 + (speed - 1) * 5;
        if(speed >= 3){
            velX = r.nextInt(speed) + 1;
        }
        this.boss = boss;
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

    @Override
    public void collision(){
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject tempObject = Game.handler.object.get(i);
            if (tempObject.getId() == ID.BulletFriendly && getBounds().intersects(tempObject.getBounds())) {
                health--;
                if(health <= 0){
                    getBoss().killWeakPoint(getBoss());
                    Game.handler.removeObject(this);
                }
            }
        }
    }

    private BossEnemy getBoss() {
        return boss;
    }

}
