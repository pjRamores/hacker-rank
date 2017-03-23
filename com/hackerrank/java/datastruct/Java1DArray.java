package com.hackerrank.java.datastruct;

import java.io.*;
import java.util.*;

/*
* https://www.hackerrank.com/challenges/java-1d-array
*/
public class Java1DArray {

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int [] arr = new int[n];
            
            int consecutiveOnes = 0;
            for(int i = 0; i < n; i++) {
                if(consecutiveOnes > 0 && consecutiveOnes >= m) {
                    in.nextLine();
                    break; //if consecutive 1's > m, then there is no way to win the game
                }
                
                arr[i] = in.nextInt();
                
                if(arr[i] == 1) {
                    consecutiveOnes++;
                    for(int j = i-1; j > 0; j--) {
                        if(arr[j] != -1) break;
                        arr[j] = 1; /* index is not reachable so mark it as 1 */
                        consecutiveOnes++;
                    }
                    continue;
                }
                
                /* check if current position is reachable */
                if(i == 0 || (i > 0 && arr[i-1] == 0) || (i >= m && arr[i-m] == 0)) {
                    consecutiveOnes = 0;
                    for(int j = i-1; j > 0; j--) {
                        if(arr[j] != -1) break;
                        arr[j] = 0; /* index is reachable so mark it as 0 */
                    }
                    continue;
                }
                
                arr[i] = -1; /* mark as unknown if this index is reachable or not */
            }
            
            if(consecutiveOnes > 0 && consecutiveOnes >= m) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
        in.close();
    }
}
