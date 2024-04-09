package snake.model;

import java.util.*;

import static snake.model.Direction.RIGHT;

public class Game
{
    private static final Random R = new Random();


    private int nbRow;
    private int nbCol;

    private Set<Position> wall;
    private Set<Position> empty;
    private Position food;

    private BodyPart head;
    private Deque<BodyPart> body;
    private BodyPart tail;

    private boolean isAlive;
    private boolean isGrowing;
    private boolean willGrow;

    private Direction nextDirection;


    public Game(int nbRow, int nbCol)
    {
        this.nbRow = nbRow + 2;
        this.nbCol = nbCol + 2;

        this.wall = new HashSet<>();
        for (int r = 0; r < this.nbRow; r++)
        {
            this.wall.add(new Position(r, 0));
            this.wall.add(new Position(r, this.nbCol - 1));
        }
        for (int c = 0; c < this.nbCol; c++)
        {
            this.wall.add(new Position(0, c));
            this.wall.add(new Position(this.nbRow - 1, c));
        }

        this.empty = new HashSet<>();
        for (int r = 1; r < this.nbRow - 1; r++)
            for (int c = 1; c < this.nbCol - 1; c++)
                this.empty.add(new Position(r, c));

        this.tail = new BodyPart((this.nbRow / 2), (this.nbCol / 2) - 2, RIGHT);
        this.body = new ArrayDeque<>();
        this.body.addFirst(this.tail.next(RIGHT));
        this.head = this.body.getFirst().next(RIGHT);

        this.empty.remove(this.tail);
        this.empty.remove(this.body.getFirst());
        this.empty.remove(this.head);

        this.nextDirection = RIGHT;

        this.isAlive = true;
        this.isGrowing = false;
        this.willGrow = false;

        this.generateFood();
    }

    public int getNbRow(){ return this.nbRow; }
    public int getNbCol(){ return this.nbCol; }

    public Set<Position> getWall(){ return this.wall; }
    public Set<Position> getEmpty(){ return this.empty; }
    public Position getFood(){ return this.food; }

    public BodyPart getHead(){ return this.head; }
    public Queue<BodyPart> getBody(){ return this.body; }
    public BodyPart getTail(){ return this.tail; }

    public boolean isOver(){ return !this.isAlive; }
    public boolean isGrowing(){ return this.isGrowing; }

    private void generateFood()
    {
        Position[] lstEmpty = this.empty.toArray(new Position[0]);
        this.food = lstEmpty[R.nextInt(lstEmpty.length)];
    }

    public void turn(Direction direction)
    {
        if (direction != this.head.getDirection() && !direction.isOpposite(this.head.getDirection()))
            this.nextDirection = direction;
    }

    public void move()
    {
        // Add new head
        BodyPart nextHead = this.head.next(this.nextDirection);
        if (!this.empty.contains(nextHead))
        {
            this.isAlive = false;
            return;
        }

        this.body.addFirst(this.head);
        this.head = nextHead;
        this.empty.remove(this.head);

        // Remove old tail
        if (!this.willGrow)
        {
            this.empty.add(this.tail);
            this.tail = this.body.removeLast();
        }

        // Detect growing
        this.isGrowing = this.willGrow;
        this.willGrow = this.head.equals(this.food);
        if (this.willGrow)
            this.generateFood();
    }
}
