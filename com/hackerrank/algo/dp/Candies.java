package com.hackerrank.algo.dp;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/candies/problem
 * 
 * <br><br>
 * t<sub>i</sub> : total candies after <sub>i</sub>th child
 * <br>
 * d<sub>i</sub> : number of decrements at <sub>i</sub>th child
 * <br>
 * c<sub>i</sub> : number of candies of <sub>i</sub>th child
 * <br>
 * l<sub>i</sub> : (last increment number of candies) number of candies of the last child before decrementing
 * <br>
 * p : rating of previous child in the line
 * <br>
 * n : rating of next child in the line
 * 
 * <br><br>
 * t<sub>i</sub> = t<sub>i-1</sub> + (d<sub>i</sub> > 1 ? (d<sub>i</sub>-1) : 0) + (d<sub>i</sub> >= 1 && l<sub>i</sub> > 1 ? 1 : 0)
 * 
 * <br><br>
 * Runtime : O(n) - linear
 * <br>
 * Memory : O(6) - constant
 */
public class Candies {
    
    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt() - 1;
        int d = 1, c = 1, l = c, p = in.nextInt(), n = 0;
        long t = c;
        while(N-- > 0) {
            n = in.nextInt();
            if(n > p) {
                d = 0;
                c++;
                l = c;
            } else if(n == p) {
                l = c = d = 1;
            } else {
                d++;
                c = 1;
            }
            if(d > 1) {
                t += (d - 1);
            }
            if(d >= l && l > 1) {
                t++;
            }
            t += c;
            p = n;
        }

        System.out.println(t);

        in.close();
    }
}
