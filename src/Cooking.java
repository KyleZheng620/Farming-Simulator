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
    private BufferedImage CookingBackGround;
    private FarmGame farmGame;
    private Farmer player;
    private JButton Xbutton;
    private JButton BreadButton;
    private JButton RiceButton;
    private JButton PotatoButton;
    private Font customFont;


    public Cooking(FarmGame farmGame, Farmer player) {
        this.farmGame = farmGame;
        this.player = player;
        Timer timer = new Timer(150, this);
        timer.start();

        Xbutton = new JButton("");
        BreadButton = new JButton("");
        RiceButton = new JButton("");
        PotatoButton = new JButton("");
        JButton[] buttons = {Xbutton,BreadButton,RiceButton,PotatoButton};

        for (JButton button: buttons) {
            button.addActionListener(this);
            button.setOpaque(false);
            button.setEnabled(true);
            button.setBorderPainted(false);
            add(button);
        }

        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);


        try {
            sign = ImageIO.read(new File("src/Sprites/Sign.png"));
            health = ImageIO.read(new File("src/Sprites/health.png"));
            barnInside = ImageIO.read(new File("src/Sprites/Cooking.png"));
            CloseButton = ImageIO.read(new File("src/Sprites/ExitButton.png"));
            CookingBackGround = ImageIO.read(new File("src/Sprites/CookingBackground.png"));
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

        g.drawImage(CookingBackGround,0,0,null);

        g.drawImage(CloseButton,766,130, 300, 120,null);
        Xbutton.setLocation(766,130);
        Xbutton.setSize(300,120);
        Xbutton.setBackground(Color.RED);
        BreadButton.setLocation(845,265);
        BreadButton.setSize(150,50);
        BreadButton.setBackground(Color.RED);
        RiceButton.setLocation(845,475);
        RiceButton.setSize(150,50);
        RiceButton.setBackground(Color.RED);
        PotatoButton.setLocation(845,370);
        PotatoButton.setSize(150,50);
        PotatoButton.setBackground(Color.RED);


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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton casted = (JButton) e.getSource();
            if (casted == Xbutton) {
                farmGame.showBarn(0);
                System.out.println("hello");
            }
            if (casted == BreadButton) {
                System.out.println(player.getInventory().getQuanitiyOfItem("Wheat"));
                if (player.getInventory().getQuanitiyOfItem("Wheat") >= 3) {
                    player.getInventory().addItem("Bread", 1);
                    player.getInventory().removeItem("Wheat", 3);
                    System.out.println("cooked bread");
                } else {
                    System.out.println("Not enough Wheat");
                }
            }
            if (casted == RiceButton) {
                if (player.getInventory().getQuanitiyOfItem("Rice") >= 1) {
                    player.getInventory().addItem("Cooked Rice",1);
                    player.getInventory().removeItem("Rice",1);
                    System.out.println("Cooked Rice");
                } else {
                    System.out.println("Not enough Rice");
                }
            }
            if (casted == PotatoButton) {
                if (player.getInventory().getQuanitiyOfItem("Potato") >= 2) {
                    player.getInventory().addItem("Cooked Potato",1);
                    player.getInventory().removeItem("Potato", 2);
                    System.out.println("Cooked Potatoes");
                } else {
                    System.out.println("Not enough Potatoes");
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
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
    public void mouseExited(MouseEvent e) {

    }

}
