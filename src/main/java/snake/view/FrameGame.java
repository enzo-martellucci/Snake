package snake.view;

import snake.controller.Controller;
import snake.model.Game;

import javax.swing.*;
import java.awt.*;

public class FrameGame extends JFrame
{
    public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

    private final PanelGame panelGame;

    public FrameGame(Controller ctrl, Game game)
    {
        this.panelGame = new PanelGame(ctrl, game);
        this.add(this.panelGame);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Snake");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void refreshGame()
    {
        this.panelGame.repaint();
    }
}
