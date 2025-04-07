import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Barn extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage barnInside;
    private FarmGame farmGame;
    private BufferedImage farmer;
    private BufferedImage farmerIdle;
    private BufferedImage health;
    private BufferedImage sign;
    private Font customFont;
    private boolean moving;
    private Farmer player;
    private int direction;
    private int farmerX;
    private int farmerY;
    private int currentFrame;
    private Rectangle CookingPlace;
    private Rectangle doorRectangle;
    private Rectangle bedRectangle;
    private Rectangle box;
    private Rectangle box2;
    private Rectangle box3;
    private Rectangle box4;

    public Barn(FarmGame farmGame, Farmer player) {
        this.farmGame = farmGame;
        this.player = player;
        doorRectangle = new Rectangle(830, 1050, 50,30);
        bedRectangle = new Rectangle(940, 140, 30,40);
        CookingPlace = new Rectangle(940,620,50,30);
        box = new Rectangle(810,630,70,310);
        box2 = new Rectangle(870,620,240,220);
        box3 = new Rectangle(1060,80,50,560);
        box4 = new Rectangle(740,90,370,200);

        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        moving = false;
        currentFrame = 0;
        farmerX = 840;
        farmerY = 920;
        direction = 0;

        Timer timer = new Timer(150, this);
        timer.start();

        try {
            sign = ImageIO.read(new File("src/Sprites/Sign.png"));
            health = ImageIO.read(new File("src/Sprites/health.png"));
            barnInside = ImageIO.read(new File("src/Sprites/BarnInside.png"));
            farmer = ImageIO.read(new File("src/Sprites/farmer.png"));
            farmerIdle = ImageIO.read(new File("src/Sprites/farmer_idle.png"));
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
        g2d.setFont(customFont);
        g2d.setColor(new Color(56, 23,0));
        g.drawImage(barnInside, 0, 0, null);
        int fx1 = direction * 128;
        int fy1 = currentFrame * 128;
        int fx2 = fx1 + 128;
        int fy2 = fy1 + 128;
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

        g.drawImage(sign, 1650,10,null);
        g2d.drawString("DAY  " + player.getDay(), 1700, 60);
        if (moving){
            g.drawImage(farmer, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        } else {
            g.drawImage(farmerIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        }
        doorRectangle = new Rectangle(830, 1050, 50,30);
        bedRectangle = new Rectangle(940, 140, 30,40);
        CookingPlace = new Rectangle(940,620,50,30);
        box = new Rectangle(810,630,70,310);
        box2 = new Rectangle(870,620,240,220);
        box3 = new Rectangle(1060,80,50,560);
        box4 = new Rectangle(740,90,370,200);

    }

    private boolean collidesWithCook(int x, int y){
        return CookingPlace.contains(x,y);
    }

    private boolean WithinBarn (int x, int y) {
        return (box.contains(x,y) || box2.contains(x,y) ||box3.contains(x,y) ||box4.contains(x,y));
    }

    private boolean collidesWithBed(int x, int y){
        Rectangle player = new Rectangle(x,y,128,128);
        return(player.intersects(bedRectangle));
    }

    private boolean collidesWithDoor(int x, int y){
        Rectangle player = new Rectangle(x,y,128,128);
        return(player.intersects(doorRectangle));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame = (currentFrame + 1) % 4;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E){
            farmGame.toggleInventory("Barn");
        }
        int Xchange = 0;
        int Ychange = 0;

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
        if (WithinBarn(farmerX + Xchange, farmerY + Ychange)) {
            farmerY += Ychange;
            farmerX += Xchange;
        }

        repaint();
        if (collidesWithDoor(farmerX, farmerY)){
            farmGame.showFarm();
            try {
                Thread.sleep(20);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
            moving = false;
            farmerX = 840;
            farmerY = 920;
            direction = 0;
        }
        if (collidesWithBed(farmerX, farmerY)){
            farmGame.showBarn(true, player.sleep());
            farmerX = 970;
            farmerY = 100;
            direction = 2;
            moving = false;
        }
        if (collidesWithCook(farmerX, farmerY)){
            farmGame.showCooking();
            this.farmerX = 962;
            this.farmerY = 658;
            direction = 0;
            moving = false;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        moving = false;
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

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
