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
    private FarmGame farmGame;
    private Rectangle barnRectangle;
    private Rectangle shopRectangle;
    private FarmLand farmPlots;
    private Farmer player;
    private int direction;
    private boolean moving;
    private int currentFrame;
    private Rectangle box;
    private int day = 1;
    ArrayList<int[]> SnowFlakesList;
    SnowFlake e;


    public Farm(FarmGame farmGame, Farmer player) {
        this.farmGame = farmGame;
        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        barnRectangle = new Rectangle(100, 50, 300,240);
        shopRectangle = new Rectangle(1050,-10,200,300);
        box = new Rectangle(-40, 150, 1320,450);
        moving = false;
        currentFrame = 0;
        farmerX = 170;
        farmerY = 300;
        direction = 0;

        SnowFlakesList = new ArrayList<>();
        e = new SnowFlake();

        this.player = player;
        farmPlots = new FarmLand(player);

        Timer timer = new Timer(150, this);
        timer.start();

        try {
            sign = ImageIO.read(new File("src/Sprites/Sign.png"));
            background = ImageIO.read(new File("src/Sprites/background.png"));
            snowbackground = ImageIO.read(new File("src/Sprites/SnowBackGround.png"));
            snow = ImageIO.read(new File("src/Sprites/Snow.png"));
            barn = ImageIO.read(new File("src/Sprites/Barn.png"));
            farmer = ImageIO.read(new File("src/Sprites/farmer.png"));
            farmerIdle = ImageIO.read(new File("src/Sprites/farmer_idle.png"));
            spriteSheet = ImageIO.read(new File("src/Sprites/crop_spritesheet-1.png-2.png"));
            shop = ImageIO.read(new File("src/Sprites/ShopOutside.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(customFont);
        g2d.setColor(new Color(56, 23,0));

        if (player.getCurrentWeather().equals("Snowy")) {
            g.drawImage(snowbackground,0,0,null);
        } else {
            g.drawImage(background, 0, 0, null);
        }

        g.drawImage(barn, 30,50,null);
        g.drawImage(shop, 900,-80, null);

        if (player.getCurrentWeather().equals("Snowy")) {
            if (SnowFlakesList.size() >= 20) {

            } else {
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
        }


        int spriteWidth = 48;
        int spriteHeight = 48;
        for (int r = 0; r < farmPlots.getPlots().size(); r++){
            for (int c = 0; c < farmPlots.getPlots().get(r).size(); c++){
                switch (farmPlots.getPlots().get(r).get(c).getCrop()) {
                    case "Rice" -> {
                        int sx1 = 11 * spriteWidth - (4- farmPlots.getPlots().get(r).get(c).getGrowthTime()) * 48;
                        int sy1 = 4 * spriteHeight;
                        int dx1 = 30 + (64 * c);
                        int dy1 = 450 + (64 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                    }
                    case "Potatoes" -> {
                        int sx1 = 5 * spriteWidth - (4- farmPlots.getPlots().get(r).get(c).getGrowthTime()) * 48;
                        int sy1 = 7 * spriteHeight;
                        int dx1 = 20 + (64 * c);
                        int dy1 = 450 + (64 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                    }
                    case "Wheat" -> {
                        int sx1 = 5 * spriteWidth - (4- farmPlots.getPlots().get(r).get(c).getGrowthTime()) * 48;
                        int sy1 = 5 * spriteHeight;
                        int dx1 = 20 + (64 * c);
                        int dy1 = 450 + (64 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                    }
                    case "Mandarin" -> {
                        int sx1 = 5 * spriteWidth - (4- farmPlots.getPlots().get(r).get(c).getGrowthTime()) * 48;
                        int sy1 = 8 * spriteHeight;
                        int dx1 = 30 + (64 * c);
                        int dy1 = 455 + (64 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                    }
                }
            }
        }

        g.drawImage(sign, 1650,10,null);
        g2d.drawString("DAY  " + player.getDay(), 1700, 60);

        int fx1 = direction * 128;
        int fy1 = currentFrame * 128;
        int fx2 = fx1 + 128;
        int fy2 = fy1 + 128;
        if (moving){
            g.drawImage(farmer, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        } else {
            g.drawImage(farmerIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
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
        return box.contains(x,y);
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
                break;
        };
        if (WithinTheBarn(farmerX + Xchange, farmerY + Ychange)) {
            farmerY += Ychange;
            farmerX += Xchange;
        }
        repaint();
        if (collidesWithBarn(farmerX, farmerY)){
            farmGame.showBarn(false);
            try {
                Thread.sleep(20);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
            farmerX = 170;
            farmerY = 300;
            direction = 2;
            moving = false;
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
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        moving = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

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
    public ArrayList<ArrayList<Crop>> getPlots() {
        return farmPlots.getPlots();
    }

    public int getFarmerX(){
        return farmerX;
    }
    public int getFarmerY(){
        return farmerY;
    }
}