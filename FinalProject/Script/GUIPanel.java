import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class GUIPanel extends JPanel {
    private ScenePanel gfx;
    private HUD hud;
    private AudioManager audio;

    private MenuManager menu;

    public GUIPanel(MenuManager panel, AudioManager audioManager) {
        setLayout(new BorderLayout());
        audio = audioManager;
        menu = panel;

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./Font/Public_Pixel_Font_1_0/PublicPixel.otf")));
        } catch (IOException|FontFormatException e) {
        }
        hud = new HUD();
        add(hud, BorderLayout.NORTH);
        
        gfx = new ScenePanel(menu, hud, audio); // Put the graphics panel in the center
        add(gfx, BorderLayout.CENTER);

        // JButton button = new JButton("Reset");
        // button.setFont(new Font("Public Pixel", Font.PLAIN, 20));
        // button.addActionListener(new ResetListener());
        // add(button, BorderLayout.SOUTH);        
    }

    public void start() {
        gfx.reset();
        gfx.begin();
        gfx.requestFocusInWindow();
    }

    public void stop() {
        gfx.end();
        gfx.requestFocusInWindow();
    }

    // private class ResetListener implements ActionListener {
    //     public void actionPerformed(ActionEvent e) {
    //         gfx.reset();
    //         gfx.begin();
    //         gfx.requestFocusInWindow();
    //     }
    // }
}
