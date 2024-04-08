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

    private Loop loop;

    public Controller() throws InterruptedException, InvocationTargetException
    {
        this.game = new Game();
        SwingUtilities.invokeAndWait(() -> this.view = new FrameGame(this, this.game));

        this.loop = new Loop(this, 200);
        this.loop.start();
    }

    public void turn(Direction direction)
    {
        this.game.turn(direction);
    }

    public void move()
    {
        this.game.move();
        if (!this.game.isOver())
            this.view.refreshGame();
        else
            this.loop.interrupt();
    }
}
