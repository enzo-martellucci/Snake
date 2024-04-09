package snake.controller;

import snake.model.Direction;
import snake.model.Game;
import snake.view.FrameGame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Controller
{
    private static final int REFRESH_RATE = 16; // 60 FPS (1000 / 60)
    private static final int FRAME_PER_MOVE = 5; // Speed of the snake

    private Game game;
    private FrameGame view;

    private Timer loop;
    private int animationCount;
    private int animationTotal;

    public Controller(int nbRow, int nbCol) throws InterruptedException, InvocationTargetException
    {
        this.game = new Game(nbRow, nbCol);
        SwingUtilities.invokeAndWait(() -> this.view = new FrameGame(this, this.game));

        this.loop = new Timer(REFRESH_RATE, e -> this.tick());
        this.animationCount = FRAME_PER_MOVE;
        this.animationTotal = FRAME_PER_MOVE;
    }

    public void start()
    {
        this.game.init();

        this.animationCount = FRAME_PER_MOVE;
        this.animationTotal = FRAME_PER_MOVE;
        this.loop.restart();
    }

    public void turn(Direction direction)
    {
        this.game.turn(direction);
    }

    private void tick()
    {
        if (this.animationCount == this.animationTotal)
        {
            this.animationCount = 0;
            this.game.move();
            this.view.refreshInfo();
            if (this.game.isOver())
                this.loop.stop();
        }

        this.view.refreshGame();
        this.animationCount++;
    }

    public double getProgression()
    {
        return (double) this.animationCount / this.animationTotal;
    }
}
