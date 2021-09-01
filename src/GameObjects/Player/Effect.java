package GameObjects.Player;

import GameObjects.GameObject;

public class Effect {
    private int counter = 0;
    private final int coolDown;
    private boolean hasEffect = false;
    private final boolean hasTies;
    private GameObject gameObject = null;
    public Effect(int coolDown, boolean hasTies){
        this.coolDown = coolDown;
        this.hasTies = hasTies;
    }
    public boolean coolDown(){
        if(hasEffect){
            counter++;
            if(counter % coolDown == 0){
                counter = 0;
                hasEffect = false;
                if(hasTies){
                    gameObject = null;
                }
                return false;
            }
            return true;
        }
        return false;
    }
    public void setGameObject(GameObject gameObject){
        this.gameObject = gameObject;
    }
    public GameObject getGameObject(){
        return gameObject;
    }
    public void setEffect(boolean b){
        hasEffect = b;
    }
    public void setCounter(int counter){
        this.counter = counter;
    }
    public int getCounter(){
        return counter;
    }
}
