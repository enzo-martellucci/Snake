package snake.model;

import java.awt.*;

public enum Cell
{
    EMPTY     (Color.GREEN),
    WALL      (Color.DARK_GRAY), // █
    FOOD      (Color.PINK), // @
    SNAKE_HEAD(Color.RED), // O
    SNAKE_BODY(Color.BLUE), // *
    SNAKE_TAIL(Color.YELLOW); // x

    private final Color color;

    Cell(Color color)
    {
        this.color = color;
    }

    public Color getColor(){ return this.color; }
}
