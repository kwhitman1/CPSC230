import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] arraysizes = {2, 4, 5, 10, 100, 1000, 10000};
        Random random = new Random();

        System.out.println(String.format("%-20s%-20s%-20s", "Array Size:", "Row By Row:", "Column By Column:"));

        for (int s = 0; s < arraysizes.length; s++) {
            int size = arraysizes[s];
            int[][] array = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    array[i][j] = random.nextInt(100); 
                }
            }

            long startRow = System.nanoTime();
            long sumRow = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    sumRow = sumRow + array[i][j];
                }
            }
            long endRow = System.nanoTime();
            long timeOfRowByRow = endRow - startRow;

            long startCol = System.nanoTime();
            long sumCol = 0;
            for (int j = 0; j < size; j++) {
                for (int i = 0; i < size; i++) {
                    sumCol = sumCol + array[i][j];
                }
            }
            long endCol = System.nanoTime();
            long timeOfColumnByColumn = endCol - startCol;

            String sizeLabel = size + " by " + size;
            System.out.println(String.format("%-20s%-20d%-20d", sizeLabel, timeOfRowByRow, timeOfColumnByColumn));
        }
    }
}



