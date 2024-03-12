package com.companyname.stringquestions;

public class EvenOddPrinter {
    private static int count = 1;
    private static final int MAX_COUNT = 10; // Define maximum count of numbers to print
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread evenThread = new Thread(EvenOddPrinter::printEven);
        Thread oddThread = new Thread(EvenOddPrinter::printOdd);

        evenThread.start();
        oddThread.start();
    }

    public static void printEven() {
        synchronized (lock) {
            while (count <= MAX_COUNT) {
                if (count % 2 == 0) {
                    System.out.println("Even: " + count);
                    count++;
                    lock.notify(); // Notify the waiting thread (printOdd) to acquire the lock
                } else {
                    try {
                        lock.wait(); // Wait for the odd numbers thread to print an odd number
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void printOdd() {
        synchronized (lock) {
            while (count <= MAX_COUNT) {
                if (count % 2 != 0) {
                    System.out.println("Odd: " + count);
                    count++;
                    lock.notify(); // Notify the waiting thread (printEven) to acquire the lock
                } else {
                    try {
                        lock.wait(); // Wait for the even numbers thread to print an even number
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
