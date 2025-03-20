import javax.swing.JFrame;

public class SampleFrame {

    public SampleFrame() {
        JFrame frame = new JFrame("Farming Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        DisplayPanel panel = new DisplayPanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}