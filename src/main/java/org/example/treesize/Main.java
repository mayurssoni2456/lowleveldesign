package org.example.treesize;


import com.sun.source.tree.Tree;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);

        tree.left.left = new Node(4);
        tree.left.right = new Node(5);

        tree.right.left = new Node(6);
        tree.right.right = new Node(7);

        ExecutorService service = Executors.newCachedThreadPool();
        TreeSizeCalculator treeSize = new TreeSizeCalculator(tree, service);

        Future<Integer> future = service.submit(treeSize);

        Integer size = future.get();

    }
}
