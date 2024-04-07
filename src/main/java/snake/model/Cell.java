package snake.model;

public enum Cell
{
    EMPTY     (' '),
    WALL      ('\u2588'), // █
    FOOD      ('@'), // @
    SNAKE_HEAD('O'), // O
    SNAKE_BODY('*'), // *
    SNAKE_TAIL('x'); // x

    private final char display;

    Cell(char display)
    {
        this.display = display;
    }

    public char getDisplay(){ return this.display; }
}
