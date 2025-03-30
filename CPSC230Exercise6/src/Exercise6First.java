public class Exercise6First extends Thread
{
    private int count;
    private String name;
    
    public static void main(String[] args)
    {
        int numThreads = 10;
        
        for (int i = 0; i < numThreads; i++)
        {
            Exercise6First thread = new Exercise6First ("Thread " + i, i+3);
            thread.start ();
        }
    }
    
    public Exercise6First (String n, int c)
    {
        name = n;
        count = c;
    }
    
    public synchronized void run ()
    {
        for (int i = 0; i < count; i++)
            System.out.println (name + " " + i);
        	
    }
}