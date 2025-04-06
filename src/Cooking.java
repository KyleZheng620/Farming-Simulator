import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cooking extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage health;
    private BufferedImage sign;
    private BufferedImage barnInside;
    private BufferedImage CloseButton;
    private BufferedImage CookingBackGround;
    private BufferedImage BakedPotato;
    private BufferedImage Bread;
    private BufferedImage CookedRice;
    private BufferedImage WaterBottle;
    private FarmGame farmGame;
    private Farmer player;
    private Font customFont;
    private Font customFont3;
    private int mouseX;
    private int mouseY;
    private BufferedImage spriteSheet;
    private BufferedImage spriteSheet2;

    private Rectangle PotatoButton;
    private Rectangle BreadButton;
    private Rectangle RiceButton;
    private Rectangle WaterButton;

    public Cooking(FarmGame farmGame, Farmer player) {
        this.farmGame = farmGame;
        this.player = player;
        Timer timer = new Timer(150, this);
        timer.start();

        PotatoButton = new Rectangle(1500,300,400,100);
        BreadButton = new Rectangle(1500,400,400,100);
        RiceButton = new Rectangle(1500,500,400,100);
        WaterButton = new Rectangle(1500,600,400,100);

        mouseX = -1;
        mouseY = -1;

        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        customFont3 = FontLoader.loadFont("src/Fonts/Daydream.ttf", 10f);

        try {
            sign = ImageIO.read(new File("src/Sprites/Sign.png"));
            health = ImageIO.read(new File("src/Sprites/health.png"));
            barnInside = ImageIO.read(new File("src/Sprites/Cooking.png"));
            CloseButton = ImageIO.read(new File("src/Sprites/ExitButton.png"));
            CookingBackGround = ImageIO.read(new File("src/Sprites/CookingBackground.png"));
            BakedPotato = ImageIO.read(new File("src/Sprites/BakedPotato.png"));
            CookedRice = ImageIO.read(new File("src/Sprites/CookedRice.png"));
            Bread = ImageIO.read(new File("src/Sprites/Bread.png"));
            WaterBottle = ImageIO.read(new File("src/Sprites/WaterBottle.png"));
            spriteSheet = ImageIO.read(new File("src/Sprites/crop_spritesheet-1.png-2.png"));
            spriteSheet2 = ImageIO.read(new File("src/Sprites/sprites2.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(barnInside, 0, 0, null);

        g.drawImage(CookingBackGround,0,0,null);
        for (int i = 0; i <= 1920;i +=100) {
            for (int j = 0; j <= 1080; j+=100) {
                Rectangle rec = new Rectangle(i-1, j-1, 3,3);
                g2d.fill(rec);
                g2d.setColor(Color.RED);
                g2d.draw(rec);
            }
        }
        int Xstart = 60;
        int YStart = 320;
        int r = 0;
        int c = -1;
        g2d.setFont(customFont3);
        g2d.setColor(Color.WHITE);
        System.out.println("running");
        for (FoodItem food: player.getInventory().getFoodItems()) {
            c++;
            r*=79;
            c*=87;
            switch (food.getName()) {
                case "Rice" -> {
                    int sx1 = 6 * 48;
                    int sy1 = 4 * 48;
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 87, dy1 + 87, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Potato" -> {
                    int sx1 = 0;
                    int sy1 = 7 * 48;
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 87, dy1 + 87, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Wheat" -> {
                    int sx1 = 0;
                    int sy1 = 5 * 48;
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 87, dy1 + 87, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Mandarin" -> {
                    int sx1 = 0;
                    int sy1 = 8 * 48;
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + 87, dy1 + 87, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Water" -> {
                    int sx1 = 0;
                    int sy1 = 0;

                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    g.drawImage(spriteSheet2, dx1, dy1, dx1 + 87, dy1 + 87, sx1, sy1, sx1 + 48, sy1 + 48, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Bread" -> {
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    System.out.println("x: " + dx1 + " y:" + dy1 + " R:" + r);

                    g.drawImage(Bread, dx1 + 6, dy1 + 3, 87, 87, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Cooked Rice" -> {
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    System.out.println("x: " + dx1 + " y:" + dy1 + " R:" + r);


                    g.drawImage(CookedRice, dx1 + 6, dy1, 87, 87, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Cooked Potato" -> {
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    System.out.println("x: " + dx1 + " y:" + dy1 + " R:" + r);

                    g.drawImage(BakedPotato, dx1 + 8, dy1, 87, 87, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Boiled Water" -> {
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    System.out.println("x: " + dx1 + " y:" + dy1 + " R:" + r);

                    g.drawImage(WaterBottle, dx1, dy1, 87, 87, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                }
            c/=87;
            r/=87;
            if (c == 3) {
                c = -1;
                r++;
                System.out.println("full, next row");
            }
        }

        for (int e = 320; e < 640; e+= 87) {
            for (int f = 60; f < 390; f += 87) {
                Rectangle rec = new Rectangle(f,e,85,85);
            }
        }

        for (int i = 0; i <= 1920;i +=10) {
            for (int j = 0; j <= 1080; j+=10) {
                Rectangle rec = new Rectangle(i, j, 1,1);
                g2d.fill(rec);
                g2d.setColor(Color.GREEN);
                g2d.draw(rec);
            }
        }

        int playerHunger = player.getHunger();
        int playerThirst = player.getThirst();
        for (int i = 0; i < 5; i++){
            g.drawImage(health,1600 + 60*i, 918, 1672 + 60*i,990, 649, 0, 720,72, null);
            g.drawImage(health,1600 + 60*i, 848, 1672 + 60*i,920, 577, 0, 648,72, null);
            if (playerHunger>=1){
                if (player.isFoodPoison()){
                    if (playerHunger>=2){
                        g.drawImage(health,1600 + 60*i, 918, 1672 + 60*i,990, 433, 0, 504,72, null);
                    } else {
                        g.drawImage(health,1604 + 60*i, 918, 1672 + 60*i,990, 505, 0, 576,72, null);
                    }
                } else {
                    if (playerHunger>=2){
                        g.drawImage(health,1600 + 60*i, 918, 1672 + 60*i,990, 289, 0, 360,72, null);
                    } else {
                        g.drawImage(health,1603 + 60*i, 918, 1675 + 60*i,990, 361, 0, 432,72, null);
                    }
                }
            }

            if (playerThirst>=1){
                if (player.isWaterPoison()){
                    if (playerThirst>=2){
                        g.drawImage(health,1599 + 60*i, 847, 1672 + 60*i,919, 145, 0, 216,72, null);
                    } else {
                        g.drawImage(health,1600 + 60*i, 847, 1672 + 60*i,919, 217, 0, 288,72, null);
                    }
                } else {
                    if (playerThirst>=2){
                        g.drawImage(health,1599 + 60*i, 847, 1672 + 60*i,919, 0, 0, 72,72, null);
                    } else {
                        g.drawImage(health,1600 + 60*i, 847, 1672 + 60*i,919, 73, 0, 144,72, null);
                    }
                }
            }
            playerHunger-=2;
            playerThirst-=2;
        }
        g2d.setFont(customFont);
        g2d.setColor(new Color(56, 23,0));

        g.drawImage(sign, 1650,10,null);
        g2d.drawString("DAY  " + player.getDay(), 1700, 60);
        System.out.println("finished");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (PotatoButton.contains(mouseX,mouseY)) {
            if (player.getInventory().getQuanitiyOfItem("Potato") >= 2) {
                player.getInventory().addItem(new FoodItem("Cooked Potato",1));
                player.getInventory().removeItem("Potato", 2);
                System.out.println("Cooked Potatoes");
            } else {
                System.out.println("Not enough Potatoes");
            }
        } else if (BreadButton.contains(mouseX,mouseY)) {
            if (player.getInventory().getQuanitiyOfItem("Wheat") >= 3) {
                player.getInventory().addItem(new FoodItem("Bread", 1));
                player.getInventory().removeItem("Wheat", 3);
                System.out.println("cooked bread");
            } else {
                System.out.println("Not enough Wheat");
            }
        } else if (RiceButton.contains(mouseX,mouseY)) {
            if (player.getInventory().getQuanitiyOfItem("Rice") >= 1) {
                player.getInventory().addItem(new FoodItem("Cooked Rice",1));
                player.getInventory().removeItem("Rice",1);
                System.out.println("Cooked Rice");
            } else {
                System.out.println("Not enough Rice");
            }
        } else if (WaterButton.contains(mouseX,mouseY)) {
            if (player.getInventory().getQuanitiyOfItem("Water") >= 1) {
                player.getInventory().addItem(new FoodItem("Boiled Water",1));
                player.getInventory().removeItem("Water",1);
                System.out.println("Boiled Water");
            } else {
                System.out.println("Not enough Water");
            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
