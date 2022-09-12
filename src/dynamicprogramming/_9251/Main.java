package dynamicprogramming._9251;

/* LCS
LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때,
모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.

ACAYKP
CAPCAK

4
 */

/* 풀이
str1 길이 x str2 길이 테이블 생성

str1[i] == str2[j]면 dp[i][j] = dp[i-1][j-1] + 1     // str2[j] 문자 포함 전 LCS 길이 + 포함 1
        !=        면 dp[i][j-1], dp[i-1][j] 중 최대값 // str2[j] 문자 미포함시 LCS 최대
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            char[] source = bufferedReader.readLine().toCharArray();
            char[] target = bufferedReader.readLine().toCharArray();

            int len = getLcsLen(source, target);
            System.out.println(len);

            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static int getLcsLen(char[] source, char[] target) {
        int sourceLen = source.length;
        int targetLen = target.length;
        int[][] dp = new int[sourceLen+1][targetLen+1];
        for (int i = 0; i < sourceLen+1; i++) {
            Arrays.fill(dp[i], 0);
        }

        for (int i = 1; i < sourceLen+1; i++) {
            for (int j = 1; j < targetLen+1; j++) {
                if (source[i-1] == target[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }

        return dp[sourceLen][targetLen];
    }
}
