public class BT4 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Thread 1", null);
        MyThread thread2 = new MyThread("Thread 2", thread1);
        MyThread thread3 = new MyThread("Thread 3", thread1);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class MyThread extends Thread {
    private final String threadName;
    private final Thread waitFor;

    public MyThread(String threadName, Thread waitFor) {
        this.threadName = threadName;
        this.waitFor = waitFor;
    }

    @Override
    public void run() {
        try {
            if (waitFor != null) {
                waitFor.join();
            }
            if (threadName.equals("Thread 1")) {
                for (int i = 1; i <= 5; i++) {
                    System.out.println(threadName + " in ra số: " + i);
                    Thread.sleep(1000);
                }
            } else {
                System.out.println(threadName + " bắt đầu...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
