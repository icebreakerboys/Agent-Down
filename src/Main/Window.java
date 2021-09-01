package Main;

import Main.Game;

import java.awt.*;
import javax.swing.*;

public class Window extends Canvas {

  public static final int WIDTH = 638, HEIGHT = 740;
  /**
   * Makes the window of which the game is on
   *
   * @param game Used to add & start the game
   */
  public Window(Game game) {
    JFrame frame = new JFrame("Agent Down");
    frame.setSize(new Dimension(WIDTH, HEIGHT));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    //frame.setLayout(null);
    frame.setLocationRelativeTo(null);
    frame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/Player.png")).getImage());
    frame.add(game);
    frame.setVisible(true);
    game.start();
  }
}
