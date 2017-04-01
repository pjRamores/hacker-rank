package com.hackerrank.algo.dp;

import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/equal
 */
public class Equal {
    
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        
        byte t = in.nextByte();
        while(t-- > 0) {
            short n = in.nextShort();
            int [] arr = new int[n];
            int min = Integer.MAX_VALUE;
            
            /*
             * step 1) collect the values and get the minimum value
             */
            for(int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
                min = arr[i] < min ? arr[i] : min;
            }
            
            /*
             * step 2) reduce to 1-5 index range then group in array, count the number of operations
             */
            int operations = 0;
            int [] arr5 = new int[5];
            for(int i = 0; i < n; i++) {
                arr[i] -= min;
                operations += arr[i] / 5;
                arr5[arr[i] % 5]++;
            }
            
            /*
             * step 3) adjust the minimum valued index to zero
             */
            while(arr5[0] == 0) {
                for(int i = 0; i < 4; i++) {
                    arr[i] = arr[i+1];
                }
                arr[4] = 0;
            }
            
            /*
             * step 4) count the remaining operations
             */
            operations += arr5[1] + arr5[2];
            if(arr5[4] > arr5[0] + arr5[1] + arr5[2]) {
                operations += arr5[4] - arr5[0] - arr5[1] - arr5[2] + 2;
            } else {
                operations += (arr5[4] * 2);
            }
            if(arr5[3] > arr5[0] + arr5[1] + arr5[2] + arr5[4]) {
                operations += arr5[3] - arr5[0] - arr5[1] - arr5[2] - arr5[4] + 2;
            } else {
                operations += (arr5[3] * 2);
            }
            
            System.out.println(operations);
        }
        in.close();
    }
}
