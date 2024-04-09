package snake.view;

import snake.controller.Controller;
import snake.model.Game;

import javax.swing.*;
import java.awt.*;
public class FrameGame extends JFrame
{
    public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

    private final PanelInfo panelInfo;
    private final PanelGame panelGame;

    public FrameGame(Controller ctrl, Game game)
    {
        // Components
        this.panelInfo = new PanelInfo(ctrl, game);
        this.panelGame = new PanelGame(ctrl, game);

        // Layout
        this.add(this.panelInfo, BorderLayout.NORTH);
        this.add(this.panelGame, BorderLayout.CENTER);

        // Parameters
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Snake");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void refreshInfo()
    {
        this.panelInfo.refreshInfo();
    }

    public void refreshGame()
    {
        this.panelGame.repaint();
    }
}
