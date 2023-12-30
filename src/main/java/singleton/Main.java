package singleton;

public class Main {
    public static void main(String[] args) {

        Task task1 = new Task();
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task1);
        Thread t3 = new Thread(task1);
        Thread t4 = new Thread(task1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
