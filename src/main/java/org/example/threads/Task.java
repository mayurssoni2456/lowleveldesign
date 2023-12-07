package org.example.threads;

public class Task implements Runnable {

    int number;
    public Task(int number){
        this.number = number;
    }
    @Override
    public void run() {
        System.out.println(this.number + " " + Thread.currentThread().getName());
    }
}