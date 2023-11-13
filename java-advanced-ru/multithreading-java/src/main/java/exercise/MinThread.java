package exercise;

// BEGIN
public class MinThread extends Thread {
    private int result;
    private int[] numbers;
    @Override
    public void run() {
        int min = Integer.MAX_VALUE;
        for (int i : numbers) {
            min = Math.min(i, min);
        }
        result = min;
    }

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getResult() {
        return result;
    }
}
// END
