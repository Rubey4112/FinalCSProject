import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class TitleScreen extends JPanel {
    public static final int FRAME = 400;
    private static final Color BACKGROUND = new Color(62, 33, 74);
    private BufferedImage myImage;
    private Graphics myBuffer;

    public TitleScreen() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./Font/Public_Pixel_Font_1_0/PublicPixel.otf")));
        } catch (IOException|FontFormatException e) {
            // Handle exception
        }

        myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME, FRAME);
        myBuffer.setColor(new Color(63, 63, 63));
        myBuffer.fillRect(0, 200, FRAME, FRAME);
        myBuffer.setColor(new Color(30, 77, 42));
        myBuffer.fillRect(50, 150, 150, 200);
        for(int x = 1; x<51;x++)
        {
            for(int y = 2;y<102;y+=2)
            {
                myBuffer.fillRect(100+x,150+y, 150+x, 200+y);
            }
            
        }
        myBuffer.fillRect(150,250, FRAME, 350);
        myBuffer.setColor(new Color(255,255,255));
        myBuffer.setFont(new Font("Public Pixel", Font.PLAIN, 30));
        myBuffer.drawString("Teo's", 160, 260);


    }

    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }
}