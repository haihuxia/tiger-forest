package com.xhh.demo.http.practice.cas;

/**
 * SimulatedCAS
 * <p/>
 * Simulated CAS operation
 *
 * @author Brian Goetz and Tim Peierls
 */
public class SimulatedCAS {

    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue,
                                           int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue)
            value = newValue;
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue,
                                              int newValue) {
        return (expectedValue
                == compareAndSwap(expectedValue, newValue));
    }

    public static void main(String[] args) {
        int product = 1;
        for (int i = 10; i <= 99; i++) {
            product *= i;
        }
        System.out.println(product);
    }
}
