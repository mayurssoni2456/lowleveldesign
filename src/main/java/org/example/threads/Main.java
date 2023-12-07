package org.example.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        /*
        for(int i=1; i<=100; i++){
            Task number = new Task(i);
            Thread t1 = new Thread(number);
            t1.start();
        }*/

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i=1; i<=100; i++){
            Task number = new Task(i);
            executorService.submit(number);
        }

        executorService.shutdown();

    }
}
