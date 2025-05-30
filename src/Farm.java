import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;


public class Farm extends JPanel implements KeyListener, MouseListener, ActionListener {
    private int farmerX;
    private int farmerY;
    private Font customFont;
    private BufferedImage farmer;
    private BufferedImage farmerIdle;
    private BufferedImage spriteSheet;
    private BufferedImage background;
    private BufferedImage snowbackground;
    private BufferedImage snow;
    private BufferedImage barn;
    private BufferedImage shop;
    private BufferedImage sign;
    private BufferedImage farmerRain;
    private BufferedImage farmerRainIdle;
    private BufferedImage health;
    private BufferedImage select;
    private FarmGame farmGame;
    private Rectangle barnRectangle;
    private Rectangle shopRectangle;
    private Farmer player;
    private int direction;
    private boolean moving;
    private int currentFrame;
    private Rectangle box;
    private Rectangle C_box;
    private int mouseX;
    private int mouseY;
    private Timer timer;
    private ArrayList<int[]> SnowFlakesList;
    private ArrayList<Rectangle> RainDropList;
    private SnowFlake e;
    private boolean selected;
    private boolean watering;
    private boolean harvesting;
    private boolean planting;


    public Farm(FarmGame farmGame, Farmer player) {
        this.farmGame = farmGame;
        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        barnRectangle = new Rectangle(200, 50, 130,240);
        shopRectangle = new Rectangle(1050,-10,200,300);
        box = new Rectangle(-40, 150, 1320,450);
        C_box = new Rectangle(-40,360,1180,240);
        moving = false;
        mouseX = -1;
        mouseY = -1;
        currentFrame = 0;
        farmerX = 170;
        farmerY = 300;
        direction = 2;
        SnowFlakesList = new ArrayList<>();
        e = new SnowFlake();
        selected = false;
        watering = false;
        harvesting = false;
        planting = false;

        RainDropList = new ArrayList<>();

        this.player = player;

        timer = new Timer(150, this);
        timer.start();

        try {
            select = ImageIO.read(new File("src/Sprites/select.png"));
            health = ImageIO.read(new File("src/Sprites/health.png"));
            sign = ImageIO.read(new File("src/Sprites/Sign.png"));
            background = ImageIO.read(new File("src/Sprites/background.png"));
            snowbackground = ImageIO.read(new File("src/Sprites/SnowBackGround.png"));
            snow = ImageIO.read(new File("src/Sprites/Snow.png"));
            barn = ImageIO.read(new File("src/Sprites/Barn.png"));
            farmer = ImageIO.read(new File("src/Sprites/farmer.png"));
            farmerIdle = ImageIO.read(new File("src/Sprites/farmer_idle.png"));
            farmerRain = ImageIO.read(new File("src/Sprites/farmerRain.png"));
            farmerRainIdle = ImageIO.read(new File("src/Sprites/farmerRainIdle.png"));
            spriteSheet = ImageIO.read(new File("src/Sprites/crop_spritesheet-1.png-2.png"));
            shop = ImageIO.read(new File("src/Sprites/ShopOutside.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        if (player.getCurrentWeather().equals("Snowy")) {
            g.drawImage(snowbackground,0,0,null);
        } else {
            g.drawImage(background, 0, 0, null);
        }

        g.drawImage(barn, 30,50,null);
        g.drawImage(shop, 900,-80, null);


        if (player.getCurrentWeather().equals("Snowy")) {
            if (!(SnowFlakesList.size() >= 20)) {
                if (Math.random() < 0.1) {
                    SnowFlakesList.add(e.getXandY((int)(Math.random()*10)));
                }
            }
            for (int i = 0; i < SnowFlakesList.size(); i++) {
                int[] list = SnowFlakesList.get(i);
                int x = list[0];
                int y = list[1];
                int xLength = list[2];
                int yLength = list[3];
                int locationX = list[4];
                int locationY = list[5];
                g.drawImage(snow,locationX,locationY,locationX+100,locationY+100,x,y,x +xLength,y +yLength,null);
                SnowFlakesList.get(i)[5] += 10;
                if (list[5] >= 1080) {
                    SnowFlakesList.remove(list);
                    i--;
                }
            }
        } else if (player.getCurrentWeather().equals("Rain")) {
            if (!(RainDropList.size() >= 60)) {
                if (Math.random() < 0.3) {
                    RainDropList.add(new Rectangle((int)(Math.random() * 1920),0,3,20));
                }
            }
            for (int i = 0; i < RainDropList.size(); i++) {
                Rectangle rain = RainDropList.get(i);
                int locationX = (int)rain.getX();
                int locationY = (int)rain.getY();
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(new Color(101,123,180));
                g2d.fill(rain);
                g2d.draw(rain);
                rain.setBounds(locationX,locationY + 10, 3, 20);
                if (rain.getY() >= 1080) {
                    RainDropList.remove(rain);
                    i--;
                }
            }

        }

        int spriteWidth = 48;
        int spriteHeight = 48;
        for (int c = 0; c < player.getFarmPlots().getPlots()[0].length; c++){
            for (int r = 0; r < player.getFarmPlots().getPlots().length; r++){
                if (player.getFarmPlots().getPlots()[r][c].getCrop().equals("Rice")){
                    int sx1 = 11 * spriteWidth - (4- player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                    int sy1 = 4 * spriteHeight;
                    int dx1 = 35 + (54 * c);
                    int dy1 = 462 + (55 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                } else if (player.getFarmPlots().getPlots()[r][c].getCrop().equals("Potato")){
                    int sx1 = 5 * spriteWidth - (4- player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                    int sy1 = 7 * spriteHeight;
                    int dx1 = 35 + (54 * c);
                    int dy1 = 462 + (55 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                } else if (player.getFarmPlots().getPlots()[r][c].getCrop().equals("Wheat")){
                    int sx1 = 5 * spriteWidth - (4- player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                    int sy1 = 5 * spriteHeight;
                    int dx1 = 35 + (54 * c);
                    int dy1 = 462 + (55 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                } else if ((player.getFarmPlots().getPlots()[r][c].getCrop().equals("Mandarin"))){
                    int sx1 = 5 * spriteWidth - (4- player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                    int sy1 = 8 * spriteHeight;
                    int dx1 = 35 + (54 * c);
                    int dy1 = 462 + (55 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                }
            }
        }
        g2d.setFont(customFont);
        g2d.setColor(new Color(56, 23,0));
        g.drawImage(sign, 1650,10,null);
        g2d.drawString("DAY  " + player.getDay(), 1700, 60);

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

        int fx1 = direction * 128;
        int fy1 = currentFrame * 128;
        int fx2 = fx1 + 128;
        int fy2 = fy1 + 128;
        if (!player.getCurrentWeather().equals("Rain")){
            if (moving){
                g.drawImage(farmer, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
            } else {
                g.drawImage(farmerIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
            }
        } else {
            if (moving) {
                g.drawImage(farmerRain, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
            } else {
                g.drawImage(farmerRainIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
            }
        }
        double row = (mouseY - 465) / 55.0;
        double col = (mouseX - 35) / 54.0;
        if (selected){
            if (row >= 0 && row < 4 && col >= 0 && col < 21) {
                int x = (int) col * 54 + 32;
                int y = (int) row * 55 + 462 - 141;
                g.drawImage(select, x, y, null);
            }
        }
        if (watering){
            farmGame.showAnimations(row, col, 1, farmerX, farmerY, direction);
            watering = false;
        }
        if (harvesting){
            farmGame.showAnimations(row, col, 2, farmerX, farmerY, direction);
            harvesting = false;
        }
        if (planting){
            farmGame.toggleInventory((int) row, (int) col, farmerX, farmerY, direction);
            planting = false;
        }
    }

    private boolean collidesWithBarn(int x, int y){
        Rectangle player = new Rectangle(x,y,128,128);
        return(player.intersects(barnRectangle));
    }

    private boolean collidesWithShop(int x, int y){
        Rectangle player = new Rectangle(x,y,128,128);
        return(player.intersects(shopRectangle));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame = (currentFrame + 1) % 4;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    private boolean WithinTheBarn(int x, int y) {
        return box.contains(x,y) && !C_box.contains(x,y);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int Xchange = 0;
        int Ychange = 0;
        if (e.getKeyCode() == KeyEvent.VK_E){
            farmGame.toggleInventory("Farm");
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                moving = true;
                Ychange -= 10;
                direction = 0;
                break;
            case KeyEvent.VK_S:
                moving = true;
                Ychange += 10;
                direction = 2;
                break;
            case KeyEvent.VK_A:
                moving = true;
                Xchange -= 10;
                direction = 3;
                break;
            case KeyEvent.VK_D:
                moving = true;
                Xchange += 10;
                direction = 1;
                if (farmerX == 1270 && player.getInventory().itemAmount("Boat") >= 1){
                    farmGame.showWinLose(3);
                }
                break;
        };
        if (selected){
            int row = (mouseY - 465) / 55;
            int col = (mouseX - 35) / 54;
            Crop[][] plots = player.getFarmPlots().getPlots();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_1:
                    if (plots[row][col].waterCrop(player)){
                        watering = true;
                        selected = false;
                    }
                    break;
                case KeyEvent.VK_2:
                    if (plots[row][col].harvestCrop(player)){
                        harvesting = true;
                        selected = false;
                    }
                    break;
                case KeyEvent.VK_3:
                    if (plots[row][col].getCrop().equals("Soil")){
                        selected = false;
                        planting = true;
                    }
                    break;
            };
        }
        if (WithinTheBarn(farmerX + Xchange, farmerY + Ychange)) {
            farmerY += Ychange;
            farmerX += Xchange;
        }

        if (collidesWithBarn(farmerX, farmerY)){
            farmGame.showBarn(false, true);
            farmerX = 170;
            farmerY = 300;
            direction = 2;
            moving = false;
            selected = false;
        }
        if (collidesWithShop(farmerX, farmerY)){
            farmGame.showShop();
            try {
                Thread.sleep(20);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
            farmerX = 1000;
            farmerY = 300;
            direction = 2;
            moving = false;
            selected = false;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        moving = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        double row = (mouseY - 465) / 55.0;
        double col = (mouseX - 35) / 54.0;

        selected = row >= 0 && row < 4 && col >= 0 && col < 21;

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {
        moving = false;
    }
    public Crop[][] getPlots() {
        return player.getFarmPlots().getPlots();
    }

    public int getFarmerX(){
        return farmerX;
    }
    public int getFarmerY(){
        return farmerY;
    }
}