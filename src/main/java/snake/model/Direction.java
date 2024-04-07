package snake.model;

public enum Direction
{
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public boolean isOpposite(Direction other)
    {
        return this.getOpposite() == other;
    }

    private Direction getOpposite()
    {
        return switch (this)
        {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
