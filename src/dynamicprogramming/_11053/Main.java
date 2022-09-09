package dynamicprogramming._11053;

/* 가장 긴 증가하는 부분 수열
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

6
10 20 10 30 20 50

4

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

            int max = getMaxSubSequence(len, sequence);
            System.out.println(max);

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static int getMaxSubSequence(int len, int[] sequence) {
        int[] dp = new int[len];
        Arrays.fill(dp, 1); // 0으로 초기화하면 틀림

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[i] > sequence[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        OptionalInt max = Arrays.stream(dp).max();
        if (max.isPresent()) return max.getAsInt();
        else return 1;
    }
}
