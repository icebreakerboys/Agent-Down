package GameObjects.Player;

import GameObjects.*;
import GameObjects.Items.Bullet;
import Main.*;
import Main.MenusAndInputs.*;
import Main.MenusAndInputs.Menu;
import Main.Window;
import java.awt.*;

public class Player extends GameObject {

  private static double minVelY = -100;
  private static int ammo = 0;
  public static boolean[][] perks = {{false, false}, {false, false, false}, {false, false}};
  private static int[] treeTiers = {2,1,2};
  private static int perkPoints = 0, perksBought = 0;
  private static boolean justHit = false, justOnParachute = false, stunned = false, hasSpeedBuff = false, haveDamageBoost = false;
  private static final Effect effect1 = new Effect(30, false), effect2 = new Effect(30, false),
          effect3 = new Effect(30000, false), effect4 = new Effect(90, true);
  private static double timeDown = 0;
  private static GameObject parachute = null;

  public Player(int x, int y) {
    super(ID.Player, Color.gray, true);
    this.x = x;
    this.y = y;
    w = 48;
    h = 48;
    health = 100;
  }
  /**
   * Handles players movement
   * Keeps player within window bounds
   * Determines if the player hits anything
   * Ends the game when necessary
   */
  public void tick() {
    if(!stunned) {
      x += velX;
      y += velY;
      if(hasSpeedBuff){
        x += velX;
        y += velY;
      }
    }
    justHit = effect1.coolDown();
    stunned = effect2.coolDown();
    hasSpeedBuff = effect3.coolDown();
    justOnParachute = effect4.coolDown();
    parachute = effect4.getGameObject();
    if(effect4.getCounter() < 60 && parachute != null && x >= parachute.getX() - 36 && x <= parachute.getX() + 36){
      y += parachute.getVelY();
      timeDown = 2;
    }
    if(KeyInput.keysDown[2] || KeyInput.keysDown[3]){
      determineVelY();
    } else {
      velY = 0;
      timeDown = 1;
    }
    collision();
    x = Game.clamp(x, 48, Window.WIDTH - 110);
    y = Game.clamp(y, 0, 3*(Window.HEIGHT/4) - 45);
    velY = Game.clamp(velY, minVelY, 10);
    minVelY = -5;
    //health = 100;
    //ammo = 10;
    //perkPoints = 10;
    health = (int) Game.clamp(health, 0, 100);
    ammo = (int) Game.clamp(ammo, 0, 420);

    if (health == 0) {
      die();
    }
  }

