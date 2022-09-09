package dynamicprogramming._10844;

/* 쉬운 계단 수

45656이란 수를 보자.
이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자.
0으로 시작하는 수는 계단수가 아니다.

입력  출력
1    9
2    17
 */

/* 풀이
dp[1][k] = dp[0][k-1] + dp[0][k+1]
k = 8, 1에서는 하나만
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final int DIVIDER = 1000000000;

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int len = Integer.parseInt(bufferedReader.readLine());

            long count = countNumberOfSteps(len) % DIVIDER;
            System.out.println(count);
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static long countNumberOfSteps(int len) {
        long[][] dp = new long[2][10];
        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], 0);
        dp[0][0] = 0;

        for (int i = 1; i < len; i++) {
            dp[1][1] += dp[0][0];
            dp[1][8] += dp[0][9];
            for (int j = 1; j < 9; j++) {
                dp[1][j-1] += dp[0][j];
                dp[1][j+1] += dp[0][j];
            }
            for (int j = 0; j < 9; j++) {
                dp[1][j] %= DIVIDER;
            }
            long[] temp = dp[0];
            dp[0] = dp[1];
            dp[1] = temp;
            Arrays.fill(dp[1], 0);
        }

        return Arrays.stream(dp[0]).sum();
    }
}
