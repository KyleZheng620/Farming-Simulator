import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontLoader {

    public static Font loadFont(String path, float size) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            return customFont.deriveFont(size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.out.println("Font loading failed. Using default font.");
            return new Font("Arial", Font.PLAIN, 14);
        }
    }
}