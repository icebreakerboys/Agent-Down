package Main.MenusAndInputs;

import Main.Game;

import java.awt.*;

public class Button {
    protected int x, y;
    private final int dX, dY;
    protected final int w, h;
    protected String label;
    private int sX, sY;
    protected final Font fnt;
    protected final Game.STATE btnState;
    protected Color btncolor, fntcolor;

    private TYPE type;

    public TYPE getType() {
        return type;
    }

    public enum TYPE {
        UpgradeBtn(),
        Btn(),
    }


    public Button(int dX, int dY, int w, int h, String label, Font fnt, Game.STATE btnState, Color btncolor, Color fntcolor){
        this.dX = dX;
        this.dY = dY;
        x = dX;
        y = dY;
        this.w = w;
        this.h = h;
        this.btnState = btnState;
        this.btncolor = btncolor;
        this.label = label;
        this.fnt = fnt;
        this.fntcolor = fntcolor;
        determineStrPlacement(label);
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
                g.setColor(fntcolor);
                g.setFont(fnt);
                g.drawString(label, sX + x, sY + y);
            }
        } else {
            g.setColor(btncolor);
            g.fillRect(x, y, w, h);
            g.setColor(fntcolor);
            g.setFont(fnt);
            g.drawString(label, x + sX, y + sY);
        }
    }

    private void determineStrPlacement(String str){
        int numChar = str.length();
        int fntSize = fnt.getSize();
        sY = (int) (fntSize*0.6 - h)/2 + h;
        sX = (int) (w - (numChar * fntSize*0.6))/2;
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

    public void removeBtn(){
        Game.handler.buttons.remove(this);
    }
}
