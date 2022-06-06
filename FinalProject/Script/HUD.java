import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HUD extends JPanel {
    private JLabel health;
    private JLabel floor;
    private int healthVal;
    private int floorVal;

    public HUD() {
        setLayout(new GridLayout(1, 2));
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(
                    Font.createFont(Font.TRUETYPE_FONT, new File("./Font/Public_Pixel_Font_1_0/PublicPixel.otf")));
        } catch (IOException | FontFormatException e) {
            // Handle exception
        }
        healthVal = 5;
        health = new JLabel("Health: " + healthVal + "            ");
        health.setFont(new Font("Public Pixel", Font.PLAIN, 20));
        health.setHorizontalAlignment(SwingConstants.CENTER);
        add(health);

        floorVal = 1;
        floor = new JLabel("            Floor: " + floorVal);
        floor.setFont(new Font("Public Pixel", Font.PLAIN, 20));
        floor.setHorizontalAlignment(SwingConstants.CENTER);
        add(floor);
    }

    public void setHealth(int health) {
        healthVal = health;
        this.health.setText("Health: " + healthVal + "            ");
    }

    public void setFloor(int floor) {
        floorVal = floor;
        this.floor.setText("            Floor: " + floorVal);
    }
}
