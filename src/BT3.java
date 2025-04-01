public class BT3 {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(new MyRunnable(sharedResource));
        Thread thread2 = new Thread(new MyRunnable(sharedResource));

        thread1.start();
        thread2.start();
    }

    static class SharedResource {
        public synchronized void printAlphabet() {
            for (char c = 'A'; c <= 'Z'; c++) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    static class MyRunnable implements Runnable {
        private final SharedResource sharedResource;

        public MyRunnable(SharedResource sharedResource) {
            this.sharedResource = sharedResource;
        }

        @Override
        public void run() {
            sharedResource.printAlphabet();
        }
    }

}


