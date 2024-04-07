package snake.view;

import snake.Controller;
import snake.model.Cell;
import snake.model.Game;

import java.util.Scanner;

public class ViewCUI
{
    private Controller ctrl;
    private Game game;

    private Scanner scanner;

    public ViewCUI(Controller ctrl, Game game)
    {
        this.ctrl = ctrl;
        this.game = game;

        this.scanner = new Scanner(System.in);
    }

    public char readDirection()
    {
        System.out.print("Direction ([U]p, [D]own, [L]eft, [R]ight): ");
        return this.scanner.nextLine().charAt(0);
    }

    public void displayGame()
    {
        Cell[][] grid = this.game.getGrid();

        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++)
                System.out.print(grid[r][c].getDisplay());
            System.out.println();
        }
    }

    public void gameOver()
    {
        System.out.println("Game over !");
    }

    public void clear()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
