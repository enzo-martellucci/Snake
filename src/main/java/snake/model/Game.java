package snake.model;

import java.util.*;

public class Game
{
    private static final Random R = new Random();


    private Cell[][] grid;
    private Deque<Position> snake;

    private Direction currentDirection;
    private Direction nextDirection;

    private boolean isAlive;
    private boolean isGrowing;

    public Game()
    {
        this.grid = new Cell[21][21];
        for (int r = 0; r < this.grid.length; r++)
        {
            this.grid[r][0] = Cell.WALL;
            this.grid[r][this.grid[r].length - 1] = Cell.WALL;
        }

        for (int c = 1; c < this.grid[0].length - 1; c++)
        {
            this.grid[0][c] = Cell.WALL;
            this.grid[this.grid.length - 1][c] = Cell.WALL;
        }

        for (int r = 1; r < this.grid.length - 1; r++)
            for (int c = 1; c < this.grid[r].length - 1; c++)
                this.grid[r][c] = Cell.EMPTY;

        Position head = new Position(this.grid.length / 2, this.grid[0].length / 2);
        Position body = head.next(Direction.LEFT);
        Position tail = body.next(Direction.LEFT);

        this.snake = new ArrayDeque<>();
        this.snake.addFirst(tail);
        this.snake.addFirst(body);
        this.snake.addFirst(head);

        this.grid[head.getR()][head.getC()] = Cell.SNAKE_HEAD;
        this.grid[body.getR()][body.getC()] = Cell.SNAKE_BODY;
        this.grid[tail.getR()][tail.getC()] = Cell.SNAKE_TAIL;

        this.currentDirection = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;

        this.isAlive = true;
        this.isGrowing = false;

        this.generateFood();
    }

    public Cell[][] getGrid(){ return this.grid; }
    public boolean isOver(){ return !this.isAlive; }

    private void generateFood()
    {
        List<Position> lstPosition = new ArrayList<>();

        for (int r = 1; r < this.grid.length - 1; r++)
            for (int c = 1; c < this.grid[r].length - 1; c++)
                if (this.grid[r][c] == Cell.EMPTY)
                    lstPosition.add(new Position(r, c));

        Position food = lstPosition.get(R.nextInt(lstPosition.size()));
        this.grid[food.getR()][food.getC()] = Cell.FOOD;
    }

    public void turn(Direction nextDirection)
    {
        if (!nextDirection.isOpposite(this.currentDirection))
            this.nextDirection = nextDirection;
    }

    public void move()
    {
        this.currentDirection = this.nextDirection;

        Position prevHead = this.snake.getFirst();
        Position nextHead = prevHead.next(this.currentDirection);

        if (this.grid[nextHead.getR()][nextHead.getC()] != Cell.EMPTY && this.grid[nextHead.getR()][nextHead.getC()] != Cell.FOOD)
        {
            this.isAlive = false;
            return;
        }

        boolean willGrow = this.grid[nextHead.getR()][nextHead.getC()] == Cell.FOOD;
        this.grid[prevHead.getR()][prevHead.getC()] = Cell.SNAKE_BODY;
        this.grid[nextHead.getR()][nextHead.getC()] = Cell.SNAKE_HEAD;
        this.snake.addFirst(nextHead);

        if (!this.isGrowing)
        {
            Position prevTail = this.snake.removeLast();
            Position nextTail = this.snake.getLast();
            this.grid[prevTail.getR()][prevTail.getC()] = Cell.EMPTY;
            this.grid[nextTail.getR()][nextTail.getC()] = Cell.SNAKE_TAIL;
        }

        this.isGrowing = willGrow;
        if (willGrow)
            this.generateFood();
    }
}
