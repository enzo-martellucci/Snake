package snake.view;

import snake.controller.Controller;
import snake.model.Cell;
import snake.model.Direction;
import snake.model.Game;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import static snake.view.FrameGame.SCREEN;

public class PanelGame extends JPanel
{
    private final Controller ctrl;
    private final Game game;

    public PanelGame(Controller ctrl, Game game)
    {
        this.ctrl = ctrl;
        this.game = game;

        InputMap inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("UP"), "UP");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
        ActionMap actionMap = this.getActionMap();
        actionMap.put("UP", new MoveAction(() -> this.ctrl.turn(Direction.UP)));
        actionMap.put("DOWN", new MoveAction(() -> this.ctrl.turn(Direction.DOWN)));
        actionMap.put("LEFT", new MoveAction(() -> this.ctrl.turn(Direction.LEFT)));
        actionMap.put("RIGHT", new MoveAction(() -> this.ctrl.turn(Direction.RIGHT)));

        int size = (int) (Math.min(SCREEN.width, SCREEN.height) * 0.7);
        this.setPreferredSize(new Dimension(size, size));

        this.setBackground(Color.WHITE);
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

    private static class MoveAction extends AbstractAction
    {
        private final Runnable action;

        public MoveAction(Runnable action)
        {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            this.action.run();
        }
    }
}
