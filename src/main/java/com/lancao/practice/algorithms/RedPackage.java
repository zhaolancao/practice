package com.lancao.practice.algorithms;

import java.util.concurrent.ThreadLocalRandom;

public class RedPackage {
    public static double[] solution(double amount, int num, double bottom) {
        double[] cutPoints = new double[num];
        int count = 0;
        while (count < num - 1) {
            double current = ThreadLocalRandom.current().nextDouble(bottom, amount - bottom);
            int i = count;
            while (i > 0 && current < cutPoints[i - 1]) {
                cutPoints[i] = cutPoints[i - 1];
                i--;
            }
            if (current == cutPoints[i]) {
                continue;
            }
            cutPoints[i] = current;
            count++;
        }
        cutPoints[count] = amount - cutPoints[count - 1];
        count--;
        while (count > 0) {
            cutPoints[count] -= cutPoints[count - 1];
            count--;
        }
        return cutPoints;
    }
}
