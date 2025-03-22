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
    private BufferedImage sign;
    private Font customFont;
    private boolean moving;
    private Farmer player;
    private int direction;
    private int farmerX;
    private int farmerY;
    private int currentFrame;
    private Rectangle doorRectangle;
    private Rectangle bedRectangle;

    public Barn(FarmGame farmGame, Farmer player) {
        this.farmGame = farmGame;
        this.player = player;
        doorRectangle = new Rectangle(830, 1050, 50,30);
        bedRectangle = new Rectangle(940, 140, 30,40);
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
        g.drawImage(sign, 1650,10,null);
        g2d.drawString("DAY  " + player.getDay(), 1700, 60);
        if (moving){
            g.drawImage(farmer, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        } else {
            g.drawImage(farmerIdle, farmerX, farmerY, farmerX + 128, farmerY + 128, fx1, fy1, fx2, fy2, this);
        }


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

        farmerY += Ychange;
        farmerX += Xchange;
        repaint();
        if (collidesWithDoor(farmerX, farmerY)){
            farmGame.showFarm();
            try {
                Thread.sleep(20);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
            farmerX = 840;
            farmerY = 920;
            direction = 0;
            moving = false;
        }
        if (collidesWithBed(farmerX, farmerY)){
            farmGame.showBarn(true);
            try {
                Thread.sleep(20);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
            player.sleep();
            farmerX = 970;
            farmerY = 100;
            direction = 2;
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
