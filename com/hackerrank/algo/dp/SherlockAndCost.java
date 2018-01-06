package com.hackerrank.algo.dp;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-cost/problem
 */
public class SherlockAndCost {
    
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        
        int T = in.nextInt();
        while(T-- > 0) {
            int N = in.nextInt();
            int [] B = IntStream.range(0, N).map(i -> in.nextInt()).toArray();
            System.out.println(cost(B));
        }
        
        in.close();
    }
    
    static int cost(int[] arr) {
        int high = 0;
        int low = 0;
        
        for(int i = 1; i < arr.length; i++) {
            int newHigh = Math.max(high, low + arr[i] - 1);
            int newLow = Math.max(low, high + arr[i-1] - 1);
            high = newHigh;
            low = newLow;
        }
        
        return Math.max(high, low);
    }

}
