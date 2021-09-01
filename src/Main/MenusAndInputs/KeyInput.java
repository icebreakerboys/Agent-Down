package Main.MenusAndInputs;

import GameObjects.Player.Player;
import Main.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
  /**
   * Used to determine what key(s) is pressed
   */
  public static Boolean[] keysDown = new Boolean[4];
  public KeyInput() {
    keysDown[0] = false;
    keysDown[1] = false;
    keysDown[2] = false;
    keysDown[3] = false;
  }
  /**
   * Sets X & Y velocities of the player when a key is pressed
   *
   * @param e KeyEvent
   */
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    //Key Events for GameObjects.PlayerAndMore.Player
    if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
      Player.velX = 5;
      keysDown[0] = true;
    }
    if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
      Player.velX = -5;
      keysDown[1] = true;
    }
    if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
      //GameObjects.PlayerAndMore.Player.velY = 2;
      keysDown[2] = true;
    }
    if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
      Player.velY = -4;
      keysDown[3] = true;
    }
    if (key == KeyEvent.VK_P) {
      if (Game.state == Game.STATE.PlayScreen) {
        Game.state = Game.STATE.PauseMenu;
      } else if (Game.state == Game.STATE.PauseMenu) {
        Menu.unPauseGame();
      }
    }
  }

  /**
   * Stops player from moving after key is released
   *
   * @param e KeyEvent
   */
  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    //Key Events for GameObjects.PlayerAndMore.Player
    if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
      keysDown[0] = false;
    if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
      keysDown[1] = false;
    if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
      keysDown[2] = false;
    if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
      keysDown[3] = false;
    if (!keysDown[0] && !keysDown[1]) {
      Player.velX = 0;
    }
    if (!keysDown[2] && !keysDown[3]) {
      Player.velY = 0;
    }
  }
}

