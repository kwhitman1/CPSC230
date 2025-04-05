import java.util.Random;

public class MyThread extends Thread {
	private int N;
	private int sum = 0;
	
	public MyThread(int N)
	{
		this.N = N;
	}
	
	public void run()
	{
		int [] randomNumbers = new int[N];
		Random random = new Random();
		
		for (int i = 0; i < N; i++)
		{
			randomNumbers[i] = random.nextInt(100) + 1;
		}
		
		int i = 0;
		
		while (i < N)
		{
			sum += randomNumbers[i];
			i++;
			
		
		}
		
		
		
	}
	public int getSum()
	{
		return sum;
	}

}
