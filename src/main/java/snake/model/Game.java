package snake.model;

import java.util.*;

import static snake.model.Direction.RIGHT;

public class Game
{
    private static final Random R = new Random();


    private int nbRow;
    private int nbCol;

    private Set<Position> empty;
    private Position food;

    private BodyPart head;
    private Deque<BodyPart> body;
    private BodyPart tail;

    private boolean isAlive;
    private boolean isGrowing;

    private Direction nextDirection;


    public Game(int nbRow, int nbCol)
    {
        this.nbRow = nbRow;
        this.nbCol = nbCol;

        this.empty = new HashSet<>();
        for (int r = 0; r < this.nbRow; r++)
            for (int c = 0; c < this.nbCol; c++)
                this.empty.add(new Position(r, c));

        this.tail = new BodyPart(this.nbRow / 2, (this.nbCol / 2) - 2, RIGHT);
        this.body = new ArrayDeque<>();
        this.body.addFirst(this.tail.next(RIGHT));
        this.head = this.body.getFirst().next(RIGHT);

        this.empty.remove(this.tail);
        this.empty.remove(this.body.getFirst());
        this.empty.remove(this.head);

        this.nextDirection = RIGHT;

        this.isAlive = true;
        this.isGrowing = false;

        this.generateFood();
    }

    public int getNbRow(){ return this.nbRow; }
    public int getNbCol(){ return this.nbCol; }

    public Set<Position> getEmpty(){ return this.empty; }
    public Position getFood(){ return this.food; }

    public BodyPart getHead(){ return this.head; }
    public Queue<BodyPart> getBody(){ return this.body; }
    public BodyPart getTail(){ return this.tail; }

    public boolean isOver(){ return !this.isAlive; }

    private void generateFood()
    {
        Position[] lstEmpty = this.empty.toArray(new Position[0]);
        this.food = lstEmpty[R.nextInt(lstEmpty.length)];
    }

    public void turn(Direction direction)
    {
        if (!direction.isOpposite(this.head.getDirection()))
            this.nextDirection = direction;
    }

    public void move()
    {
        // Add new head
        this.body.addFirst(this.head);
        this.head = this.head.next(this.nextDirection);

        if (!this.empty.contains(this.head))
        {
            this.isAlive = false;
            return;
        }

        this.empty.remove(this.head);

        // Remove old tail
        if (!this.isGrowing)
        {
            this.empty.add(this.tail);
            this.tail = this.body.removeLast();
        }

        // Detect growing
        this.isGrowing = this.head.equals(this.food);
        if (this.isGrowing)
            this.generateFood();
    }
}
