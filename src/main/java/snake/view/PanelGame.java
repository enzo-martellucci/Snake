package snake.view;

import snake.controller.Controller;
import snake.model.BodyPart;
import snake.model.Direction;
import snake.model.Game;
import snake.model.Position;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Queue;

import static snake.view.FrameGame.SCREEN;

public class PanelGame extends JPanel
{
    private static final Color WALL_TILE = Color.DARK_GRAY;
    private static final Color ODD_TILE = new Color(65, 210, 80);
    private static final Color EVEN_TILE = new Color(50, 160, 60);
    private static final Color FOOD_TILE = new Color(220, 90, 90);
    private static final Color HEAD_TILE = new Color(0, 20, 125);
    private static final Color BODY_TILE = new Color(110, 115, 225);
    private static final Color TAIL_TILE = new Color(170, 145, 80);


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

        int row = this.game.getNbRow();
        int col = this.game.getNbCol();
        int tile = Math.min(this.getHeight() / (row + 4), this.getWidth() / (col + 4));

        // Drawing wall (Top, Bottom, Left, Right)
        g.setColor(WALL_TILE);
        g.fillRect(tile, tile, (col + 2) * tile, tile);
        g.fillRect(tile, (row + 2) * tile, (col + 2) * tile, tile);
        g.fillRect(tile, tile, tile, (row + 2) * tile);
        g.fillRect((col + 2) * tile, tile, tile, (row + 2) * tile);

        // Drawing empty cell (Odd / Even)
        for (Position empty : this.game.getEmpty())
        {
            g.setColor(empty.getR() % 2 == empty.getC() % 2 ? ODD_TILE : EVEN_TILE);
            g.fillRect((empty.getC() + 2) * tile, (empty.getR() + 2) * tile, tile, tile);
        }

        // Drawing food
        Position food = this.game.getFood();
        g.setColor(FOOD_TILE);
        g.fillRect((food.getC() + 2) * tile, (food.getR() + 2) * tile, tile, tile);

        // Drawing snake (Head, Tail, Body)
        Position head = this.game.getHead();
        g.setColor(HEAD_TILE);
        g.fillRect((head.getC() + 2) * tile, (head.getR() + 2) * tile, tile, tile);

        Position tail = this.game.getTail();
        g.setColor(TAIL_TILE);
        g.fillRect((tail.getC() + 2) * tile, (tail.getR() + 2) * tile, tile, tile);

        Queue<BodyPart> body = this.game.getBody();
        g.setColor(BODY_TILE);
        for (Position p : body)
            g.fillRect((p.getC() + 2) * tile, (p.getR() + 2) * tile, tile, tile);
    }

    public void refresh()
    {
        this.repaint();
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
