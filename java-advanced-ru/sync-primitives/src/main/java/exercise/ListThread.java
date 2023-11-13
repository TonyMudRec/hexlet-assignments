package exercise;

// BEGIN
public class ListThread extends Thread {
    private SafetyList list;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                sleep(1);
                list.add((int) (Math.random() * 10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ListThread(SafetyList list) {
        this.list = list;
    }
}
// END
