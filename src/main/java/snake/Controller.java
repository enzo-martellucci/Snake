package snake;

import snake.model.Direction;
import snake.model.Game;
import snake.view.FrameGame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Controller
{
    private Game game;
    private FrameGame view;

    public Controller() throws InterruptedException, InvocationTargetException
    {
        this.game = new Game();
        SwingUtilities.invokeAndWait(() -> this.view = new FrameGame(this, this.game));
    }

    public void move(Direction direction)
    {
        this.game.move(direction);
        this.view.refreshGame();
    }
}
