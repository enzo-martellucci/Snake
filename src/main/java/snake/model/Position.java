package snake.model;

public class Position
{
    private final int r;
    private final int c;

    public Position(int r, int c)
    {
        this.r = r;
        this.c = c;
    }

    public int getR(){ return this.r; }
    public int getC(){ return this.c; }

    public Position next(Direction direction)
    {
        return switch (direction)
        {
            case UP -> new Position(this.r - 1, this.c);
            case DOWN -> new Position(this.r + 1, this.c);
            case LEFT -> new Position(this.r, this.c - 1);
            case RIGHT -> new Position(this.r, this.c + 1);
        };
    }
}
