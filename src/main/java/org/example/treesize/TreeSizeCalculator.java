package org.example.treesize;

import com.sun.source.tree.Tree;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TreeSizeCalculator implements Callable<Integer> {

    Node root;
    ExecutorService service;
    TreeSizeCalculator(Node root, ExecutorService service){
        this.root = root;
        this.service = service;
    }

    @Override
    public Integer call() throws Exception {

        Integer size;

        if(this.root == null) {
            return 0;
        }else{
            TreeSizeCalculator leftTree = new TreeSizeCalculator(this.root.left, this.service);
            TreeSizeCalculator rightTree = new TreeSizeCalculator(this.root.right, this.service);

            Future<Integer> leftTreeFuture = service.submit(leftTree);
            Future<Integer> rightTreeFuture = service.submit(rightTree);

            Integer leftSize = leftTreeFuture.get() + 1;
            Integer rightSize = rightTreeFuture.get();
            System.out.println("left size " + leftSize + " " + rightSize);
            return leftSize + rightSize;
        }
    }
}
