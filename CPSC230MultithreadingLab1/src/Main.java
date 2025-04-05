
public class Main {

	public static void main(String[] args) {
		int [] sizes = {10, 100, 1000, 10000};
		int[] threadCounts = {1, 2, 4, 10};
		
		for (int N : sizes)
		{
			System.out.println("\nArray Size: " + N);
			System.out.println();
			long longestTime = 0;
			long[] times = new long[threadCounts.length];
			
			for (int i = 0; i < threadCounts.length; i++)
			{
				int threadCount = threadCounts[i];
				MyThread[] threads = new MyThread[threadCount];
				
				long start = System.nanoTime();
				
				for (int j = 0; j < threadCount; j++)
				{
					threads[j] = new MyThread(N);
					threads[j].start();
				}
				
				for (MyThread j : threads)
				{
					try {
						j.join();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				
				long end = System.nanoTime();
				long elapsed = end - start;
				times[i] = elapsed;
				if (elapsed > longestTime) longestTime = elapsed;
				
				int totalSum = 0;
				for (MyThread j : threads)
				{
					totalSum = totalSum + j.getSum();
				}
				System.out.println("Thread Count: " + threadCount + ", Sum of Numbers in Array: " + totalSum);
			}
			System.out.println();
			
			for (int i = 0; i < threadCounts.length; i++) {
				double efficiency = (double) longestTime / times[i];
				System.out.println("Number of Threads Used: " + threadCounts[i]
				        + ", Time: " + times[i] + " ns"
				        + ", Efficiency Factor: " + String.format("%.2f", efficiency));

			}
			System.out.println();
		}
		
		
	}

}
