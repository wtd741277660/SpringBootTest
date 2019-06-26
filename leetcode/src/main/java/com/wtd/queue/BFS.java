package com.wtd.queue;

import javafx.scene.Node;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    /**
     * Return the length of the shortest path between root and target node.
     */
    int BFS(Node root, Node target) {
        Queue<Node> queue = new LinkedList<>();  // store all nodes which are waiting to be processed
        int step = 0;       // number of steps neeeded from root to current node
        // initialize
        queue.offer(root);
        // BFS
        while (!queue.isEmpty()) {
            step = step + 1;
            // iterate the nodes which are already in the queue
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Node cur = queue.peek();
                if(cur.equals(target)){
                    return step;
                }
//                for (Node next : cur.n) {
//                    queue.offer(next);
//                }
                queue.poll();
            }
        }
        return -1;          // there is no path from root to target
    }
}
