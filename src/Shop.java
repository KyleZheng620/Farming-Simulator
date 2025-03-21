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
    private boolean moving;
    private int direction;
    private int farmerX;
    private int farmerY;
    private int currentFrame;
    private Rectangle doorRectangle;

    public Shop(FarmGame farmGame) {
        this.farmGame = farmGame;
        doorRectangle = new Rectangle(900, 950, 50,30);

        moving = false;
        currentFrame = 0;
        farmerX = 890;
        farmerY = 820;
        direction = 0;

        Timer timer = new Timer(150, this);
        timer.start();

        try {
            shopInside = ImageIO.read(new File("src/ShopInside.png"));
            shopInside2 = ImageIO.read(new File("src/ShopInside 2.png"));
            farmer = ImageIO.read(new File("src\\farmer.png"));
            farmerIdle = ImageIO.read(new File("src\\farmer_idle.png"));
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
        g.drawImage(shopInside, 0, 0, null);
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                moving = true;
                farmerY -= 10;
                direction = 0;
                break;
            case KeyEvent.VK_DOWN:
                moving = true;
                farmerY += 10;
                direction = 2;
                break;
            case KeyEvent.VK_LEFT:
                moving = true;
                farmerX -= 10;
                direction = 3;
                break;
            case KeyEvent.VK_RIGHT:
                moving = true;
                farmerX += 10;
                direction = 1;
                break;
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
            farmerX = 890;
            farmerY = 820;
            direction = 0;
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