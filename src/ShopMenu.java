import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShopMenu extends JDialog {
    private JPanel descriptionPanel;
    private Font customFont;
    private BufferedImage itemSprites;
    private BufferedImage background;
    private Farmer player;
    private FarmGame farmGame;
    private BufferedImage descriptionBg;
    
    public ShopMenu(JFrame parent, Farmer player, FarmGame farmGame) {
        super(parent, "Shop Menu", true);
        this.player = player;
        this.farmGame = farmGame;
        setLayout(new BorderLayout());
        // Load sprites and background once
        try {
            itemSprites = ImageIO.read(new File("src/Sprites/sprites2.png"));
            background = ImageIO.read(new File("src/Sprites/ShopMenu.png"));
            descriptionBg = ImageIO.read(new File("src/Sprites/DescriptionBg (1).png"));
            customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 16f);
            
            // Set single background for entire menu
            setContentPane(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(background,0,0,null);
                }
            });
            getContentPane().setLayout(new BorderLayout());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Adjust layout to match background boxes
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setOpaque(false);
        splitPane.setDividerLocation(300);
        splitPane.setBorder(null);
        
        // Make buy panel transparent
        JPanel buyPanel = new JPanel(new BorderLayout());
        buyPanel.setOpaque(false);

        JPanel goldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        goldPanel.setOpaque(false);
        JLabel goldLabel = new JLabel("Gold: " + player.getMoney() + " ◆", SwingConstants.RIGHT);
        goldLabel.setFont(customFont.deriveFont(16f));
        goldLabel.setForeground(new Color(255, 215, 0));
        goldPanel.add(goldLabel);

        buyPanel.add(goldPanel, BorderLayout.NORTH);
        
        // Remove duplicate background loading code
        
        // Adjust grid to match background boxes
        JPanel itemGrid = new JPanel(new GridLayout(2, 4, 20, 20));
        itemGrid.setOpaque(false);
        itemGrid.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        // Make description panel transparent
        descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setOpaque(false);
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 40));
        descriptionPanel.setPreferredSize(new Dimension(300, 200));
        
        // Add items to grid including boat - fixed sprite coordinates
        addShopItem(itemGrid, "Rice seeds", 14, 2, 0, "Rice seeds for planting.\nNeeds regular watering.");
        addShopItem(itemGrid, "Potato seeds", 10, 4, 0, "Potato seeds for planting.\nHardy crop.");
        addShopItem(itemGrid, "Wheat seeds", 7, 5, 0, "Wheat seeds for planting.\nBasic crop.");
        addShopItem(itemGrid, "Mandarin seeds", 17, 3, 0, "Mandarin seeds for planting.\nPremium crop.");
        addShopItem(itemGrid, "Water", 4, 0, 0, "Water for your crops.\nKeeps plants healthy.");
        addShopItem(itemGrid, "Boat", 200, 1, 0, "A sturdy fishing boat.\nAllows you to row into the horizon.");
        // Removed duplicate water item
        
        // Layout for buy panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(itemGrid, BorderLayout.CENTER);
        topPanel.add(descriptionPanel, BorderLayout.EAST);
        
        buyPanel.add(topPanel, BorderLayout.CENTER);
        
        // Add to split pane
        splitPane.setTopComponent(buyPanel);
        splitPane.setBottomComponent(createSellPanel(farmGame));
        
        add(splitPane, BorderLayout.CENTER);
        setSize(1200, 700);
        setLocationRelativeTo(parent);
    }
    
    private void addShopItem(JPanel grid, String itemName, int price, int spriteX, int spriteY, String description) {
        JPanel itemPanel = new JPanel(new BorderLayout(5, 5)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(245, 222, 179, 180));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.setColor(new Color(139, 69, 19));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 10, 10);
            }
        };
        itemPanel.setOpaque(false);

        // Add hover effect
        itemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                itemPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
                itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                itemPanel.setBorder(null);
                itemPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Style the labels with pixel-like font and colors
        JLabel nameLabel = new JLabel(itemName, SwingConstants.CENTER);
        nameLabel.setFont(customFont.deriveFont(14f));
        nameLabel.setForeground(new Color(101, 67, 33));

        JLabel priceLabel = new JLabel(price + " gold", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(255, 215, 0));
                g2d.drawString("◆", 5, getHeight() - 5);
            }
        };
        priceLabel.setFont(customFont.deriveFont(12f));
        priceLabel.setForeground(new Color(139, 69, 19));
        
        // Create a custom icon label instead of a panel
        JLabel iconLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (itemSprites != null) {
                    // Draw the sprite with proper scaling
                    int iconSize = Math.min(getWidth(), getHeight()) - 10;
                    int x = (getWidth() - iconSize) / 2;
                    int y = (getHeight() - iconSize) / 2;
                    
                    g.drawImage(itemSprites, x, y, x + iconSize, y + iconSize, spriteX * 48, spriteY * 48, (spriteX + 1) * 48, (spriteY + 1) * 48,
                        this);
                }
            }
            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(48, 48);
            }
            
            @Override
            public Dimension getMinimumSize() {
                return new Dimension(32, 32);
            }
        };
        
        // Center the icon in a panel
        JPanel iconContainer = new JPanel(new GridBagLayout());
        iconContainer.setOpaque(false);
        iconContainer.add(iconLabel);
        
        // Remove duplicate label declarations and keep the rest of the code
        itemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                updateDescription(itemName, description, price);
            }
        });
        
        itemPanel.add(iconContainer, BorderLayout.CENTER);
        itemPanel.add(nameLabel, BorderLayout.NORTH);
        itemPanel.add(priceLabel, BorderLayout.SOUTH);
        
        grid.add(itemPanel);
    }
    
    private void updateDescription(String itemName, String description, int price) {
        descriptionPanel.removeAll();
        
        // Create a panel with the wooden background
        JPanel woodPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (descriptionBg != null) {
                    g.drawImage(descriptionBg, 0, -50, getWidth(), getHeight(), this);
                }
            }
        };
        woodPanel.setOpaque(false);
        woodPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextArea descArea = new JTextArea(description);
        descArea.setFont(customFont.deriveFont(12f));
        descArea.setForeground(new Color(101, 67, 33));
        descArea.setBackground(new Color(0, 0, 0, 0));
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        
        woodPanel.add(descArea, BorderLayout.CENTER);
        
        // If there's a buy button, add it to the wood panel
        if (price > 0) {
            JButton buyButton = new JButton("Buy for " + price + " gold");
            buyButton.setFont(customFont.deriveFont(12f));
            buyButton.setForeground(Color.WHITE);
            buyButton.setBackground(new Color(139, 69, 19));
            buyButton.setFocusPainted(false);
            buyButton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 215, 0), 2),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            
            buyButton.addActionListener(e -> {
                if (player.getMoney() >= price) {
                    player.setMoney(player.getMoney() - price);
                    
                    // Add the purchased item to player's inventory
                    if (itemName.equals("Water")) {
                        farmGame.getInventory().addItem(new FoodItem(itemName, 1));
                    } else {
                        farmGame.getInventory().addItem(new Item(itemName, 1));
                    }
                    
                    JOptionPane.showMessageDialog(this, 
                        "You bought " + itemName + " for " + price + " gold!", 
                        "Purchase Successful", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // Refresh the UI
                    farmGame.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Not enough gold! You need " + price + " gold.", 
                        "Purchase Failed", 
                        JOptionPane.WARNING_MESSAGE);
                }
            });
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setOpaque(false);
            buttonPanel.add(buyButton);
            
            woodPanel.add(buttonPanel, BorderLayout.SOUTH);
        }
        
        descriptionPanel.add(woodPanel);
        descriptionPanel.revalidate();
        descriptionPanel.repaint();}
    
    
    private JPanel createSellPanel(FarmGame farmGame) {
        JPanel sellPanel = new JPanel(new BorderLayout(15, 0));
        sellPanel.setOpaque(false);
    
        // Create description panel first with wooden background - removed outline
        JPanel sellDescriptionPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (descriptionBg != null) {
                    g.drawImage(descriptionBg, 0, -50, getWidth(), getHeight(), this);
                }
            }
        };
        sellDescriptionPanel.setOpaque(false);
        sellDescriptionPanel.setPreferredSize(new Dimension(300, 200));
        sellDescriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Left side - Inventory Panel with solid brown background matching the outline
        JPanel inventoryPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(139, 69, 19)); // Solid brown color matching the outline
                g2d.setStroke(new BasicStroke(2)); // Same boldness as outline
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            }
        };
        inventoryPanel.setOpaque(false);
        
        // Title for inventory - update text color for better visibility on brown background
        JLabel inventoryTitle = new JLabel("Your Items", SwingConstants.CENTER);
        inventoryTitle.setFont(customFont.deriveFont(16f));
        inventoryTitle.setForeground(new Color(255, 223, 186)); // Lighter color for better visibility
        inventoryTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Grid for inventory items
        JPanel inventoryGrid = new JPanel(new GridLayout(0, 3, 10, 10));
        inventoryGrid.setOpaque(false);
        inventoryGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Add items to grid
        for (Item item : player.getInventory().getItems()) {
            if (item instanceof CropItem) {
                addSellableItem(inventoryGrid, (CropItem) item, sellDescriptionPanel);
            }
        }
    
        // Scrollable inventory
        JScrollPane scrollPane = new JScrollPane(inventoryGrid);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    
        inventoryPanel.add(inventoryTitle, BorderLayout.NORTH);
        inventoryPanel.add(scrollPane, BorderLayout.CENTER);
    
        // Initial message
        JLabel initialMessage = new JLabel("Select an item to sell", SwingConstants.CENTER);
        initialMessage.setFont(customFont.deriveFont(14f));
        initialMessage.setForeground(new Color(139, 69, 19));
        sellDescriptionPanel.add(initialMessage, BorderLayout.CENTER);
    
        // Add panels to main sell panel
        sellPanel.add(inventoryPanel, BorderLayout.CENTER);
        sellPanel.add(sellDescriptionPanel, BorderLayout.EAST);
    
        return sellPanel;
    }

    private void addSellableItem(JPanel grid, CropItem item, JPanel descriptionPanel) {
        JPanel itemPanel = new JPanel(new BorderLayout(5, 5)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(245, 222, 179, 180));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.setColor(new Color(139, 69, 19));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 10, 10);
            }
        };
        itemPanel.setOpaque(false);
    
        // Calculate sell price (90% of buy price)
        int sellPrice = item.getCropSellPrice();
    
        JLabel nameLabel = new JLabel(item.getName(), SwingConstants.CENTER);
        nameLabel.setFont(customFont.deriveFont(14f));
        nameLabel.setForeground(new Color(101, 67, 33));
    
        JLabel quantityLabel = new JLabel("Owned: " + item.getQuantity(), SwingConstants.CENTER);
        quantityLabel.setFont(customFont.deriveFont(12f));
        quantityLabel.setForeground(new Color(139, 69, 19));
        
        // Add crop icon based on item name
        JLabel iconLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (itemSprites != null) {
                    int iconSize = Math.min(getWidth(), getHeight()) - 10;
                    int x = (getWidth() - iconSize) / 2;
                    int y = (getHeight() - iconSize) / 2;
                    
                    // Set sprite coordinates based on crop type
                    int spriteX = 0;
                    int spriteY = 0;
                    
                    if (item.getName().equals("Wheat")) {
                        spriteX = 0;
                        spriteY = 5;
                    } else if (item.getName().equals("Rice")) {
                        spriteX = 2;
                        spriteY = 0;
                    } else if (item.getName().equals("Potato")) {
                        spriteX = 4;
                        spriteY = 0;
                    } else if (item.getName().equals("Mandarin")) {
                        spriteX = 3;
                        spriteY = 0;
                    }
                    
                    g.drawImage(itemSprites, 
                        x, y, x + iconSize, y + iconSize,
                        spriteX * 48, spriteY * 48, (spriteX + 1) * 48, (spriteY + 1) * 48,
                        this);
                }
            }
            
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(48, 48);
            }
            
            @Override
            public Dimension getMinimumSize() {
                return new Dimension(32, 32);
            }
        };
        
        // Center the icon in a panel
        JPanel iconContainer = new JPanel(new GridBagLayout());
        iconContainer.setOpaque(false);
        iconContainer.add(iconLabel);
    
        itemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateSellDescription(descriptionPanel, item, sellPrice);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                itemPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
                itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                itemPanel.setBorder(null);
                itemPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    
        itemPanel.add(nameLabel, BorderLayout.NORTH);
        itemPanel.add(iconContainer, BorderLayout.CENTER); // Add the icon container
        itemPanel.add(quantityLabel, BorderLayout.SOUTH);
        
        grid.add(itemPanel);
    }

    private void updateSellDescription(JPanel descriptionPanel, CropItem item, int sellPrice) {
        descriptionPanel.removeAll();
        
        // Create a panel with the wooden background
        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(customFont.deriveFont(14f));

        String description = item.getItemDescription();
        JTextArea descArea = new JTextArea(description);
        descArea.setFont(customFont.deriveFont(12f));
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setOpaque(false);

        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, item.getQuantity(), 1));
        quantitySpinner.setFont(customFont.deriveFont(12f));

        // Make the sell button more visible
        JButton sellButton = new JButton("Sell for " + sellPrice + " gold");
        sellButton.setFont(customFont.deriveFont(12f));
        sellButton.setForeground(Color.WHITE);
        sellButton.setBackground(new Color(139, 69, 19));
        sellButton.setFocusPainted(false);
        sellButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 215, 0), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        sellButton.addActionListener(e -> {
            int quantity = (int)quantitySpinner.getValue();
            int totalPrice = sellPrice * quantity;
            
            // Remove item from game inventory only
            farmGame.getInventory().removeItem(item.getName(), quantity);
            
            // Add money to player
            player.setMoney(player.getMoney() + totalPrice);
            
            JOptionPane.showMessageDialog(this, 
                "Sold " + quantity + " " + item.getName() + " for " + totalPrice + " gold!");
            
            // Refresh the shop menu
            Window window = SwingUtilities.getWindowAncestor(descriptionPanel);
            if (window instanceof JDialog) {
                window.dispose();
                new ShopMenu((JFrame)farmGame, player, farmGame).setVisible(true);
            }
        });

        // Improve the sell panel layout
        JPanel sellPanel = new JPanel();
        sellPanel.setLayout(new BoxLayout(sellPanel, BoxLayout.Y_AXIS));
        sellPanel.setOpaque(false);
        
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        quantityPanel.setOpaque(false);
        quantityPanel.add(new JLabel("Quantity:"));
        quantityPanel.add(quantitySpinner);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(sellButton);
        
        sellPanel.add(quantityPanel);
        sellPanel.add(Box.createVerticalStrut(10));
        sellPanel.add(buttonPanel);

        content.add(nameLabel, BorderLayout.NORTH);
        content.add(descArea, BorderLayout.CENTER);
        content.add(sellPanel, BorderLayout.SOUTH);

        descriptionPanel.add(content);
        descriptionPanel.revalidate();
        descriptionPanel.repaint();
    }
}