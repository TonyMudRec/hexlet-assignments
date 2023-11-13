package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> result = new HashMap<>();
        MinThread thread1 = new MinThread(numbers);
        MaxThread thread2 = new MaxThread(numbers);
        thread1.start();
        LOGGER.log(Level.INFO, thread1.getName() + " started");

        thread2.start();
        LOGGER.log(Level.INFO, thread2.getName() + " started");
        try {
            thread1.join();
            LOGGER.log(Level.INFO, thread1.getName() + " finished");

            thread2.join();
            LOGGER.log(Level.INFO, thread2.getName() + " finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        result.put("min", thread1.getResult());
        result.put("max", thread2.getResult());
        return result;
    }
    // END
}
