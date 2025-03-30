import java.util.ArrayList;
import java.util.List;

public class Exercise6Third extends Thread
{
    private static Integer threadCount = 0;
    private int count;
    private String name;
    private List<String> output;
    
    public static void main(String[] args)
    {
        List<String> output = new ArrayList<String> ();
        int numThreads = 10;
        
        for (int i = 0; i < numThreads; i++)
        {
            Exercise6Third thread = new Exercise6Third ("Thread " + i, i+3, output);
            thread.start ();
        }
        
        // Give time for the threads to get going
        try
        {
            sleep (1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        while (true)
        {
        	synchronized(output)
        	{
            while (output.size() > 0)
            {
                System.out.println (output.remove(0));
            }
        	}
			
        	synchronized(threadCount)
        	{
            if (threadCount == 0)
                break;
        	}
        }
    }
    
    public Exercise6Third (String n, int c, List<String> o)
    {
        name = n;
        count = c;
        output = o;
    }
    
    public void run ()
    {
    	synchronized(threadCount)
    	{
        threadCount++;
    	}
        
    	synchronized(output)
    	{
        for (int i = 0; i < count; i++)
            output.add(name + " " + i);
    	}
        
        synchronized(threadCount)
        {
        threadCount--;
        }
    }
}