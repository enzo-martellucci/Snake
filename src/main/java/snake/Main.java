package snake;

import snake.controller.Controller;

public class Main
{
    private static final int DEFAULT_ROW = 15;
    private static final int DEFAULT_COL = 15;

    public static void main(String[] args)
    {
        try
        {
            new Controller(DEFAULT_ROW, DEFAULT_COL);
        } catch (Exception e)
        {
            System.out.println("Couldn't start the game");
            System.out.println(e.getMessage());
        }
    }
}
