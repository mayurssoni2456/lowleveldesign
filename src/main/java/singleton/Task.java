package singleton;

public class Task implements Runnable {

    @Override
    public void run() {
        // create singleTon object instance
        DBConnection dbInstance = DBConnection.getDBInstance();
        System.out.println("Thread " + Thread.currentThread().getName() + "dbInstance " + dbInstance);
    }
}
