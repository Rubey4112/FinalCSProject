import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartScene extends JPanel {

    private MenuManager menu;
    private AudioManager audio;

    public StartScene(MenuManager panel, AudioManager audioManager) {
        audio = audioManager;
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(
                    Font.createFont(Font.TRUETYPE_FONT, new File("../Font/Public_Pixel_Font_1_0/PublicPixel.otf")));
        } catch (IOException | FontFormatException e) {
            // Handle exception
        }

        menu = panel;

        setLayout(new BorderLayout());

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartListener());
        startButton.setFont(new Font("Public Pixel", Font.PLAIN, 20));
        add(startButton, BorderLayout.SOUTH);

        TitleScreen titleScreen = new TitleScreen();
        add(titleScreen);
    }

    private class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            menu.gameScene();
            audio.playClick();
        }
    }
}
