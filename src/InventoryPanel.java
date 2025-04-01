import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class InventoryPanel extends JPanel implements KeyListener, MouseListener, ActionListener {
    private Font customFont;
    private Font customFont2;
    private Inventory inventory;
    private BufferedImage spriteSheet;
    private BufferedImage inventoryImage;
    private BufferedImage spriteSheet2;
    private String panel;
    private boolean toggleInventory;
    private FarmGame farmGame;
    private Farmer player;

    public InventoryPanel(Inventory inventory, FarmGame farmGame, Farmer player) {
        this.player = player;
        this.farmGame = farmGame;
        panel = "";
        this.inventory = inventory;
        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 18f);
        customFont2 = FontLoader.loadFont("src/Fonts/Daydream.ttf", 35f);
        setBackground(new Color(50, 50, 50, 220));
        try {
            inventoryImage = ImageIO.read(new File("src/Sprites/inventory.png"));
            spriteSheet = ImageIO.read(new File("src/Sprites/crop_spritesheet-1.png-2.png"));
            spriteSheet2 = ImageIO.read(new File("src/Sprites/sprites2.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addMouseListener(this);
        addKeyListener(this);
    }

    public void panel(String panel) {
        this.panel = panel;
        toggleInventory = true;
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!toggleInventory) {
            switch (panel) {
                case "Farm" -> farmGame.showFarm(0);
                case "Barn" -> farmGame.showBarn(0);
                case "Shop" -> farmGame.showShop(0);
            }
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(customFont);

        g.drawImage(inventoryImage, 0, 0, null);
        ArrayList<Item> items = inventory.getItems();

        int r = 0;
        int c = 0;
        for (Item item : items) {
            c++;
            switch (item.getName()) {
                case "Rice seeds" -> {
                    int sx1 = 11 * 48;
                    int sy1 = 4 * 48;
                    int dx1 = 475 + (87 * c);
                    int dy1 = 450 + (88 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (90 * r));
                }
                case "Potato seeds" -> {
                    int sx1 = 5 * 48;
                    int sy1 = 7 * 48;
                    int dx1 = 475 + (87 * c);
                    int dy1 = 450 + (88 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (90 * r));
                }
                case "Wheat seeds" -> {
                    int sx1 = 5 * 48;
                    int sy1 = 5 * 48;
                    int dx1 = 475 + (87 * c);
                    int dy1 = 450 + (88 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (90 * r));
                }
                case "Mandarin seeds" -> {
                    int sx1 = 5 * 48;
                    int sy1 = 8 * 48;
                    int dx1 = 475 + (87 * c);
                    int dy1 = 450 + (88 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                }
                case "Rice" -> {
                    int sx1 = 6 * 48;
                    int sy1 = 4 * 48;
                    int dx1 = 475 + (87 * c);
                    int dy1 = 445 + (88 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                }
                case "Potato" -> {
                    int sx1 = 0;
                    int sy1 = 7 * 48;
                    int dx1 = 475 + (87 * c);
                    int dy1 = 445 + (88 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                }
                case "Wheat" -> {
                    int sx1 = 0;
                    int sy1 = 5 * 48;
                    int dx1 = 475 + (87 * c);
                    int dy1 = 445 + (88 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                }
                case "Mandarin" -> {
                    int sx1 = 0;
                    int sy1 = 8 * 48;
                    int dx1 = 475 + (87 * c);
                    int dy1 = 445 + (88 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                }
                case "Water" -> {
                    int sx1 = 0;
                    int sy1 = 0;
                    int dx1 = 470 + (87 * c);
                    int dy1 = 445 + (88 * r);
                    g.drawImage(spriteSheet2, dx1, dy1, dx1 + 64, dy1 + 64, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                }
            }
            if (c == 8) {
                c = 0;
                r++;
            }
        }
        g2d.setColor(new Color(127,63,49));
        g2d.setFont(customFont2);
        g2d.drawString("Gold", 1250, 550);
        g2d.drawString("" + player.getMoney(), 1300, 600);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E) {
            toggleInventory = false;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}