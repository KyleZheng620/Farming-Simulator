import javax.swing.*;
import java.awt.*;

public class FarmGame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Farm farmPanel;
    private Barn barnPanel;
    private Shop shopPanel;
    private Farmer player;
    private TransitionPanel transitionPanel;
    private Inventory inventory;
    private InventoryPanel inventoryPanel;

    public FarmGame() {
        inventory = new Inventory();

        player = new Farmer(inventory);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        inventoryPanel = new InventoryPanel(inventory, this, player);
        inventory.addItem("Rice seeds", 10);
        inventory.addItem("Wheat seeds", 5);
        inventory.addItem("Potato seeds", 3);
        inventory.addItem("Mandarin seeds", 3);

        farmPanel = new Farm(this, player);
        barnPanel = new Barn(this, player);
        shopPanel = new Shop(this, player);
        transitionPanel = new TransitionPanel(player);

        mainPanel.add(farmPanel, "Farm");
        mainPanel.add(barnPanel, "Barn");
        mainPanel.add(shopPanel, "Shop");
        mainPanel.add(inventoryPanel, "Inventory");
        mainPanel.add(transitionPanel, "TRANSITION");

        add(mainPanel);

        setTitle("Farming Simulator");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
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

    public void showBarn(boolean sleeping){
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
                for (int i = 255; i >= 0; i -= 20) {
                    transitionPanel.setAlpha(i, "Barn", sleeping);
                    transitionPanel.repaint();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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

            cardLayout.show(mainPanel, "Barn");
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



    public void toggleInventory(String panel){
        cardLayout.show(mainPanel, "Inventory");
        inventoryPanel.panel(panel);
    }
}
