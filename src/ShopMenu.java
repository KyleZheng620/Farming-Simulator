import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ShopMenu extends JPanel implements KeyListener, MouseListener, ActionListener{
    private JFrame frame;
    private JPanel gamePanel;
    private JLayeredPane menuContainer;
    private JLabel backgroundImage;
    private JPopupMenu popupMenu;
    private int farmerX;
    private int farmerY;
    private int currentFrame;
    private boolean moving;
    private int direction;
    private BufferedImage farmer;

    private Rectangle shopArea = new Rectangle(100, 100, 200, 200);

    public ShopMenu() {
        frame = new JFrame("Interactive Menu Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(shopArea.x, shopArea.y,
                        shopArea.width, shopArea.height);
            }
        };
        gamePanel.setPreferredSize(new Dimension(500, 500));
        gamePanel.setBackground(Color.WHITE);

        // Create layered pane for menu
        menuContainer = new JLayeredPane();
        menuContainer.setBounds(0, 0, 500, 500);

        // Load and set background image
        try {
            BufferedImage img = ImageIO.read(new File("src/urplqfr8cnt61 (1).webp"));
            backgroundImage = new JLabel(new ImageIcon(img));
            backgroundImage.setBounds(0, 0, 500, 500);
            menuContainer.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
        } catch (IOException e) {
            // Fallback to colored background
            JPanel coloredBackground = new JPanel();
            coloredBackground.setBackground(new Color(240, 240, 240));
            coloredBackground.setBounds(0, 0, 500, 500);
            menuContainer.add(coloredBackground, JLayeredPane.DEFAULT_LAYER);
        }

        createPopupMenu();

        // Add mouse motion listener
        gamePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (shopArea.contains(e.getPoint())) {
                    popupMenu.show(gamePanel, e.getX(), e.getY());
                } else {
                    popupMenu.setVisible(false);
                }
            }
        });

        frame.getContentPane().add(gamePanel);
        frame.add(menuContainer);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createPopupMenu() {
        popupMenu = new JPopupMenu("Farm Shop") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Optional: Add semi-transparent overlay
                g.setColor(new Color(255, 255, 255, 200));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Add menu items with actions
        JMenuItem item1 = new JMenuItem("Seed");
        item1.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Selected Option 1");
        });
        popupMenu.add(item1);

        JMenuItem item2 = new JMenuItem("Water");
        item2.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Selected Option 2");
        });
        popupMenu.add(item2);

        JMenuItem item3 = new JMenuItem("Sell");
        item3.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Selected Option 2");
        });
        popupMenu.add(item3);

        popupMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        popupMenu.add(exitItem);
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
        moving = true;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                farmerY -= 10;
                direction = 0;
                break;
            case KeyEvent.VK_DOWN:
                farmerY += 10;
                direction = 2;
                break;
            case KeyEvent.VK_LEFT:
                farmerX -= 10;
                direction = 3;
                break;
            case KeyEvent.VK_RIGHT:
                farmerX += 10;
                direction = 1;
                break;
        }
        repaint();
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