import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShopMenu extends JDialog {
    private Farmer player;
    private FarmGame farmGame;
    private Font customFont;
    private JLayeredPane menuContainer;
    private BufferedImage backgroundImage;

    public ShopMenu(JFrame parent, Farmer player, FarmGame farmGame) {
        super(parent, "Shop Menu", true);
        this.player = player;
        this.farmGame = farmGame;

        setSize(1077, 950);
        setLocationRelativeTo(parent);
        setResizable(false);

        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 16f);

        menuContainer = new JLayeredPane();
        menuContainer.setPreferredSize(new Dimension(1077, 950));

        // Load background
        try {
            backgroundImage = ImageIO.read(new File("src/Sprites/ShopMenu.png"));
            JLabel background = new JLabel(new ImageIcon(backgroundImage));
            background.setBounds(0, 0, 1077, 950);
            menuContainer.add(background, JLayeredPane.DEFAULT_LAYER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create shop items panel
        JPanel itemsPanel = createShopItemsPanel();
        itemsPanel.setBounds(50, 50, 700, 450);
        menuContainer.add(itemsPanel, JLayeredPane.MODAL_LAYER);

        // Add gold display
        JLabel goldLabel = new JLabel("Gold: " + player.getMoney());
        goldLabel.setFont(customFont);
        goldLabel.setForeground(new Color(255, 215, 0));
        goldLabel.setBounds(600, 20, 200, 30);
        menuContainer.add(goldLabel, JLayeredPane.POPUP_LAYER);

        // Add close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(customFont);
        closeButton.setBounds(350, 520, 130, 30);
        closeButton.addActionListener(e -> dispose());
        menuContainer.add(closeButton, JLayeredPane.POPUP_LAYER);

        add(menuContainer);
    }

    private JPanel createShopItemsPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 20, 20));
        panel.setOpaque(false);

        addShopItem(panel, "Rice seeds", 15);
        addShopItem(panel, "Potato seeds", 12);
        addShopItem(panel, "Wheat seeds", 10);
        addShopItem(panel, "Mandarin seeds", 20);

        return panel;
    }

    private void addShopItem(JPanel panel, String itemName, int price) {
        JPanel itemPanel = new JPanel(new BorderLayout(10, 5));
        itemPanel.setBackground(new Color(255, 255, 255, 180));
        itemPanel.setBorder(BorderFactory.createLineBorder(new Color(56, 23, 0), 2));

        JLabel nameLabel = new JLabel(itemName);
        nameLabel.setFont(customFont);
        JLabel priceLabel = new JLabel(price + " gold");
        priceLabel.setFont(customFont);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setOpaque(false);
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        JButton buyButton = new JButton("Buy");
        buyButton.setFont(customFont);

        buyButton.addActionListener(e -> {
            int quantity = (int)quantitySpinner.getValue();
            int totalCost = price * quantity;

            if (player.getMoney() >= totalCost) {
                player.setMoney(player.getMoney() - totalCost);
                farmGame.getInventory().addItem(itemName, quantity);
                JOptionPane.showMessageDialog(this,
                        "Purchased " + quantity + " " + itemName + " for " + totalCost + " gold!");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Not enough gold!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        controlPanel.add(new JLabel("Quantity:"));
        controlPanel.add(quantitySpinner);
        controlPanel.add(buyButton);

        itemPanel.add(infoPanel, BorderLayout.CENTER);
        itemPanel.add(controlPanel, BorderLayout.SOUTH);

        panel.add(itemPanel);
    }
}