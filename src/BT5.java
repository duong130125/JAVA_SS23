public class BT5 {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();
        Writer writer = new Writer(sharedData);
        Reader reader = new Reader(sharedData);

        writer.start();
        reader.start();
    }


    static class Reader extends Thread {
        private final SharedData sharedData;

        public Reader(SharedData sharedData) {
            this.sharedData = sharedData;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                sharedData.readMessage();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Writer extends Thread {
        private final SharedData sharedData;

        public Writer(SharedData sharedData) {
            this.sharedData = sharedData;
        }

        @Override
        public void run() {
            String[] messages = {"Hello", "How are you?", "Goodbye"};
            for (String msg : messages) {
                sharedData.writeMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class SharedData {
        private String message;
        private boolean hasMessage = false;

        public synchronized void writeMessage(String msg) {
            while (hasMessage) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            message = msg;
            hasMessage = true;
            System.out.println("Người gửi: " + msg);
            notify();
        }

        public synchronized void readMessage() {
            while (!hasMessage) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Người nhận: " + message);
            hasMessage = false;
            notify();
        }
    }
}
