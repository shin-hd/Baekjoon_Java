package _1912;

/* 연속합

n개의 정수로 이루어진 임의의 수열이 주어진다.
우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다.
단, 수는 한 개 이상 선택해야 한다.

예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자.
여기서 정답은 12+21인 33이 정답이 된다.

 */

/* 풀이
지금까지의 합 vs 지금부터 시작
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
            int max = Integer.parseInt(bufferedReader.readLine());
            int[] dp = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            OptionalInt maxSum = getMaxSum(max, dp);
            if(maxSum.isPresent())
                System.out.println(maxSum.getAsInt());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static OptionalInt getMaxSum(int max, int[] dp) {
        for (int i = 1; i < max; i++) {
            dp[i] = Math.max(dp[i-1] + dp[i], dp[i]);
        }
        return Arrays.stream(dp).reduce(Math::max);
    }
}
