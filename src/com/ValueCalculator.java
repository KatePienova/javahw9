package com;

import java.util.Arrays;

public class ValueCalculator implements Runnable {
    private final int arrayL = 2000000;
    private final int halfArrayL = arrayL / 2;
    private float[] array = new float[arrayL];

    ValueCalculator() {
    }

    ValueCalculator(float[] array) {
        this.array = array;
    }

    void doCalc(float el) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        float[] destArray1 = new float[halfArrayL];
        float[] destArray2 = new float[halfArrayL];
        Arrays.fill(array, el);
        System.arraycopy(array, 0, destArray1,
                0, halfArrayL);
        System.arraycopy(array, halfArrayL,
                destArray2, 0, halfArrayL);
        final Thread thread1 = new Thread(new ValueCalculator(destArray1));
        final Thread thread2 = new Thread(new ValueCalculator(destArray2));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.arraycopy(destArray1, 0, array, 0, halfArrayL);
        System.arraycopy(destArray2, 0, array, halfArrayL, halfArrayL);
        System.out.println("Время в миллисекундах: " + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void run() {
        for (int i = 0; i < halfArrayL; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
