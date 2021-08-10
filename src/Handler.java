
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
  LinkedList<GameObject> object = new LinkedList<GameObject>();

  //Runs all math for GameObjects
  public void tick() {
    for (int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);
      tempObject.tick();
    }
  }

  //Runs all visuals for GameObjects
  public void render(Graphics g) {
    for (int i = 0; i < object.size() ; i--) {
      GameObject tempObject = object.get(i);
      tempObject.render(g);
    }
  }

  //Add and Remove from handler List
  public void addObject(GameObject object) {
    this.object.add(object);
  }

  public void removeObject(GameObject object) {
    this.object.remove(object);
  }

  public void makeForDelete() {
    for (int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);
      if (tempObject.getId() == ID.Enemy || tempObject.getId() == ID.ShooterEnemy) {
        tempObject.markForDelete();
      }
    }
  }
}
