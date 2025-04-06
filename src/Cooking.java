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
    private BufferedImage Description;
    private BufferedImage CookingBackGround;
    private BufferedImage BakedPotato;
    private BufferedImage Bread;
    private BufferedImage CookedRice;
    private BufferedImage WaterBottle;
    private BufferedImage select;
    private BufferedImage spriteSheet;
    private BufferedImage spriteSheet2;

    private FarmGame farmGame;
    private Farmer player;
    private Font customFont;
    private Font customFont2;
    private Font customFont3;
    private FoodItem currentItem;
    private int currentItemInt;
    private int mouseX;
    private int mouseY;


    private Rectangle PotatoButton;
    private Rectangle BreadButton;
    private Rectangle RiceButton;
    private Rectangle WaterButton;
    private Rectangle EatButton;
    private Rectangle XButton;
    private Rectangle[] buttons;

    public Cooking(FarmGame farmGame, Farmer player) {
        currentItem = null;
        currentItemInt = -1;
        this.farmGame = farmGame;
        this.player = player;
        Timer timer = new Timer(150, this);
        timer.start();
        PotatoButton = new Rectangle(1500,300,400,100);
        BreadButton = new Rectangle(1500,400,400,100);
        RiceButton = new Rectangle(1500,500,400,100);
        WaterButton = new Rectangle(1500,600,400,100);
        EatButton = new Rectangle(430,490,110,110);
        XButton = new Rectangle(800,400,300,100);

        buttons = new Rectangle[16];
        int i = 0;
        for (int e = 320; e < 640; e+= 87) {
            for (int f = 60; f < 390; f += 87) {
                Rectangle rec = new Rectangle(f,e,85,85);
                buttons[i] = rec;
                i++;
            }
        }

        mouseX = -1;
        mouseY = -1;

        customFont2 = FontLoader.loadFont("src/Fonts/Daydream.ttf", 20f);
        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        customFont3 = FontLoader.loadFont("src/Fonts/Daydream.ttf", 10f);

        try {
            Description = ImageIO.read(new File("src/Sprites/back.png"));
            select = ImageIO.read(new File("src/Sprites/buttonUI.png"));
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

        g.drawImage(CloseButton,800,400,300,100,null);

        int Xstart = 60;
        int YStart = 320;
        int r = 0;
        int c = 0;

        if (currentItem != null) {
            g.drawImage(Description,0,0,null);
            int x =(int) buttons[currentItemInt].getX();
            int y =(int) buttons[currentItemInt].getY();
            g.drawImage(select, x, y, x + 87, y + 87, 0, 0, 32, 32,null);
            g2d.setFont(customFont);
            g2d.setColor(new Color(56, 23,0));
            g2d.drawString(currentItem.getName() , 750, 70);
            g2d.setFont(customFont2);
            g2d.drawString("Poisoned? " + currentItem.isPoisoned(), 750, 110);
            g2d.drawString("Hunger+ " + currentItem.getHungerValue(), 750, 150);
            g2d.drawString("WaterLevel+ " + currentItem.getWaterValue(), 750, 190);
            g2d.drawString("Description:", 750, 230);

            g2d.drawString(currentItem.getDescript(), 750, 270);

        }

        g2d.setFont(customFont3);
        g2d.setColor(Color.WHITE);
        for (FoodItem food: player.getInventory().getFoodItems()) {

            r*=87;
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
                    g.drawImage(Bread, dx1 + 6, dy1 + 3, 87, 87, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Cooked Rice" -> {
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;

                    g.drawImage(CookedRice, dx1 + 6, dy1, 87, 87, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Cooked Potato" -> {
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    g.drawImage(BakedPotato, dx1 + 8, dy1, 87, 87, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }
                case "Boiled Water" -> {
                    int dx1 = Xstart + c;
                    int dy1 = YStart + r;
                    g.drawImage(WaterBottle, dx1, dy1, 87, 87, null);
                    g2d.drawString("" + food.getQuantity(), Xstart + c + 85, YStart + r +85);
                }

            }
            c/=87;
            r/=87;
            if (c == 3) {
                c = -1;
                r++;
            }
            c++;
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public int Button(int x, int y) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].contains(x,y)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (XButton.contains(mouseX,mouseY)) {
            farmGame.showBarn(1);
        }
        if (currentItemInt != -1) {
            if (EatButton.contains(mouseX,mouseY)) {
                player.getInventory().removeItem(currentItem.getName(),1);
                player.addHunger(currentItem.getHungerValue());
                player.addThirst(currentItem.getWaterValue());
                if (currentItem.getName().equals("Water")) {
                    player.setWaterPoison(true);
                } else if (currentItem.getName().equals("Boiled Water")) {
                    player.setWaterPoison(false);
                } else {
                    player.setFoodPoison(currentItem.isPoisoned());
                }
            }
        }
        if (PotatoButton.contains(mouseX,mouseY)) {
            if (player.getInventory().getQuanitiyOfItem("Potato") >= 2) {
                player.getInventory().addItem(new FoodItem("Cooked Potato",1));
                player.getInventory().removeItem("Potato", 2);
            }
        } else if (BreadButton.contains(mouseX,mouseY)) {
            if (player.getInventory().getQuanitiyOfItem("Wheat") >= 3) {
                player.getInventory().addItem(new FoodItem("Bread", 1));
                player.getInventory().removeItem("Wheat", 3);
            }
        } else if (RiceButton.contains(mouseX,mouseY)) {
            if (player.getInventory().getQuanitiyOfItem("Rice") >= 1) {
                player.getInventory().addItem(new FoodItem("Cooked Rice",1));
                player.getInventory().removeItem("Rice",1);
            }
        } else if (WaterButton.contains(mouseX,mouseY)) {
            if (player.getInventory().getQuanitiyOfItem("Water") >= 1) {
                player.getInventory().addItem(new FoodItem("Boiled Water",1));
                player.getInventory().removeItem("Water",1);
            }
        }
        int buttonPressed = Button(mouseX,mouseY);
        if ((!(buttonPressed == -1) && buttonPressed < player.getInventory().getFoodItems().size())) {
            currentItemInt = buttonPressed;
            currentItem = player.getInventory().getFoodItems().get(buttonPressed);
        } else if (currentItem != null && EatButton.contains(mouseX,mouseY) && currentItem.getQuantity() > 0) {

        } else {
                currentItem = null;
                currentItemInt = -1;
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
