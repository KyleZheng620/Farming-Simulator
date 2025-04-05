import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartingScreen extends JPanel implements MouseListener {
    private FarmGame farmGame;
    private BufferedImage background;
    private int mouseX;
    private int mouseY;

    public StartingScreen(FarmGame farmGame){
        mouseX = -1;
        mouseY = -1;
        this.farmGame = farmGame;
        addMouseListener(this);
        try {
            background = ImageIO.read(new File("src/Sprites/StartScreen.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        requestFocusInWindow();
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if (mouseX > 789 && mouseX < 1058 && mouseY > 591 && mouseY < 720){
            farmGame.showCutscene();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

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
