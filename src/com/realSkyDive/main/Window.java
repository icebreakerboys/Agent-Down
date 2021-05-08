package realSkyDive.src.com.realSkyDive.main;


import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

//this basically just starts the game window and isn't used again don't really understand it
public class Window extends Canvas{

	private static final long serialVersionUID = 5619799561406492006L;
	
	public Window (int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
		game.start();
		
	}
}
