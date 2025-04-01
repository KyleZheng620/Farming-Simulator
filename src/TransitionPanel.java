import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TransitionPanel extends JPanel {

    private int a = 0;
    private String card;
    private Font customFont;
    private Crop[][] farmPlots;
    private BufferedImage barnInside;
    private BufferedImage farmerIdle;
    private BufferedImage shopInside;
    private BufferedImage shopInside2;
    private BufferedImage spriteSheet;
    private BufferedImage snowbackground;
    private BufferedImage farmerRainIdle;
    private BufferedImage farm;
    private BufferedImage barn;
    private BufferedImage shop;
    private BufferedImage sign;
    private BufferedImage sleep;
    private Farmer player;
    private int farmerY;
    private int farmerX;
    private boolean sleeping;

    public TransitionPanel(Farmer player){
        this.player = player;
    }

    public void setAlpha(int a, String card) {
        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        this.a = a;
        this.card = card;
        try {
            sign = ImageIO.read(new File("src/Sprites/Sign.png"));
            farm = ImageIO.read(new File("src/Sprites/background.png"));
            barn = ImageIO.read(new File("src/Sprites/Barn.png"));
            snowbackground = ImageIO.read(new File("src/Sprites/SnowBackGround.png"));
            shopInside = ImageIO.read(new File("src/Sprites/ShopInside.png"));
            shopInside2 = ImageIO.read(new File("src/Sprites/ShopInside 2.png"));
            barnInside = ImageIO.read(new File("src/Sprites/BarnInside.png"));
            farmerIdle = ImageIO.read(new File("src/Sprites/farmer_idle.png"));
            farmerRainIdle = ImageIO.read(new File("src/Sprites/farmerRainIdle.png"));
            spriteSheet = ImageIO.read(new File("src/Sprites/crop_spritesheet-1.png-2.png"));
            shop = ImageIO.read(new File("src/Sprites/ShopOutside.png"));
            sleep = ImageIO.read(new File("src/Sprites/Sleep.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setAlpha(int a, String card, boolean sleeping) {
        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        this.sleeping = sleeping;
        this.a = a;
        this.card = card;
        try {
            sign = ImageIO.read(new File("src/Sprites/Sign.png"));
            farm = ImageIO.read(new File("src/Sprites/background.png"));
            barn = ImageIO.read(new File("src/Sprites/Barn.png"));
            snowbackground = ImageIO.read(new File("src/Sprites/SnowBackGround.png"));
            shopInside = ImageIO.read(new File("src/Sprites/ShopInside.png"));
            shopInside2 = ImageIO.read(new File("src/Sprites/ShopInside 2.png"));
            barnInside = ImageIO.read(new File("src/Sprites/BarnInside.png"));
            farmerIdle = ImageIO.read(new File("src/Sprites/farmer_idle.png"));
            farmerRainIdle = ImageIO.read(new File("src/Sprites/farmerRainIdle.png"));
            spriteSheet = ImageIO.read(new File("src/Sprites/crop_spritesheet-1.png-2.png"));
            shop = ImageIO.read(new File("src/Sprites/ShopOutside.png"));
            sleep = ImageIO.read(new File("src/Sprites/Sleep.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setAlpha(int a, String card, Crop[][] farmPlots, int farmerX, int farmerY) {
        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        this.farmPlots = farmPlots;
        this.farmerX = farmerX;
        this.farmerY = farmerY;
        this.a = a;
        this.card = card;
        try {
            farm = ImageIO.read(new File("src/Sprites/background.png"));
            barn = ImageIO.read(new File("src/Sprites/Barn.png"));
            snowbackground = ImageIO.read(new File("src/Sprites/SnowBackGround.png"));
            farmerIdle = ImageIO.read(new File("src/Sprites/farmer_idle.png"));
            farmerRainIdle = ImageIO.read(new File("src/Sprites/farmerRainIdle.png"));
            spriteSheet = ImageIO.read(new File("src/Sprites/crop_spritesheet-1.png-2.png"));
            shop = ImageIO.read(new File("src/Sprites/ShopOutside.png"));
            sleep = ImageIO.read(new File("src/Sprites/Sleep.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(customFont);
        g2d.setColor(new Color(56, 23,0));
        if (card.equals("Farm")){
            if (player.getCurrentWeather().equals("Snowy")) {
                g.drawImage(snowbackground,0,0,null);
            } else {
                g.drawImage(farm, 0, 0, null);
            }
            g.drawImage(barn, 30,50,null);
            g.drawImage(shop, 900,-80, null);
            int spriteWidth = 48;
            int spriteHeight = 48;
            g.drawImage(sign, 1650,10,null);
            g2d.drawString("DAY  " + player.getDay(), 1700, 60);
            for (int c = 0; c < player.getFarmPlots().getPlots()[0].length; c++){
                for (int r = 0; r < player.getFarmPlots().getPlots().length; r++){
                    if (player.getFarmPlots().getPlots()[r][c] == null){
                        break;
                    }
                    if (player.getFarmPlots().getPlots()[r][c].getCrop().equals("Rice")){
                        int sx1 = 11 * spriteWidth - (4- player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                        int sy1 = 4 * spriteHeight;
                        int dx1 = 35 + (53 * c);
                        int dy1 = 460 + (55 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                    } else if (player.getFarmPlots().getPlots()[r][c].getCrop().equals("Potato")){
                        int sx1 = 5 * spriteWidth - (4- player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                        int sy1 = 7 * spriteHeight;
                        int dx1 = 40 + (53 * c);
                        int dy1 = 455 + (55 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                    } else if (player.getFarmPlots().getPlots()[r][c].getCrop().equals("Wheat")){
                        int sx1 = 5 * spriteWidth - (4- player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                        int sy1 = 5 * spriteHeight;
                        int dx1 = 40 + (53 * c);
                        int dy1 = 465 + (55 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                    } else if ((player.getFarmPlots().getPlots()[r][c].getCrop().equals("Mandarin"))){
                        int sx1 = 5 * spriteWidth - (4- player.getFarmPlots().getPlots()[r][c].getGrowthTime()) * 48;
                        int sy1 = 8 * spriteHeight;
                        int dx1 = 35 + (53 * c);
                        int dy1 = 460 + (55 * r);
                        g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                    }
                }
            }
            if (player.getCurrentWeather().equals("Rain")){
                g.drawImage(farmerRainIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, 256,0,256+128,128, this);
            } else {
                g.drawImage(farmerIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, 256,0,256+128,128, this);
            }
        } else if (card.equals("Barn")){
            if (!sleeping){
                g.drawImage(barnInside,0,0,null);
                g.drawImage(farmerIdle, 840,920, 840 + 128, 920 + 128, 0, 0, 128, 128,this);
                g.drawImage(sign, 1650,10,null);
                g2d.drawString("DAY  " + player.getDay(), 1700, 60);
            } else {
                g.drawImage(sleep,0,0,null);
                g.drawImage(sign, 1650,10,null);
                if (player.getDay()==1){
                    g2d.drawString("DAY  1", 1700, 60);
                } else {
                    g2d.drawString("DAY  " + (player.getDay()-1), 1700, 60);
                }
            }
        } else if (card.equals("Shop")){
            g.drawImage(shopInside, 0, 0, null);
            g.drawImage(shopInside2, 0, 0, null);
            g.drawImage(farmerIdle, 890, 820, 890 + 128, 820 + 128, 0, 0, 128, 128, this);
            g.drawImage(sign, 1650,10,null);
            g2d.drawString("DAY  " + player.getDay(), 1700, 60);
        }
        g.setColor(new Color(0, 0, 0, a));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}