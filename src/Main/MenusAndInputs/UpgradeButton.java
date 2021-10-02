package Main.MenusAndInputs;

import GameObjects.Player.Player;
import Main.Game;

import java.awt.*;

public class UpgradeButton extends Button{
    private boolean pawn;
    private int tier;
    private final int tree;
    private static final int[] perksBought = {0, 0, 0};
    private static final String[][] treeNames = {{"Armor Piercin' Rounds","The Ex-Wife", "Max Upgrades"},
            {"Better PowerUps", "More PowerUps", "Super Duper Powers", "Max Upgrades"},
            {"Shocka Hacka", "Mr. Electric", "Max Upgrades"}};
    private String[] labels = {"", "", ""};
    private final int[] xPos = {332, 186, 40};

    public UpgradeButton(int tier, int tree, String upgradeName, boolean pawn) {
        super(478, 0,104, 104, upgradeName, Menu.font20, Game.STATE.ShopMenu, Menu.navyBlue, Color.white);
        this.tier = tier;
        this.tree = tree;
        this.pawn = pawn;
        y = ((tree-1) * 150) + 179;
        divideString(upgradeName);
    }

    private void divideString(String upgradeName) {
        int lastSpace = 0;
        int numLabels = 0;
        for(int i = 0; i < upgradeName.length(); i++){
            if(upgradeName.charAt(i) == ' '){
                labels[numLabels] = upgradeName.substring(lastSpace, i);
                numLabels++;
                lastSpace = i+1;
            } else if(i == upgradeName.length()-1){
                labels[numLabels] = upgradeName.substring(lastSpace, i + 1);
            }
        }
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
        } else {
            g.fillRect(x, y, w, h);
            g.setColor(fntcolor);
            if((tree == 1 && tier != 3 ) || (tree == 2 && tier != 4) || (tree == 3 && tier != 3)) {
                g.drawString("(" + (tier) + ")", x, y + 100);
            }
        }
        g.setColor(fntcolor);
        for(int i = 0; i < 3; i++){
            g.drawString(labels[i], x, y + (i+1)*20);
        }
    }

    public void resetBtn(){
        tier = 1;
        setBtnColor(Menu.navyBlue);
        perksBought[tree -1] = 0;
        label = treeNames[tree-1][tier - 1];
        labels[2] = "";
        divideString(label);
    }

    public void perkBought() {
        if((tree == 1 && tier != 3 && Player.buyPerk(0)) || (tree == 3 && tier != 3 && Player.buyPerk(2))) {
            perksBought[tree -1]++;
            Game.handler.addButton(new UpgradeButton(tier, tree, treeNames[tree-1][tier -1], true));
            tier++;
            label = treeNames[tree-1][tier-1];
            if(tier == 3){
                setBtnColor(Menu.bluishGray);
            }
        } else if(tree == 2 && tier != 4 && Player.buyPerk(1)){
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
        labels[2] = "";
        divideString(label);
        Player.checkPerksBought();
    }

    public void maxOut() {
        if(tree == 2){
            label = treeNames[1][3];
            tier = 4;
        } else {
            label = treeNames[tree-1][2];
            tier = 3;
        }
        setBtnColor(Menu.bluishGray);
        labels[2] = "";
        divideString(label);
    }
}
