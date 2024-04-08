package snake;

import snake.controller.Controller;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            new Controller();
        } catch (Exception e)
        {
            System.out.println("Couldn't start the game");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
