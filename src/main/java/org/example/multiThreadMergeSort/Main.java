package org.example.multiThreadMergeSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> listToSort = List.of(1,5, 4, 3, 2, 8, 7, 6);

        // if thread are available it will reuse
        // else create a new thread.
        // since we are solving this using recursion, we are not sure about the number
        // of thread, hence cacheThreadPool();

        ExecutorService service = Executors.newCachedThreadPool();

        MergeSorter mergeSorter = new MergeSorter(listToSort, service);

        Future<List<Integer>> futureArray = service.submit(mergeSorter);

        List<Integer> sortedArray = futureArray.get();

        System.out.println(sortedArray);


    }
}
