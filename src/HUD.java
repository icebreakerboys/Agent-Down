
import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	/**
	 * this method is called repeatedly to update the HUD
	 */
	public void tick() {
		Player.HEALTH = Game.clamp(Player.HEALTH, 0, Player.HEALTH);
		if(Player.HEALTH == 0) {
			Game.running = false;

		}
	}

	/**
	 * renders the hud and health of player
	 * @param g
	 */
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(15, 15, 200, 16);
		g.setColor(Color.green);
		g.fillRect(15, 15, Player.HEALTH * 2, 16);
		g.setColor(Color.black);
		g.drawRect(15, 15, 200, 16);
		g.drawString("" + Player.AMMO, 600, 28);
	}
	
}
