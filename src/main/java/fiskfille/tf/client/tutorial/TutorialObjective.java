package fiskfille.tf.client.tutorial;

public class TutorialObjective
{
    private boolean status = false;
    private boolean completing = false;

    public boolean get()
    {
        return status;
    }

    public void set(boolean b)
    {
        status = b;

        if (!b)
        {
            completing = false;
        }
    }

    public boolean midCompletion()
    {
        return !status && completing;
    }

    public void complete(final float delay)
    {
        if (!completing)
        {
            completing = true;

            new Thread()
            {
                public void run()
                {
                    delay(delay);
                    set(true);
                }
            }.start();
        }
    }

    private void delay(final float seconds)
    {
        new Thread()
        {
            public synchronized void start()
            {
                try
                {
                    sleep((long)(seconds * 1000F));
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            };
        }.start();
    }
}
