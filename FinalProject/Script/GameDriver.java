import java.awt.Dimension;
import javax.swing.JFrame;

public class GameDriver {
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Dungeon Amateur");
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.pack();
        frame.setLocation(20, 20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // GUIPanel gui = new GUIPanel();
        frame.setContentPane(new MenuManager());
        frame.setVisible(true);
        // System.out.println(gui.gfx.getSize());
    }
}