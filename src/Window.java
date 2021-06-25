
import java.awt.*;
import javax.swing.*;

//this basically just starts the game window and isn't used again don't really understand it
public class Window extends Canvas {

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private static JFrame frame;

	public Window(Game game){
		frame = new JFrame("Agent Down");

		frame.setSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();

	}

}
