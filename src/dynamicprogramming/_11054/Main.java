package dynamicprogramming._11054;

/* 가장 긴 바이토닉 부분 수열
수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면,
그 수열을 바이토닉 수열이라고 한다.
예를 들어, {10, 20, 30, 25, 20}과 {10, 20, 30, 40}, {50, 40, 25, 10} 은 바이토닉 수열이지만,
{1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.
수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.

10
1 5 2 1 4 3 4 5 2 1

7
 */

/* 풀이
현재 위치까지 바이토닉 수열 길이 최댓값
    = 0에서 현재 위치까지 증가수열 길이 최댓값
        + len에서 현재 위치까지 증가수열 길이 최댓값
            - 현재 위치 중복 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int len = Integer.parseInt(bufferedReader.readLine());
            int[] sequence = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int maxLen = getMaxLenOfBitonicSequence(len, sequence);
            System.out.println(maxLen);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static int getMaxLenOfBitonicSequence(int len, int[] sequence) {
        int[][] dp = new int[2][len];
        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], 0);

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[i] > sequence[j] && dp[0][i] <= dp[0][j]) dp[0][i] = dp[0][j] + 1;
            }
        }
        for (int i = len-2; i >= 0; i--) {
            for (int j = len-1; j > i; j--) {
                if (sequence[i] > sequence[j] && dp[1][i] <= dp[1][j]) dp[1][i] = dp[1][j] + 1;
            }
        }
        for (int i = 0; i < len; i++) {
            dp[0][i] += dp[1][i];
        }

        OptionalInt maxLen = Arrays.stream(dp[0]).max();
        if (maxLen.isPresent()) return maxLen.getAsInt();
        else return 1;
    }
}
