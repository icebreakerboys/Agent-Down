package Main.MenusAndInputs;

import GameObjects.Player.Player;
import Main.Game;

import java.awt.*;

public class UpgradeButton extends Button{
    private boolean pawn;
    private int tier;
    private final int tree;
    private static int perksBought1 = 0, perksBought2 = 0, perksBought3 = 0;
    private final String[] tree1Names = {"Faster Bullets", "Shotgun", "Max Upgrades"};
    private final String[] tree2Names = {"Better PowerUps", "Super PowerUps", "Super Duper PowerUps", "Max Upgrades"};
    private final String[] tree3Names = {"Shocker Hacker", "Electric Boogie", "Max Upgrades"};
    private final int[] xPos = {332, 186, 40};


    public UpgradeButton(int tier, int tree, String upgradeName, boolean pawn){
        super(478, 0,104, 104, upgradeName, Menu.font20, Game.STATE.ShopMenu, Menu.navyBlue, Color.white);
        this.tier = tier;
        this.tree = tree;
        this.pawn = pawn;
        y = ((tree-1)*150) + 179;
    }
    @Override
    public void tick() {
        try {
            if (pawn) {
                if (tree == 1 && x > xPos[perksBought1 - tier]) {
                    x -= 2;
                } else if (tree == 2 && x > xPos[perksBought2 - tier]) {
                    x -= 2;
                } else if (tree == 3 && x > xPos[perksBought3 - tier]) {
                    x -= 2;
                }
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
        perksBought1 = 0;
        perksBought2 = 0;
        perksBought3 = 0;
        if(tree == 1){
            label = tree1Names[tier - 1];
        } else if(tree == 2){
            label = tree2Names[tier - 1];
        } else if(tree == 3){
            label = tree3Names[tier - 1];
        }
    }

    public void perkBought() {
        if(tree == 1 && tier != 3 && Player.buyTopPerk()) {
            perksBought1++;
            Game.handler.addButton(new UpgradeButton(tier, 1, tree1Names[tier -1], true));
            label = tree1Names[tier-1];
            if(tier == 3){
                setBtnColor(Menu.bluishGray);
            }
        } else if(tree == 2 && tier != 4 && Player.buyMidPerk()){
            if(tier == 1){
                Game.handler.addButton(MouseInput.topPerkBtn);
                Game.handler.addButton(MouseInput.botPerkBtn);
            }
            perksBought2++;
            Game.handler.addButton(new UpgradeButton(tier, 2, tree2Names[tier-1], true));
            tier++;
            label = tree2Names[tier-1];
            if(tier == 4){
                setBtnColor(Menu.bluishGray);
            }
        } else if(tree == 3 && tier != 3 && Player.buyBotPerk()){
            perksBought3++;
            Game.handler.addButton(new UpgradeButton(tier , 3, tree3Names[tier-1], true));
            tier++;
            label = tree3Names[tier-1];
            if(tier == 3){
                setBtnColor(Menu.bluishGray);
            }
        }
    }
}
