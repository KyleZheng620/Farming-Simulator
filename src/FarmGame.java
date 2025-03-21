import javax.swing.*;
import java.awt.*;

public class FarmGame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Farm farmPanel;
    private Barn barnPanel;
    private Shop shopPanel;
    private TransitionPanel transitionPanel;

    public FarmGame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        farmPanel = new Farm(this);
        barnPanel = new Barn(this);
        shopPanel = new Shop(this);
        transitionPanel = new TransitionPanel();

        mainPanel.add(farmPanel, "Farm");
        mainPanel.add(barnPanel, "Barn");
        mainPanel.add(shopPanel, "Shop");
        mainPanel.add(transitionPanel, "TRANSITION");

        add(mainPanel);
        gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        setTitle("Farming Simulator");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }




    public void showBarn(){
        new Thread(() -> {
            cardLayout.show(mainPanel, "TRANSITION");

            for (int i = 255; i >= 0; i -= 20) {
                transitionPanel.setAlpha(i, "Barn");
                transitionPanel.repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

}
