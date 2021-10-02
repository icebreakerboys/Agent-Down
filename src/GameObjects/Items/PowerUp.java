package GameObjects.Items;

import GameObjects.GameObject;
import GameObjects.ID;

import java.awt.*;

public class PowerUp extends GameObject {

    public PowerUp(ID id, Color color) {
        super(id, color, true);
        sizeNPosVar(false);
        velY = -1;
    }

    public void tick() {
        y += velY;
        removeGameObject(true);
    }
}
