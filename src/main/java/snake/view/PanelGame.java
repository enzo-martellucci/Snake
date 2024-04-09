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
    private static final Color DEAD_TILE = Color.RED;


    private final Controller ctrl;
    private final Game game;

    public PanelGame(Controller ctrl, Game game)
    {
        // Parameters
        this.ctrl = ctrl;
        this.game = game;

        int size = (int) (Math.min(SCREEN.width, SCREEN.height) * 0.7);
        this.setPreferredSize(new Dimension(size, size));

        this.setBackground(Color.WHITE);

        // Key bindings
        InputMap inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("pressed UP"), "UP");
        inputMap.put(KeyStroke.getKeyStroke("pressed DOWN"), "DOWN");
        inputMap.put(KeyStroke.getKeyStroke("pressed LEFT"), "LEFT");
        inputMap.put(KeyStroke.getKeyStroke("pressed RIGHT"), "RIGHT");
        ActionMap actionMap = this.getActionMap();
        actionMap.put("UP", new MoveAction(() -> this.ctrl.turn(Direction.UP)));
        actionMap.put("DOWN", new MoveAction(() -> this.ctrl.turn(Direction.DOWN)));
        actionMap.put("LEFT", new MoveAction(() -> this.ctrl.turn(Direction.LEFT)));
        actionMap.put("RIGHT", new MoveAction(() -> this.ctrl.turn(Direction.RIGHT)));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int w = this.getWidth(), h = this.getHeight();
        int r = this.game.getNbRow(), c = this.game.getNbCol();
        int tile = Math.min(h / r, w / c);
        int progression = (int)(this.ctrl.getProgression() * tile);

        g.translate((w - (tile * c)) / 2, (h - (tile * r)) / 2);
        this.paintWall(g, tile);
        this.paintEmpty(g, tile);
        this.paintFood(g, tile);
        this.paintBody(g, tile);
        this.paintHead(g, tile, progression);
        this.paintTail(g, tile, progression);

    }

    private void paintWall(Graphics g, int tile)
    {
        g.setColor(WALL_TILE);
        for (Position wall : this.game.getWall())
            g.fillRect(wall.getR() * tile, wall.getC() * tile, tile, tile);
    }

    private void paintEmpty(Graphics g, int tile)
    {
        for (Position empty : this.game.getEmpty())
        {
            g.setColor(empty.getR() % 2 == empty.getC() % 2 ? ODD_TILE : EVEN_TILE);
            g.fillRect(empty.getC() * tile, empty.getR() * tile, tile, tile);
        }
    }

    private void paintFood(Graphics g, int tile)
    {
        Position food = this.game.getFood();
        g.setColor(FOOD_TILE);
        g.fillRect(food.getC() * tile, food.getR() * tile, tile, tile);
    }

    private void paintBody(Graphics g, int tile)
    {
        Queue<BodyPart> body = this.game.getBody();
        g.setColor(BODY_TILE);
        for (Position p : body)
            g.fillRect(p.getC() * tile, p.getR() * tile, tile, tile);
    }

    private void paintHead(Graphics g, int tile, int progression)
    {
        BodyPart head = this.game.getHead();
        if (this.game.isOver())
        {
            g.setColor(DEAD_TILE);
            g.fillRect(head.getC() * tile, head.getR() * tile, tile, tile);
        } else
        {
            Color cbHead = head.getR() % 2 == head.getC() % 2 ? ODD_TILE : EVEN_TILE;
            this.paintAnimation(g, tile, head, progression, cbHead, HEAD_TILE);
        }
    }

    private void paintTail(Graphics g, int tile, int progression)
    {
        BodyPart tail = this.game.getTail();
        if (this.game.isGrowing() || this.game.isOver())
        {
            g.setColor(TAIL_TILE);
            g.fillRect(tail.getC() * tile, tail.getR() * tile, tile, tile);
        } else
            this.paintAnimation(g, tile, tail, progression, BODY_TILE, TAIL_TILE);
    }

    private void paintAnimation(Graphics g, int tile, BodyPart part, int progression, Color cB, Color cF)
    {
        int r = part.getR(), c = part.getC();
        int offsetH = 0, offsetV = 0;
        switch (part.getDirection())
        {
            case UP -> offsetV -= (progression - tile);
            case DOWN -> offsetV += (progression - tile);
            case LEFT -> offsetH -= (progression - tile);
            case RIGHT -> offsetH += (progression - tile);
        }

        g.setColor(cB);
        g.fillRect(c * tile, r * tile, tile, tile);
        g.setColor(cF);
        g.fillRect(c * tile + offsetH, r * tile + offsetV, tile, tile);
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
