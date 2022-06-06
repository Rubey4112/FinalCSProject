import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOverScene extends JPanel {
    private MenuManager menu;
    private AudioManager audio;
    private JLabel floor;

    public GameOverScene(MenuManager panel, AudioManager audioManager) {
        audio = audioManager;
        setLayout(new GridBagLayout());

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./Font/Public_Pixel_Font_1_0/PublicPixel.otf")));
        } catch (IOException|FontFormatException e) {
            // Handle exception
        }

        menu = panel;

        GridBagConstraints formatText = new GridBagConstraints();
        GridBagConstraints formatButton = new GridBagConstraints();
        GridBagConstraints formatText2 = new GridBagConstraints();
        
        JLabel title = new JLabel("Game Over");
        title.setFont(new Font("Public Pixel", Font.PLAIN, 35));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        formatText.gridx = 0;
        formatText.gridy = 0;
        formatText.insets = new Insets(10, 10, 0, 10);
        // formatText.weighty = 0.3;
        formatText.anchor = GridBagConstraints.PAGE_START;
        formatText.fill = GridBagConstraints.HORIZONTAL;
        add(title, formatText);

        floor = new JLabel("You've Reached Floor: " + 1);
        floor.setFont(new Font("Public Pixel", Font.PLAIN, 20));
        floor.setHorizontalAlignment(SwingConstants.CENTER);
        formatText2.gridx = 0;
        formatText2.gridy = 1;
        formatText2.insets = new Insets(10, 10, 0, 10);
        // formatText.weighty = 0.3;
        formatText2.anchor = GridBagConstraints.PAGE_START;
        formatText2.fill = GridBagConstraints.HORIZONTAL;
        add(floor, formatText2);

        JButton startButton = new JButton("Restart");
        startButton.addActionListener(new StartListener());
        startButton.setFont(new Font("Public Pixel", Font.PLAIN, 20));
        formatButton.gridx = 0;
        formatButton.gridy = 2;
        formatButton.insets = new Insets(0, 10, 20, 10);
        // formatButton.weightx = 1;
        formatButton.weighty = 0.7;
        formatButton.fill = GridBagConstraints.HORIZONTAL;
        add(startButton, formatButton);
    }

    private class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            menu.gameScene();
            audio.playClick();
        }
    }

    public  void setFloor(int floorVal) {
        floor.setText("You've Reached Floor: " + floorVal);
    }
}
