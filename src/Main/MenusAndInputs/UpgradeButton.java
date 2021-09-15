package Main.MenusAndInputs;

import GameObjects.Player.Player;
import Main.Game;

import java.awt.*;

public class UpgradeButton extends Button{
    private boolean pawn;
    private int tier;
    private final int tree;
    private static final int[] perksBought = {0, 0, 0};
    private static final String[][] treeNames = {{"Faster Bullets","Shotgun", "Max Upgrades"},
            {"Better PowerUps", "Super PowerUps", "Super Duper PowerUps", "Max Upgrades"},
            {"Shocker Hacker", "Electric Boogie", "Max Upgrades"}};
    private final int[] xPos = {332, 186, 40};


    public UpgradeButton(int tier, int tree, String upgradeName, boolean pawn) {
        super(478, 0,104, 104, upgradeName, Menu.font20, Game.STATE.ShopMenu, Menu.navyBlue, Color.white);
        this.tier = tier;
        this.tree = tree;
        this.pawn = pawn;
        y = ((tree-1) * 150) + 179;
    }
    @Override
    public void tick() {
        try {
            if (pawn && x > xPos[perksBought[tree -1] - tier]) {
                x -= 4;
            }
        } catch (Exception e){
            removeBtn();
        }
    }
    @Override
    public void render(Graphics g){
        g.setColor(btncolor);
        g.setFont(fnt);
        if(pawn){
            g.drawRect(x, y, w-1, h-1);
            g.setColor(fntcolor);
            g.drawString(label, x, y);
        } else {
            g.fillRect(x, y, w, h);
            g.setColor(fntcolor);
            g.drawString(label, x, y);
            if((tree == 1 && tier != 3 ) || (tree == 2 && tier != 4) || (tree == 3 && tier != 3)) {
                g.drawString("(" + (tier) + ")", x, y);
            }
        }
    }

    public void resetBtn(){
        tier = 1;
        setBtnColor(Menu.navyBlue);
        perksBought[tree -1] = 0;
        label = treeNames[tree-1][tier - 1];
    }

    public void perkBought() {
        if((tree == 1 && tier != 3 && Player.buyTopPerk()) || (tree == 3 && tier != 3 && Player.buyBotPerk())) {
            perksBought[tree -1]++;
            Game.handler.addButton(new UpgradeButton(tier, tree, treeNames[tree-1][tier -1], true));
            tier++;
            label = treeNames[tree-1][tier-1];
            if(tier == 3){
                setBtnColor(Menu.bluishGray);
            }
        } else if(tree == 2 && tier != 4 && Player.buyMidPerk()){
            if(tier == 1){
                Game.handler.addButton(MouseInput.topPerkBtn);
                Game.handler.addButton(MouseInput.botPerkBtn);
            }
            perksBought[tree -1]++;
            Game.handler.addButton(new UpgradeButton(tier, tree, treeNames[tree -1][tier-1], true));
            tier++;
            label = treeNames[tree- 1][tier-1];
            if(tier == 4){
                setBtnColor(Menu.bluishGray);
            }
        }
    }
}
