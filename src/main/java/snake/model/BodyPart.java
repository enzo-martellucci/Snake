package snake.model;

public class BodyPart extends Position
{
    private final Direction direction;

    public BodyPart(int r, int c, Direction direction)
    {
        super(r, c);
        this.direction = direction;
    }

    public Direction getDirection() { return direction; }

    public BodyPart next(Direction direction)
    {
        Position next = super.next(direction);
        return new BodyPart(next.r, next.c, direction);
    }
}
