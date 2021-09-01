package Main.MenusAndInputs;

import Main.Game;

import java.awt.*;

public class Button {
    private int x, y;
    private final int dX, dY, w, h;
    private int sX, sY;
    private Font fnt;
    private final Game.STATE btnState;
    private Color btncolor, fntcolor;

    //FIXME Try adding Strings to put on top of the buttons
    public Button(int dX, int dY, int w, int h, Game.STATE btnState, Color btncolor){
        this.dX = dX;
        this.dY = dY;
        x = dX;
        y = dY;
        this.w = w;
        this.h = h;
        this.btnState = btnState;
        this.btncolor = btncolor;
//        this.name = name;
        //determineStrPlacement(name);
//        this.font = font;
//        this.fntcolor = fntcolor;
    }

    public void tick() {
        if(btnState == Game.STATE.MainMenu){
            x = Menu.x + dX;
            y = Menu.y + dY;
        }
    }

    public void render(Graphics g) {
        if(dY < 0){
            if(Menu.restarted) {
                g.setColor(btncolor);
                g.fillRect(x, y, w, h);
            }
        } else {
            g.setColor(btncolor);
            g.fillRect(x, y, w, h);
        }
    }

    private void determineStrPlacement(String str){
        int numChar = str.length();
        sY = (h - fnt.getSize())/2 + dY;
        sX = (w - fnt.getSize())/2 + dX;
        System.out.println(numChar);
    }
    public Rectangle getRect(){
        return new Rectangle(x, y, w, h);
    }

    public Game.STATE getBtnState(){
        return btnState;
    }

    public void setBtnColor(Color color) {
        this.btncolor = color;
    }
}
