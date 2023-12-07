package org.example.multiThreadMergeSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MergeSorter implements Callable<List<Integer>> {

    List<Integer> array;
    ExecutorService service;
    MergeSorter(List<Integer> array, ExecutorService service){
        System.out.println("Merge array length " + array.size());
        this.array = array;
        this.service = service;
    }
    @Override
    public List<Integer> call() throws Exception {

        int size = array.size();
        System.out.println(" size " + size);
        if(size <= 1){
            System.out.println("size is <= 1");
            return  array;
        }

        List<Integer> leftHalf = new ArrayList<>();
        List<Integer> rightHalf = new ArrayList<>();

        for(int i=0; i<size/2; i++){
            leftHalf.add(array.get(i));
        }

        for(int i=size/2; i<size; i++){
            rightHalf.add(array.get(i));
        }

        // now pass this to mergeSorter

        MergeSorter leftMg = new MergeSorter(leftHalf, service);
        MergeSorter rightMg = new MergeSorter(rightHalf, service);

        // submit this callable object to executer service
        // executer service will be running async
        // use future - similiar to promise in javascript

        Future<List<Integer>> leftFuture = service.submit(leftMg);
        Future<List<Integer>> rightFuture = service.submit(rightMg);

        List<Integer> leftSorted = leftFuture.get(); // sync call
        List<Integer> rightSorted = rightFuture.get(); // sync

        // merge two different sorted half's

        int i = 0, j=0;
        List<Integer> sortedArray = new ArrayList<>();

        while(i < leftSorted.size() && j < rightSorted.size()){
            if(leftSorted.get(i) < rightSorted.get(j)){
                sortedArray.add(leftSorted.get(i));
                i++;
            }else{
                sortedArray.add(rightSorted.get(j));
                j++;
            }
        }

        while (i< leftSorted.size()){
            sortedArray.add(leftSorted.get(i));
            i++;
        }

        while (j < rightSorted.size()){
            sortedArray.add(rightSorted.get(j));
            j++;
        }

        return sortedArray;
    }
}
