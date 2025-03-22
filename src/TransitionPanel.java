import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TransitionPanel extends JPanel {

    private int a = 0;
    private String card;
    private ArrayList<ArrayList<Crop>> farmPlots;
    private BufferedImage barnInside;
    private BufferedImage farmerIdle;
    private BufferedImage shopInside;
    private BufferedImage shopInside2;
    private BufferedImage spriteSheet;
    private BufferedImage farm;
    private BufferedImage barn;
    private BufferedImage shop;
    private int farmerY;
    private int farmerX;

    public void setAlpha(int a, String card) {
        this.a = a;
        this.card = card;
        try {
            farm = ImageIO.read(new File("src\\background.png"));
            barn = ImageIO.read(new File("src\\Barn.png"));
            shopInside = ImageIO.read(new File("src/ShopInside.png"));
            shopInside2 = ImageIO.read(new File("src/ShopInside 2.png"));
            barnInside = ImageIO.read(new File("src\\BarnInside.png"));
            farmerIdle = ImageIO.read(new File("src\\farmer_idle.png"));
            spriteSheet = ImageIO.read(new File("src\\crop_spritesheet-1.png-2.png"));
            shop = ImageIO.read(new File("src/ShopOutside.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setAlpha(int a, String card, ArrayList<ArrayList<Crop>> farmPlots, int farmerX, int farmerY) {
        this.farmPlots = farmPlots;
        this.farmerX = farmerX;
        this.farmerY = farmerY;
        this.a = a;
        this.card = card;
        try {
            farm = ImageIO.read(new File("src\\background.png"));
            barn = ImageIO.read(new File("src\\Barn.png"));
            farmerIdle = ImageIO.read(new File("src\\farmer_idle.png"));
            spriteSheet = ImageIO.read(new File("src\\crop_spritesheet-1.png-2.png"));
            shop = ImageIO.read(new File("src/ShopOutside.png"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (card.equals("Farm")){
            g.drawImage(farm, 0, 0, null);
            g.drawImage(barn, 30,50,null);
            g.drawImage(shop, 900,-80, null);
            int spriteWidth = 48;
            int spriteHeight = 48;
            for (int r = 0; r < farmPlots.size(); r++){
                for (int c = 0; c < farmPlots.get(r).size(); c++){
                    switch (farmPlots.get(r).get(c).getCrop()) {
                        case "Rice" -> {
                            int sx1 = 10 * spriteWidth - (4- farmPlots.get(r).get(c).getGrowthTime()) * 48;
                            int sy1 = 4 * spriteHeight;
                            int dx1 = 30 + (64 * c);
                            int dy1 = 450 + (64 * r);
                            g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                        }
                        case "Potatoes" -> {
                            int sx1 = 5 * spriteWidth - (4- farmPlots.get(r).get(c).getGrowthTime()) * 48;
                            int sy1 = 7 * spriteHeight;
                            int dx1 = 20 + (64 * c);
                            int dy1 = 450 + (64 * r);
                            g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                        }
                        case "Wheat" -> {
                            int sx1 = 5 * spriteWidth - (4- farmPlots.get(r).get(c).getGrowthTime()) * 48;
                            int sy1 = 5 * spriteHeight;
                            int dx1 = 20 + (64 * c);
                            int dy1 = 455 + (64 * r);
                            g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                        }
                        case "Mandarin" -> {
                            int sx1 = 5 * spriteWidth - (4- farmPlots.get(r).get(c).getGrowthTime()) * 48;
                            int sy1 = 8 * spriteHeight;
                            int dx1 = 30 + (64 * c);
                            int dy1 = 450 + (64 * r);
                            g.drawImage(spriteSheet, dx1, dy1, dx1 + spriteWidth, dy1 + spriteHeight, sx1, sy1, sx1 + spriteWidth, sy1 + spriteHeight, null);
                        }
                    }
                }
            }
            g.drawImage(farmerIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, 256,0,256+128,128, this);
        } else if (card.equals("Barn")){
            g.drawImage(barnInside,0,0,null);
            g.drawImage(farmerIdle, 840,920, 840 + 128, 920 + 128, 0, 0, 128, 128,this);
        } else if (card.equals("Shop")){
            g.drawImage(shopInside, 0, 0, null);
            g.drawImage(shopInside2, 0, 0, null);
            g.drawImage(farmerIdle, 890, 820, 890 + 128, 820 + 128, 0, 0, 128, 128, this);
        }
        g.setColor(new Color(0, 0, 0, a));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}