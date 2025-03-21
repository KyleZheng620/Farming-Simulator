import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FarmGame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Farm farmPanel;
    private Barn barnPanel;
    private Shop shopPanel;

    public FarmGame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        farmPanel = new Farm(this);
        barnPanel = new Barn(this);
        shopPanel = new Shop(this);

        mainPanel.add(farmPanel, "Farm");
        mainPanel.add(barnPanel, "Barn");
        mainPanel.add(shopPanel, "Shop");

        add(mainPanel);
        setTitle("Farming Simulator");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showBarn(){
        cardLayout.show(mainPanel,"Barn");
        barnPanel.requestFocusInWindow();
    }

    public void showFarm(){
        cardLayout.show(mainPanel, "Farm");
        farmPanel.requestFocusInWindow();
    }

    public void showShop(){
        cardLayout.show(mainPanel, "Shop");
        shopPanel.requestFocusInWindow();
    }
}
