package snake.view;

import snake.Controller;
import snake.model.Cell;
import snake.model.Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static snake.view.FrameGame.SCREEN;

public class PanelGame extends JPanel
{
    private Controller ctrl;
    private Game game;

    public PanelGame(Controller ctrl, Game game)
    {
        this.ctrl = ctrl;
        this.game = game;

        int size = (int) (Math.min(SCREEN.width, SCREEN.height) * 0.7);
        this.setPreferredSize(new Dimension(size, size));

        this.setBackground(Color.WHITE);

        this.requestFocus();
        this.addKeyListener(new GameKeyListener());
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Cell[][] grid = this.game.getGrid();

        int tileSize = Math.min(this.getHeight() / (grid.length + 2), this.getWidth() / (grid[0].length + 2));

        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[r].length; c++)
            {
                g.setColor(grid[r][c].getColor());
                g.fillRect((1 + c) * tileSize, (1 + r) * tileSize, tileSize, tileSize);
            }
    }

    private static class GameKeyListener extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            System.out.println("Key : " + e.getKeyChar());
        }
    }
}
