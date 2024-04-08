package snake.view;

import snake.Controller;
import snake.model.Direction;
import snake.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class FrameGame extends JFrame implements KeyListener
{
    public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

    private Controller ctrl;

    private PanelGame panelGame;

    public FrameGame(Controller ctrl, Game game)
    {
        this.ctrl = ctrl;

        this.panelGame = new PanelGame(ctrl, game);
        this.add(this.panelGame);
        this.addKeyListener(this);

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

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case VK_UP, VK_KP_UP -> this.ctrl.move(Direction.UP);
            case VK_DOWN, VK_KP_DOWN -> this.ctrl.move(Direction.DOWN);
            case VK_LEFT, VK_KP_LEFT -> this.ctrl.move(Direction.LEFT);
            case VK_RIGHT, VK_KP_RIGHT -> this.ctrl.move(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
