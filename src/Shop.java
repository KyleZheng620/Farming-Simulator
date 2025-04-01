import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shop extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage shopInside;
    private BufferedImage shopInside2;
    private FarmGame farmGame;
    private BufferedImage farmer;
    private BufferedImage farmerIdle;
    private BufferedImage sign;
    private Font customFont;
    private Farmer player;
    private boolean moving;
    private int direction;
    private int farmerX;
    private int farmerY;
    private int currentFrame;
    private Rectangle doorRectangle;
    private Rectangle box;
    private Rectangle box2;
    private Rectangle box3;
    private Rectangle C_box;
    private Rectangle C_box2;
    private Rectangle C_box3;
    private Rectangle C_box4;
    private Rectangle registerRectangle;

    public Shop(FarmGame farmGame, Farmer player) {
        this.farmGame = farmGame;
        this.player = player;
        doorRectangle = new Rectangle(900, 950, 50,30);
        box = new Rectangle(860, 690, 60,150);
        box2 = new Rectangle(530,510,670,180);
        box3 = new Rectangle(740,660,120,90);

        C_box = new Rectangle(1060,540,110, 120);
        C_box2 = new Rectangle(910,540,110,120);
        C_box3 = new Rectangle(530,460,190,  90);
        C_box4 = new Rectangle(750,600,160,70);

        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        moving = false;
        currentFrame = 0;
        farmerX = 890;
        farmerY = 820;
        direction = 0;

        Timer timer = new Timer(150, this);
        timer.start();

        try {
            sign = ImageIO.read(new File("src/Sprites/Sign.png"));
            shopInside = ImageIO.read(new File("src/Sprites/ShopInside.png"));
            shopInside2 = ImageIO.read(new File("src/Sprites/ShopInside 2.png"));
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

    public void showRegister() {
        ShopMenu shopMenu = new ShopMenu(
                (JFrame)SwingUtilities.getWindowAncestor(this),
                player,
                farmGame
        );
        shopMenu.setVisible(true);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(customFont);
        g2d.setColor(new Color(56, 23,0));
        g.drawImage(shopInside, 0, 0, null);
        g.drawImage(sign, 1650,10,null);
        g2d.drawString("DAY  " + player.getDay(), 1700, 60);
        registerRectangle = new Rectangle(600,530,100,50);
        int fx1 = direction * 128;
        int fy1 = currentFrame * 128;
        int fx2 = fx1 + 128;
        int fy2 = fy1 + 128;
        if (moving){
            g.drawImage(farmer, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        } else {
            g.drawImage(farmerIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        }
        g.drawImage(shopInside2, 0, 0, null);
    }

    private boolean WithinShop (int x, int y) {
        return ((box.contains(x,y) || box2.contains(x,y) || box3.contains(x,y)) && !C_box.contains(x,y) && !C_box2.contains(x,y) && !C_box3.contains(x,y) && !C_box4.contains(x,y));
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

    private boolean collidesWithRegister(int x, int y){
        Rectangle player = new Rectangle(x,y,128,128);
        return(player.intersects(registerRectangle));
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E){
            farmGame.toggleInventory("Shop");
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
        if (WithinShop(farmerX + Xchange, farmerY + Ychange)) {
            farmerY += Ychange;
            farmerX += Xchange;
            while (farmerX == 1000){
                System.out.println(farmerX);
                System.out.println(farmerY);
            }
        }
        System.out.println("X: " + farmerX  + " Y: " + farmerY);

        repaint();
        if (collidesWithDoor(farmerX, farmerY)){
            farmGame.showFarm();
            try {
                Thread.sleep(20);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
            moving = false;
            farmerX = 890;
            farmerY = 820;
            direction = 0;
        }
        if (collidesWithRegister(farmerX, farmerY)) {
                showRegister();
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
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}