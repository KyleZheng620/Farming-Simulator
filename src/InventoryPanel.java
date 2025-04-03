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
    private Font customFont3;
    private Inventory inventory;
    private BufferedImage spriteSheet;
    private BufferedImage inventoryImage;
    private BufferedImage spriteSheet2;
    private BufferedImage BakedPotato;
    private BufferedImage Bread;
    private BufferedImage CookedRice;
    private BufferedImage seeds;
    private String panel;
    private boolean toggleInventory;
    private FarmGame farmGame;
    private Farmer player;
    private boolean planting;
    private int row;
    private int col;
    private int farmerX;
    private int farmerY;
    private int direction;
    private String one;
    private String two;
    private String three;
    private String four;

    public InventoryPanel(Inventory inventory, FarmGame farmGame, Farmer player) {
        planting = false;
        this.player = player;
        this.farmGame = farmGame;
        panel = "";
        this.inventory = inventory;
        row = -1;
        col = -1;
        one = "";
        two = "";
        three = "";
        four = "";
        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 18f);
        customFont2 = FontLoader.loadFont("src/Fonts/Daydream.ttf", 35f);
        customFont3 = FontLoader.loadFont("src/Fonts/Daydream.ttf", 10f);
        try {
            inventoryImage = ImageIO.read(new File("src/Sprites/inventory.png"));
            spriteSheet = ImageIO.read(new File("src/Sprites/crop_spritesheet-1.png-2.png"));
            spriteSheet2 = ImageIO.read(new File("src/Sprites/sprites2.png"));
            seeds = ImageIO.read(new File("src/Sprites/SeedBag.png"));
            BakedPotato = ImageIO.read(new File("src/Sprites/BakedPotato.png"));
            CookedRice = ImageIO.read(new File("src/Sprites/CookedRice.png"));
            Bread = ImageIO.read(new File("src/Sprites/Bread.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addMouseListener(this);
        addKeyListener(this);
    }

    public void panel(String panel) {
        setBackground(new Color(50, 50, 50, 220));
        planting = false;
        this.panel = panel;
        toggleInventory = true;
        setFocusable(true);
        requestFocusInWindow();
    }

    public void panel(int row, int col,int farmerX, int farmerY, int direction) {
        setBackground(new Color(0, 0, 0, 0));
        this.direction = direction;
        this.farmerX = farmerX;
        this.farmerY = farmerY;
        this.row = row;
        this.col = col;
        this.planting = true;
        this.panel = "Farm";
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

        if (!planting) {
            g.drawImage(inventoryImage, 0, 0, null);
        } else {
            g.drawImage(seeds,farmerX+30,farmerY+100,null);
        }
        ArrayList<Item> items = inventory.getItems();

        int r = 0;
        int c = 0;
        if (!planting) {
            g2d.setFont(customFont);
            for (Item item : items) {
                c++;
                switch (item.getName()) {
                    case "Rice seeds" -> {
                        int sx1 = 11 * 48;
                        int sy1 = 4 * 48;
                        int dx1 = 475 + (87 * c);
                        int dy1 = 450 + (88 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                        g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                    }
                    case "Potato seeds" -> {
                        int sx1 = 5 * 48;
                        int sy1 = 7 * 48;
                        int dx1 = 475 + (87 * c);
                        int dy1 = 450 + (88 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                        g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                    }
                    case "Wheat seeds" -> {
                        int sx1 = 5 * 48;
                        int sy1 = 5 * 48;
                        int dx1 = 475 + (87 * c);
                        int dy1 = 450 + (88 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + 48, dy1 + 48, sx1, sy1, sx1 + 48, sy1 + 48, null);
                        g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
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
                    case "Bread" -> {
                        int dx1 = 470 + (87 * c);
                        int dy1 = 445 + (88 * r);
                        g.drawImage(Bread, dx1 + 6, dy1 + 3, 48, 48, null);
                        g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                    }
                    case "Cooked Rice" -> {
                        int dx1 = 470 + (87 * c);
                        int dy1 = 445 + (88 * r);
                        g.drawImage(CookedRice, dx1 + 6, dy1, 48, 48, null);
                        g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                    }
                    case "Cooked Potato" -> {
                        int dx1 = 470 + (87 * c);
                        int dy1 = 445 + (88 * r);
                        g.drawImage(BakedPotato, dx1 + 8, dy1, 48, 48, null);
                        g2d.drawString("" + item.getQuantity(), 525 + (87 * c), 500 + (88 * r));
                    }

                }
                if (c == 8) {
                    c = 0;
                    r++;
                }
            }
        } else {
            for (Item item : items) {
                g2d.setFont(customFont3);
                g2d.setColor(Color.darkGray);
                switch (item.getName()) {
                    case "Rice seeds" -> {
                        int sx1 = 11 * 48;
                        int sy1 = 4 * 48;
                        int dx1 = farmerX + (53 * c) + 50;
                        int dy1 = farmerY + (120);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + 32, dy1 + 32, sx1, sy1, sx1 + 48, sy1 + 48, null);
                        g2d.drawString("" + item.getQuantity(), farmerX + (53 * c) + 77, farmerY + (160));
                        g2d.setFont(customFont3);
                        g2d.setColor(Color.gray);
                        g2d.drawString("" + item.getQuantity(), farmerX + (53 * c) + 75, farmerY + (160));
                        c++;
                        if (c == 1) {
                            one = item.getName();
                        } else if (c == 2) {
                            two = item.getName();
                        } else if (c == 3) {
                            three = item.getName();
                        } else if (c == 4) {
                            four = item.getName();
                        }
                    }
                    case "Potato seeds" -> {
                        int sx1 = 5 * 48;
                        int sy1 = 7 * 48;
                        int dx1 = farmerX + (53 * c) + 50;
                        int dy1 = farmerY + (120);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + 32, dy1 + 32, sx1, sy1, sx1 + 48, sy1 + 48, null);
                        g2d.drawString("" + item.getQuantity(), farmerX + (53 * c) + 77, farmerY + (160));
                        g2d.setFont(customFont3);
                        g2d.setColor(Color.gray);
                        g2d.drawString("" + item.getQuantity(), farmerX + (53 * c) + 75, farmerY + (160));
                        c++;
                        if (c == 1) {
                            one = item.getName();
                        } else if (c == 2) {
                            two = item.getName();
                        } else if (c == 3) {
                            three = item.getName();
                        } else if (c == 4) {
                            four = item.getName();
                        }
                    }
                    case "Wheat seeds" -> {
                        int sx1 = 5 * 48;
                        int sy1 = 5 * 48;
                        int dx1 = farmerX + (53 * c) + 50;
                        int dy1 = farmerY + (120);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + 32, dy1 + 32, sx1, sy1, sx1 + 48, sy1 + 48, null);
                        g2d.drawString("" + item.getQuantity(), farmerX + (53 * c) + 77, farmerY + (160));
                        g2d.setFont(customFont3);
                        g2d.setColor(Color.gray);
                        g2d.drawString("" + item.getQuantity(), farmerX + (53 * c) + 75, farmerY + (160));
                        c++;
                        if (c == 1) {
                            one = item.getName();
                        } else if (c == 2) {
                            two = item.getName();
                        } else if (c == 3) {
                            three = item.getName();
                        } else if (c == 4) {
                            four = item.getName();
                        }
                    }
                    case "Mandarin seeds" -> {
                        int sx1 = 5 * 48;
                        int sy1 = 8 * 48;
                        int dx1 = farmerX + (53 * c) + 50;
                        int dy1 = farmerY + (120);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + 32, dy1 + 32, sx1, sy1, sx1 + 48, sy1 + 48, null);
                        g2d.drawString("" + item.getQuantity(), farmerX + (53 * c) + 77, farmerY + (160));
                        g2d.setFont(customFont3);
                        g2d.setColor(Color.gray);
                        g2d.drawString("" + item.getQuantity(), farmerX + (53 * c) + 75, farmerY + (160));
                        c++;
                        if (c == 1) {
                            one = item.getName();
                        } else if (c == 2) {
                            two = item.getName();
                        } else if (c == 3) {
                            three = item.getName();
                        } else if (c == 4) {
                            four = item.getName();
                        }
                    }
                }
            }
        }
        if (!planting) {
            g2d.setColor(new Color(127, 63, 49));
            g2d.setFont(customFont2);
            g2d.drawString("Gold", 1250, 550);
            g2d.drawString("" + player.getMoney(), 1300, 600);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E) {
            toggleInventory = false;
            repaint();
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
                if (!one.isEmpty()) {
                    player.getFarmPlots().getPlots()[row][col].plantCrop(one, player);
                    farmGame.showAnimations(row, col, 3, farmerX, farmerY, direction);
                    break;
                }
            case KeyEvent.VK_2:
                if (!two.isEmpty()) {
                    player.getFarmPlots().getPlots()[row][col].plantCrop(two, player);
                    farmGame.showAnimations(row, col, 3, farmerX, farmerY, direction);
                    break;
                }
            case KeyEvent.VK_3:
                if (!three.isEmpty()) {
                    player.getFarmPlots().getPlots()[row][col].plantCrop(three, player);
                    farmGame.showAnimations(row, col, 3, farmerX, farmerY, direction);
                    break;
                }
            case KeyEvent.VK_4:
                if (!four.isEmpty()) {
                    player.getFarmPlots().getPlots()[row][col].plantCrop(four, player);
                    farmGame.showAnimations(row, col, 3, farmerX, farmerY, direction);
                    break;
                }
        };
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