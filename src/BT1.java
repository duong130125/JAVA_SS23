public class BT1 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();

        thread1.start();
        thread2.start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread đang chạy: " + getName());
        }
    }
}

