package com.companyname.stringquestions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExceptionHandlerExample {
    public static void main(String[] args) {
        // Create a fixed-size thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks to the thread pool
        for (int i = 0; i < 5; i++) {
            executor.submit(new Worker(i));
        }

        // Shutdown the thread pool
        executor.shutdown();
    }
}

class Worker implements Runnable {
    private final int taskId;

    public Worker(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            // Simulate some task execution
            System.out.println("Task " + taskId + " started");
            if (taskId == 3) {
                // Simulate an exception occurring in task 3
                throw new RuntimeException("Exception occurred in task " + taskId);
            }
            Thread.sleep(1000);
            System.out.println("Task " + taskId + " completed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Preserve interrupt status
        } catch (Exception e) {
            System.err.println("Exception occurred in task " + taskId + ": " + e.getMessage());
            // Perform cleanup operations if necessary
        }
    }
}
