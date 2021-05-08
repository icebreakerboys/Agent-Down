package realSkyDive.src;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	//FIXME: I think this health feature would be better implemented
	// if you had it as a variable of the player class instead of the hud class, up to you tho
	public static int HEALTH = 100;

	/**
	 * this method is called repeatedly to update the HUD
	 */
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		if(HEALTH == 0)
			//currently ends game at 0 health
			System.exit(1);
	}

	/**
	 * renders the hud and health of player
	 * @param g
	 */
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(15, 15, 200, 16);
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH * 2, 16);
		g.setColor(Color.black);
		g.drawRect(15, 15, 200, 16);
	}
	
}
