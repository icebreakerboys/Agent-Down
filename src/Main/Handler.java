package Main;

import GameObjects.GameObject;

import java.awt.Graphics;
import java.util.LinkedList;
import Main.MenusAndInputs.*;

public class Handler {

  public LinkedList<GameObject> objects = new LinkedList<GameObject>();
  public LinkedList<Button> buttons = new LinkedList<Button>();

  /**
   * Runs all of the GameObjects.GameObject's tick method
   */
  public void objectTick() {
    for (int i = 0; i < objects.size(); i++) {
      GameObject tempObject = objects.get(i);
      tempObject.tick();
    }
  }

  public void buttonTick() {
    for (int i = 0; i < buttons.size(); i++) {
      Button tempButton = buttons.get(i);
      tempButton.tick();
    }
  }
  /**
   * Runs all of the GameObjects.GameObject's render method
   *
   * @param g Graphics
   */
  public void objectRender(Graphics g) {
    for (int i = 0; i < objects.size(); i++) {
      GameObject tempObject = objects.get(i);
      tempObject.render(g);
    }
  }

  public void buttonRender(Graphics g) {
    for (int i = 0; i < buttons.size(); i++) {
      Button tempButton = buttons.get(i);
      if(tempButton.getBtnState() == Game.state)
      tempButton.render(g);
    }
  }
  /**
   * Adds and Removes GameObjects from the Main.Handler
   */
  public void addObject(GameObject object) {
    this.objects.add(object);
  }

  public void addButton(Button button){
    this.buttons.add(button);
  }

  public void removeObject(GameObject object) {
    this.objects.remove(object);
  }

}
