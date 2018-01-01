package com.hackerrank.algo.dp;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * https://www.hackerrank.com/challenges/coin-change
 */
public class CoinChange {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int[] coins = IntStream.range(0, m).map(i -> in.nextInt()).toArray();
        in.close();

        long [] dp = new long[n+1];
        dp[0] = 1; // means first multiple or if i-coin == 0 then add 1 in the combination

        /**
         * dp(coin combination)[i] += dp(coin combination)[i-coin]
         * 
         * examples :
         * dp(1)[1] += dp(1)[0] = 0 + 1 = 1
         * dp(1)[2] += dp(1)[1] = 0 + 1 = 1
         * dp(1)[3] += dp(1)[2] = 0 + 1 = 1
         * dp(1)[4] += dp(1)[3] = 0 + 1 = 1
         * dp(1)[5] += dp(1)[4] = 0 + 1 = 1
         * 
         * dp(2,1)[1] = dp(1)[1] + dp(2,1)[-1] = 1 + 0 = 1
         * dp(2,1)[2] = dp(1)[2] + dp(2,1)[0] = 1 + 1 = 2
         * dp(2,1)[3] = dp(1)[3] + dp(2,1)[1] = 1 + 1 = 2
         * dp(2,1)[4] = dp(1)[4] + dp(2,1)[2] = 1 + 2 = 3
         * dp(2,1)[5] = dp(1)[5] + dp(2,1)[3] = 1 + 2 = 3
         */
        Arrays.stream(coins).forEach(coin -> {
            IntStream.range(coin, n+1).forEach(i -> {
                dp[i] += dp[i-coin];
            });
        });

        System.out.println(dp[n]);
    }

}
