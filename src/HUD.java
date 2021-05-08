

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	public static int HEALTH = 100;
	
	//health math
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		if(HEALTH == 0)
			//currently ends game at 0 health
			System.exit(1);
	}

	//health visuals
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(15, 15, 200, 16);
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH * 2, 16);
		g.setColor(Color.black);
		g.drawRect(15, 15, 200, 16);
	}
	
}
