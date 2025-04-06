import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cutscene extends JPanel {
    private Timer timer;
    private BufferedImage scene1;
    private BufferedImage scene2;
    private Font customFont;
    private int currentFrame;
    private FarmGame farmGame;

    public Cutscene(FarmGame farmGame){
        currentFrame = 0;

        timer = new Timer(3000, e -> {
            currentFrame = (currentFrame + 1) % 5;
            repaint();
        });
        this.farmGame = farmGame;

        customFont = FontLoader.loadFont("src/Fonts/Daydream.ttf", 30f);
        try {
            scene1 = ImageIO.read(new File("src/Sprites/scene1.png"));
            scene2 = ImageIO.read(new File("src/Sprites/scene2.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(customFont);
        g2d.setColor(Color.WHITE);

        if(currentFrame == 0) {
            g.drawImage(scene1, 0, 0, null);
            g2d.drawString("COPS: STOP RUNNING! GET BACK HERE!", 500, 970);
        }

        if (currentFrame == 1) {
            g.drawImage(scene1, 0, 0, null);
            g2d.drawString("STEVEN: We can't keep running!", 600, 970);
        }

        if (currentFrame == 2) {
            g.drawImage(scene1, 0, 0, null);
            g2d.drawString("KYLE: James, you keep going! We're going to hold them off.", 200, 970);
        }

        if (currentFrame == 3) {
            g.drawImage(scene2, 0, 0, null);
            g2d.drawString("JAMES: Guys! No!", 800, 970);
        }
        if (currentFrame == 4){
            farmGame.showFarm();
        }
    }
    public void onShow() {
        timer.start();
    }

}
