package def;

/**
 * Created by czn on 2016/1/24.
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String args[]) {
        ThreadLocalTest tt = new ThreadLocalTest();

        TestClient tc = new TestClient(tt);
        TestClient tc1 = new TestClient(tt);
        TestClient tc2 = new TestClient(tt);

        tc.start();
        tc1.start();
        tc2.start();
    }


    private static class TestClient extends Thread {

        private ThreadLocalTest tt;

        public TestClient(ThreadLocalTest tt) {
            this.tt = tt;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "] tt[" + tt.getNextNum() + "]");
            }
        }
    }
}
