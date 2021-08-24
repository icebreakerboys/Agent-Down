import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

  LinkedList<GameObject> object = new LinkedList<GameObject>();
  /**
   * Runs all of the GameObject's tick method
   */
  public void tick() {
    for (int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);
      tempObject.tick();
    }
  }
  /**
   * Runs all of the GameObject's render method
   *
   * @param g Graphics
   */
  public void render(Graphics g) {
    for (int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);
      tempObject.render(g);
    }
  }
  /**
   * Adds and Removes GameObjects from the Handler
   */
  public void addObject(GameObject object) {
    this.object.add(object);
  }

  public void removeObject(GameObject object) {
    this.object.remove(object);
  }

}
