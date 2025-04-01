public class BT6 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Thread 1", Thread.MIN_PRIORITY);
        MyThread thread2 = new MyThread("Thread 2", Thread.NORM_PRIORITY);
        MyThread thread3 = new MyThread("Thread 3", Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class MyThread extends Thread {
        public MyThread(String name, int priority) {
            super(name);
            setPriority(priority);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(getName() + " đang chạy với độ ưu tiên: " + getPriority());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/*
* Không, mức độ ưu tiên của các luồng không đảm bảo 100% thứ tự thực thi giữa chúng. Điều này là do:

1. Bộ lập lịch của JVM và hệ điều hành: Mức độ ưu tiên chỉ là gợi ý cho bộ lập lịch của JVM, nhưng JVM có thể không tuân theo hoàn toàn nếu hệ điều hành quyết định khác.

2. Tính chất đa nhiệm: Trong hệ thống đa nhiệm, các luồng có thể bị gián đoạn hoặc hoán đổi theo quyết định của bộ lập lịch.

3. Tính không xác định của Thread: Ngay cả khi một luồng có mức độ ưu tiên cao hơn, nó không nhất thiết được thực thi trước hoặc nhiều hơn một cách tuyệt đối.

* */