  /**
   * Handles the visual components of the player
   *
   * @param g Graphics
   */
  @Override
  public void render(Graphics g) {
    //Image image = new ImageIcon(getClass().getClassLoader().getResource("images/GameObjects.PlayerAndMore.Player.png")).getImage();
    if (justHit) {
      if (effect1.getCounter() % 5 != 0) {
        //g.drawImage(image, X - 8, Y - 4, 69, 56, this);
        g.setColor(color);
        g.fillRect((int) x, (int) y, w, h);
      }
    } else {
      //g.drawImage(image, X - 8, Y - 4, 69, 56, this);
      g.setColor(color);
      g.fillRect((int)x, (int)y, w, h);
    }
    if(Menu.menuState == Menu.MSTATE.EndMenu) {
      g.setColor(color);
      g.fillRect((int)x, (int)y, w, h);
    }
  }
  /**
   * Handles player collision with GameObjects
   */
  @Override
  protected void collision() {
    for (int i = 0; i < Game.handler.objects.size(); i++) {
      GameObject tempObject = Game.handler.objects.get(i);
      ID tempID = tempObject.getId();
      if(Game.player.getBounds().intersects(tempObject.getBounds())){
        switch(tempID){
          case Enemy:
            takeDamage(9 + Game.challengeVar);
            break;
          case Bullet:
            if(!tempObject.getFriendly()){
              takeDamage(9 + Game.challengeVar);
              Game.handler.removeObject(tempObject);
            }
            break;
          case ShockBullet:
            if(!tempObject.getFriendly()){
              if(!justHit){
                effect2.setEffect(true);
                Game.handler.removeObject(tempObject);
              }
            } else {
              effect3.setEffect(true);
              Game.handler.removeObject(tempObject);
            }
            break;
          case BossEnemy:
            takeDamage(100);
            break;
          case PerkPoint:
            perkPoints++;
            Game.handler.removeObject(tempObject);
            break;
          case DamageBoost:
            haveDamageBoost = true;
            Game.handler.removeObject(tempObject);
            break;
          case Magazine:
            if (perks[1][0])
              ammo += 12;
            else
              ammo += 8;
            Game.handler.removeObject(tempObject);
            break;
          case HealthPack:
            if (health < 100) {
              if (perks[1][0])
                health += 50;
              else
                health += 33;
              Game.handler.removeObject(tempObject);
            }
            break;
          case Parachute:
            if(!justOnParachute) {
              effect4.setGameObject(tempObject);
              effect4.setEffect(true);
            }
        }
      }

      if (tempID ==  ID.Parachute && new Rectangle((int)x, (int)y, 48, 12).intersects(
              new Rectangle(tempObject.getX(), tempObject.getY() + 25, 48, 10))) {
        minVelY = tempObject.getVelY();
      }
    }
  }
  /**
   * Handles player Velocity Y
   */
  private void determineVelY() {
    if(KeyInput.keysDown[2] && KeyInput.keysDown[3]) {
      velY = 0;
      timeDown = 1;
    }
    if(KeyInput.keysDown[2]) {
      velY = timeDown;
      if (timeDown <= 8) {
        timeDown += .2;
      }
    } else {
      timeDown = 1;
    }
  }
  /**
   * Shoots Friendly Bullets at angles
   */
  public void shoot(int tX, int tY) {
    ammo--;
    int x =(int) this.x + 24;
    int y =(int) this.y + 24;
    int xC = tX - x;
    int yC = tY - y;
    double angle = Math.atan2(yC,xC);
    if(!perks[0][1]) {
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle, ID.Bullet, color, true));
    } else {
      double angle1 = angle + (3.14/9);
      double angle2 = angle - (3.14/9);
      double angle3 = angle + (3.14/18);
      double angle4 = angle - (3.14/18);
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle, ID.Bullet, color, true));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle1, ID.Bullet, color, true));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle2, ID.Bullet, color, true));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle3, ID.Bullet, color, true));
      Game.handler.addObject(new Bullet(x, y, tX, tY, angle4, ID.Bullet, color, true));
    }
  }
  /**
   * Returns the Hit box of the GameObjects.PlayerAndMore.Player
   */
  public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, w, h);
  }

  private void takeDamage(int damage){
    if(!justHit) {
      health -= damage;
      effect1.setEffect(true);
    }
  }

  public static void resetPerkPoints() {
    perkPoints += perksBought - 1;
    perksBought = 0;
  }

  private void die() {
    Game.state = Game.STATE.MainMenu; Game.isPlaying = false;
    Menu.menuState = Menu.MSTATE.EndMenu; Menu.restarted = true; Menu.y = 1480;
    health = 100; ammo = 0;
    removePerks(); removeEffects();
    perkPoints = 0; perksBought = 0;
  }

  private static void removeEffects(){
    effect1.setEffect(false); effect2.setEffect(false);
    effect3.setEffect(false); effect4.setEffect(false);
    effect1.setCounter(0); effect2.setCounter(0);
    effect3.setCounter(0); effect4.setCounter(0);
    effect4.setGameObject(null);
    justHit = false; stunned = false;
    hasSpeedBuff = false; justOnParachute = false;
    haveDamageBoost = false;
    parachute = null;
  }

  public static void removePerks() {
    perks[0][0] = false; perks[0][1] = false;
    perks[1][0] = false; perks[1][1] = false; perks[1][2] = false;
    perks[2][0] = false; perks[2][1] = false;
    treeTiers[0] = 2; treeTiers[1] = 1; treeTiers[2] = 2;
  }

  public static boolean buyPerk(int tree){
    if(perkPoints >= treeTiers[tree] && perksBought <= (8 - treeTiers[tree])){
      perkPoints -= treeTiers[tree];
      perksBought += treeTiers[tree];
      if(tree == 1){
        perks[tree][treeTiers[tree] - 1] = true;
      } else {
        perks[tree][treeTiers[tree] - 2] = true;
      }
      treeTiers[tree]++;
      return true;
    }
    return false;
  }

  public static void checkPerksBought() {
    if(perksBought == 7 || perksBought == 8){
      MouseInput.topPerkBtn.maxOut();
      MouseInput.midPerkBtn.maxOut();
      MouseInput.botPerkBtn.maxOut();
    }
  }

  public static boolean getDamageBoost() {
    return haveDamageBoost;
  }
  public static int getPerkPoints() {
    return perkPoints;
  }
  public int getHealth() {
    return health;
  }
  public static int getAmmo(){
    return ammo;
  }
}
