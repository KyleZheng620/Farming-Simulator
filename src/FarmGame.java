import javax.swing.*;
import java.awt.*;

public class FarmGame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Farm farmPanel;
    private Barn barnPanel;
    private Shop shopPanel;
    private Cooking cookPanel;
    private Animations animationsPanel;
    private Farmer player;
    private TransitionPanel transitionPanel;
    private Inventory inventory;
    private InventoryPanel inventoryPanel;
    private Cutscene cutscenePanel;
    private StartingScreen startPanel;
    private WinLoseScreen winLoseScreenPanel;

    public FarmGame() {
        inventory = new Inventory();
        player = new Farmer(inventory);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        inventoryPanel = new InventoryPanel(inventory, this, player);
        inventory.addItem(new Item("Wheat seeds", 4));
        inventory.addItem(new CropItem("Wheat",2));
        inventory.addItem(new FoodItem("Water",8));


        startPanel = new StartingScreen(this);
        cutscenePanel = new Cutscene(this);
        winLoseScreenPanel = new WinLoseScreen();
        farmPanel = new Farm(this, player);
        barnPanel = new Barn(this, player);
        shopPanel = new Shop(this, player);
        cookPanel = new Cooking(this,player);
        transitionPanel = new TransitionPanel(player);
        animationsPanel = new Animations(this, player);

        mainPanel.add(startPanel, "Start");
        mainPanel.add(cutscenePanel, "Cutscene");
        mainPanel.add(winLoseScreenPanel, "WinLose");
        mainPanel.add(farmPanel, "Farm");
        mainPanel.add(barnPanel, "Barn");
        mainPanel.add(shopPanel, "Shop");
        mainPanel.add(cookPanel,"Cooking");
        mainPanel.add(animationsPanel,"Animations");
        mainPanel.add(inventoryPanel, "Inventory");
        mainPanel.add(transitionPanel, "TRANSITION");

        add(mainPanel);

        setTitle("Farming Simulator");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void showCutscene(){
        cardLayout.show(mainPanel, "Cutscene");
        cutscenePanel.onShow();
        cutscenePanel.requestFocusInWindow();
    }
    public void showBarn(int a){
        cardLayout.show(mainPanel, "Barn");
        barnPanel.requestFocusInWindow();
    }
    public void showFarm(int a){
        cardLayout.show(mainPanel, "Farm");
        farmPanel.requestFocusInWindow();
    }
    public void showShop(int a){
        cardLayout.show(mainPanel, "Shop");
        shopPanel.requestFocusInWindow();
    }
    public void showCooking() {
        cardLayout.show(mainPanel, "Cooking");
        cookPanel.requestFocusInWindow();
    }
    public void showWinLose(int a){
        cardLayout.show(mainPanel, "WinLose");
        winLoseScreenPanel.setScreen(a);
        winLoseScreenPanel.requestFocusInWindow();
    }

    public void showBarn(boolean sleeping, boolean a){
        new Thread(() -> {
            cardLayout.show(mainPanel, "TRANSITION");

            if (sleeping){
                for (int i = 0; i <= 255; i += 20) {
                    transitionPanel.setAlpha(i, "Barn", sleeping);
                    transitionPanel.repaint();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!a) {
                    showWinLose(1);
                }else {
                    for (int i = 255; i >= 0; i -= 20) {
                        transitionPanel.setAlpha(i, "Barn", sleeping);
                        transitionPanel.repaint();
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                for (int i = 255; i >= 0; i -= 20) {
                    transitionPanel.setAlpha(i, "Barn", sleeping);
                    transitionPanel.repaint();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


            if (a){
                cardLayout.show(mainPanel, "Barn");
                if (player.getDay() == 31){
                    showWinLose(2);
                }
            }


            barnPanel.requestFocusInWindow();
        }).start();
    }

    public void showFarm(){
        new Thread(() -> {
            cardLayout.show(mainPanel, "TRANSITION");

            for (int i = 255; i >= 0; i -= 20) {
                transitionPanel.setAlpha(i, "Farm", farmPanel.getPlots(), farmPanel.getFarmerX(), farmPanel.getFarmerY());
                transitionPanel.repaint();
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            cardLayout.show(mainPanel, "Farm");
            farmPanel.requestFocusInWindow();
        }).start();
    }

    public void showShop(){
        new Thread(() -> {
            cardLayout.show(mainPanel, "TRANSITION");

            for (int i = 255; i >= 0; i -= 20) {
                transitionPanel.setAlpha(i, "Shop");
                transitionPanel.repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            cardLayout.show(mainPanel, "Shop");
            shopPanel.requestFocusInWindow();
        }).start();
    }

    public void showAnimations(double col, double row, int n, int x, int y, int d){
        cardLayout.show(mainPanel, "Animations");
        if (n == 1) {
            animationsPanel.watering(col, row, x, y, d);
        } else if (n == 2){
            animationsPanel.harvesting(col, row, x, y, d);
        } else if (n == 3){
            animationsPanel.planting(col, row, x, y, d);
        }
        animationsPanel.requestFocusInWindow();
    }


    public void toggleInventory(String panel){
        cardLayout.show(mainPanel, "Inventory");
        inventoryPanel.panel(panel);
    }

    public void toggleInventory(int r, int c, int x, int y, int d){
        cardLayout.show(mainPanel, "Inventory");
        inventoryPanel.panel(r, c, x, y, d);
    }
}
