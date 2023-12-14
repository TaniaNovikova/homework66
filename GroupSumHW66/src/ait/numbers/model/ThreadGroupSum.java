package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {

    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    // TODO Homework: reduce sum numbers of numberGroups, use Threads
    @Override
    public int computeSum() throws InterruptedException {
        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new OneGroupSum();
        }
        Thread[] threads = new Thread[tasks.length];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        int res = Arrays.stream(tasks).mapToInt(OneGroupSum::getSum).sum();
        System.out.println(res);
        return res;
    }
}

