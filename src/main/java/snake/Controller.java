package snake;

import snake.model.Direction;
import snake.model.Game;
import snake.view.ViewCUI;

public class Controller
{
    private Game game;
    private ViewCUI view;

    public Controller()
    {
        this.game = new Game();
        this.view = new ViewCUI(this, this.game);

        this.view.displayGame();
        while (!this.game.isOver())
        {
            char direction = this.view.readDirection();

            switch (direction)
            {
                case 'U' -> this.game.move(Direction.UP);
                case 'D' -> this.game.move(Direction.DOWN);
                case 'L' -> this.game.move(Direction.LEFT);
                case 'R' -> this.game.move(Direction.RIGHT);
            }

            this.view.clear();
            this.view.displayGame();
        }

        this.view.gameOver();
    }
}
