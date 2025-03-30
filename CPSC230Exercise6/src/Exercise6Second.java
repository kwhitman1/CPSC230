public class Exercise6Second extends Thread
{
    private static Integer threadCount = 0;
    private int count;
    private String name;
    
    public static void main(String[] args)
    {
        int numThreads = 10;
        
        for (int i = 0; i < numThreads; i++)
        {
            Exercise6Second thread = new Exercise6Second ("Thread " + i, i+3);
            thread.start ();
        }
    }
    
    public Exercise6Second (String n, int c)
    {
        name = n;
        count = c;
    }
    
    public void run ()
    {
    	
    	synchronized(threadCount) 
    	{
    	threadCount++;
    	}
        
        for (int i = 0; i < count; i++)
            System.out.println (name + " " + i);
        
        synchronized(threadCount)
        {
        threadCount--;
        }
    }
}