package exercise;

// BEGIN
public class MaxThread extends Thread {
    private int result;
    private int[] numbers;
    @Override
    public void run() {
        int min = Integer.MIN_VALUE;
        for (int i : numbers) {
            min = Math.max(i, min);
        }
        result = min;
    }

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getResult() {
        return result;
    }
}
// END
