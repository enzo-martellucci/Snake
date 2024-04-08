package snake.controller;

public class Loop extends Thread
{
    private final Controller ctrl;

    private final int interval;

    public Loop(Controller ctrl, int interval)
    {
        this.ctrl = ctrl;

        this.interval = interval;
    }

    @Override
    public void run()
    {
        while (!this.isInterrupted())
        {
            try
            {
                Thread.sleep(this.interval);
            } catch (InterruptedException e) { break; }

            this.ctrl.move();
        }

        System.out.println("Over");
    }
}
