package snake.model;

import java.util.Objects;

public class Position
{
    protected final int r;
    protected final int c;

    public Position(int r, int c)
    {
        this.r = r;
        this.c = c;
    }

    public int getR(){ return this.r; }
    public int getC(){ return this.c; }

    @Override
    public boolean equals(Object o)
    {
        Position other = (Position) o;
        return this.r == other.r && this.c == other.c;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(r, c);
    }

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
