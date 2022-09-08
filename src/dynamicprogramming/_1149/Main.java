package dynamicprogramming._1149;

/* RGB거리
RGB거리에는 집이 N개 있다.
거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.
각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때,
아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
- 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
- N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
- i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.

입력
3
26 40 83
49 60 57
13 89 99

출력
96

 */

/* 풀이
현재까지 코스트 = 이전까지 코스트 + 이전 집과 다른 색 현재 집 RGB 코스트
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
            int numberOfHouses = Integer.parseInt(bufferedReader.readLine());
            int[][] cost = new int[numberOfHouses][];

            for (int i = 0; i < numberOfHouses; i++) {
                cost[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            OptionalInt minCost = getMinCost(numberOfHouses, cost);
            if(minCost.isPresent())
                System.out.println(minCost.getAsInt());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    static OptionalInt getMinCost(int number, int[][] cost) {
        int[][] dp = new int[number][3];

        dp[0] = cost[0];
        for (int i = 1; i < number; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 1; i < number; i++) {
            for (int currentColor = 0; currentColor < 3; currentColor++) {
                for (int  prevColor = 0; prevColor < 3; prevColor++) {
                    if (currentColor == prevColor) continue;
                    dp[i][currentColor] = Math.min(dp[i-1][prevColor] + cost[i][currentColor], dp[i][currentColor]);
                }
            }
        }

        return Arrays.stream(dp[number-1]).min();
    }
}
