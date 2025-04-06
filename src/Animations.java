import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animations extends JPanel implements ActionListener {
    private double col;
    private double row;
    private Font customFont;
    private FarmGame farmGame;
    private BufferedImage wateringCan;
    private BufferedImage harvestingScythe;
    private BufferedImage spriteSheet;
    private BufferedImage snowbackground;
    private BufferedImage farmerRainIdle;
    private BufferedImage plantingHoe;
    private BufferedImage farmerIdle;
    private BufferedImage farm;
    private BufferedImage sign;
    private BufferedImage barn;
    private BufferedImage health;
    private BufferedImage shop;
    private int currentFrame;
    private int currentFrame2;
    private int currentFrame3;
    private boolean watering;
    private boolean harvesting;
    private boolean planting;
    private Farmer player;
    private int farmerX;
    private int farmerY;
    private int direction;


    public Animations(FarmGame farmGame, Farmer player){
        new Timer(400, e -> {
            currentFrame = (currentFrame + 1) % 4;
            repaint();
        }).start();

        new Timer(100, e -> {
            currentFrame2 = (currentFrame2 + 1) % 7;
            repaint();
        }).start();

        new Timer(75, e -> {
            currentFrame3 = (currentFrame3 + 1) % 11;
            repaint();
        }).start();


        currentFrame = 0;
        currentFrame2 = 0;
        currentFrame3 = 0;
        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        this.farmGame = farmGame;
        this.player = player;
        watering = false;
        harvesting = false;
        planting = false;
        try {
            sign = ImageIO.read(new File("src/Sprites/Sign.png"));
            health = ImageIO.read(new File("src/Sprites/health.png"));
            wateringCan = ImageIO.read(new File("src/Sprites/Watering.png"));
            harvestingScythe = ImageIO.read(new File("src/Sprites/Harvesting.png"));
            plantingHoe = ImageIO.read(new File("src/Sprites/Planting.png"));
            farm = ImageIO.read(new File("src/Sprites/background.png"));
            barn = ImageIO.read(new File("src/Sprites/Barn.png"));
            snowbackground = ImageIO.read(new File("src/Sprites/SnowBackGround.png"));
            farmerIdle = ImageIO.read(new File("src/Sprites/farmer_idle.png"));
            farmerRainIdle = ImageIO.read(new File("src/Sprites/farmerRainIdle.png"));
            shop = ImageIO.read(new File("src/Sprites/ShopOutside.png"));
            spriteSheet = ImageIO.read(new File("src/Sprites/crop_spritesheet-1.png-2.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(customFont);
        g2d.setColor(new Color(56, 23, 0));

        if (player.getCurrentWeather().equals("Snowy")) {
            g.drawImage(snowbackground, 0, 0, null);
        } else {
            g.drawImage(farm, 0, 0, null);
        }
        g.drawImage(barn, 30, 50, null);
        g.drawImage(shop, 900, -80, null);
        int spriteWidth = 48;
        int spriteHeight = 48;
        g.drawImage(sign, 1650, 10, null);
        g2d.drawString("DAY  " + player.getDay(), 1700, 60);
        for (int c = 0; c < player.getFarmPlots().getPlots()[0].length; c++) {
            for (int r = 0; r < player.getFarmPlots().getPlots().length; r++) {
                if (player.getFarmPlots().getPlots()[r][c] == null) {
                    break;
                }
                if (player.getFarmPlots().getPlots()[r][c].getCrop().equals("Rice")) {
                    int sx1 = 11 * spriteWidth - (4 - player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                    int sy1 = 4 * spriteHeight;
                    int dx1 = 35 + (53 * c);
                    int dy1 = 460 + (55 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                } else if (player.getFarmPlots().getPlots()[r][c].getCrop().equals("Potato")) {
                    int sx1 = 5 * spriteWidth - (4 - player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                    int sy1 = 7 * spriteHeight;
                    int dx1 = 40 + (53 * c);
                    int dy1 = 455 + (55 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                } else if (player.getFarmPlots().getPlots()[r][c].getCrop().equals("Wheat")) {
                    int sx1 = 5 * spriteWidth - (4 - player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                    int sy1 = 5 * spriteHeight;
                    int dx1 = 40 + (53 * c);
                    int dy1 = 465 + (55 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                } else if ((player.getFarmPlots().getPlots()[r][c].getCrop().equals("Mandarin"))) {
                    int sx1 = 5 * spriteWidth - (4 - player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                    int sy1 = 8 * spriteHeight;
                    int dx1 = 35 + (53 * c);
                    int dy1 = 460 + (55 * r);
                    g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                }
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

        int fx1 = direction * 128;
        int fy1 = 0;
        int fx2 = fx1 + 128;
        int fy2 = fy1 + 128;
        if (!player.getCurrentWeather().equals("Rain")){
            g.drawImage(farmerIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        } else {
            g.drawImage(farmerRainIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        }

        if (watering) {
            int wx1 = 87 * currentFrame;
            int wy1 = 0;
            int wx2 = wx1 + 87;
            int wy2 = wy1 + 87;
            int x = (int) row * 54 + 50;
            int y = (int) col * 55 + 400;
            g.drawImage(wateringCan, x, y, x + 87, y + 87, wx1, wy1, wx2, wy2, null);
            if (currentFrame == 3) {
                farmGame.showFarm(0);
                watering = false;
            }
        } else if (harvesting) {
            int wx1 = 48 * currentFrame2;
            int wy1 = 0;
            int wx2 = wx1 + 48;
            int wy2 = wy1 + 48;
            int x = (int) row * 54;
            int y = (int) col * 55 + 420;
            g.drawImage(harvestingScythe, x, y, x + 96, y + 96, wx1, wy1, wx2, wy2, null);
            if (currentFrame2 == 6) {
                farmGame.showFarm(0);
                harvesting = false;
            }
        } else if (planting) {
            int wx1 = 42 * currentFrame3;
            int wy1 = 0;
            int wx2 = wx1 + 42;
            int wy2 = wy1 + 42;
            int x = (int) row * 54 ;
            int y = (int) col * 55 + 420;
            g.drawImage(plantingHoe, x, y, x + 82, y + 82, wx1, wy1, wx2, wy2, null);
            if (currentFrame3 == 10) {
                farmGame.showFarm(0);
                planting = false;
            }
        }
    }
    public void watering(double col, double row, int farmerX, int farmerY, int direction){
        this.direction = direction;
        currentFrame = 0;
        this.farmerX = farmerX;
        this.farmerY = farmerY;
        this.col = col;
        this.row = row;
        watering = true;
        repaint();
    }

    public void harvesting(double col, double row, int farmerX, int farmerY, int direction){
        this.direction = direction;
        currentFrame2 = 0;
        this.farmerX = farmerX;
        this.farmerY = farmerY;
        this.col = col;
        this.row = row;
        harvesting = true;
        repaint();
    }

    public void planting(double col, double row, int farmerX, int farmerY, int direction){
        this.direction = direction;
        currentFrame3 = 0;
        this.farmerX = farmerX;
        this.farmerY = farmerY;
        this.col = col;
        this.row = row;
        planting = true;
        repaint();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
