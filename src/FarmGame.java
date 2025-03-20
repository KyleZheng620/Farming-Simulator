import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class FarmGame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private DisplayPanel farmPanel;
    private Barn barnPanel;

    public FarmGame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        farmPanel = new DisplayPanel(this);
        barnPanel = new Barn();

        mainPanel.add(farmPanel, "Farm");
        mainPanel.add(barnPanel, "Barn");

        add(mainPanel);
    }

    public void showBarn(){
        cardLayout.show(mainPanel,"House");
    }

    public void showFarm(){
        cardLayout.show(mainPanel, "Farm");
    }
}
