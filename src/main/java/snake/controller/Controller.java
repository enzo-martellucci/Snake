package snake.controller;

import snake.model.Direction;
import snake.model.Game;
import snake.view.FrameGame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Controller
{
    private Game game;
    private FrameGame view;

    private Timer loop;
    private int animationCount;
    private int animationTotal;

    public Controller(int nbRow, int nbCol) throws InterruptedException, InvocationTargetException
    {
        this.game = new Game(nbRow, nbCol);
        SwingUtilities.invokeAndWait(() -> this.view = new FrameGame(this, this.game));

        this.loop = new Timer(16, e -> this.tick());
        this.animationCount = 5;
        this.animationTotal = 5;
        this.loop.start();
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
