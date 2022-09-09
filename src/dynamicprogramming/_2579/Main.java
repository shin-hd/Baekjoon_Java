package dynamicprogramming._2579;

/* 계단 오르기
계단 오르기 게임은 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임이다.
각각의 계단에는 일정한 점수가 쓰여 있는데 계단을 밟으면 그 계단에 쓰여 있는 점수를 얻게 된다.
계단 오르는 데는 다음과 같은 규칙이 있다.
- 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
- 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
- 마지막 도착 계단은 반드시 밟아야 한다.
따라서 첫 번째 계단을 밟고 이어 두 번째 계단이나, 세 번째 계단으로 오를 수 있다.
하지만, 첫 번째 계단을 밟고 이어 네 번째 계단으로 올라가거나, 첫 번째, 두 번째, 세 번째 계단을 연속해서 모두 밟을 수는 없다.
각 계단에 쓰여 있는 점수가 주어질 때 이 게임에서 얻을 수 있는 총 점수의 최댓값을 구하는 프로그램을 작성하시오.

6
10
20
15
25
10
20

75
 */

/* 풀이
dp[][0]은 한 칸 넘어온 최대값
dp[][1]은 두 칸 넘어온 최대값
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
            int steps = Integer.parseInt(bufferedReader.readLine());
            int[] stairs = new int[steps];

            for (int i = 0; i < steps; i++) {
                stairs[i] = Integer.parseInt(bufferedReader.readLine());
            }

            OptionalInt maxPoint = getMaxPoint(steps, stairs);
            if (maxPoint.isPresent())
                System.out.println(maxPoint.getAsInt());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    static OptionalInt getMaxPoint(int steps, int[] stairs) {
        int[][] dp = new int[steps][2];

        dp[0][0] = dp[0][1] = stairs[0];
        if (steps > 1) {
            dp[1][1] = stairs[1];
            dp[1][0] = stairs[0] + stairs[1];
        }
        for (int i = 2; i < steps; i++) {
            Arrays.fill(dp[i], 0);
        }

        for (int i = 2; i < steps; i++) {
            dp[i][0] = dp[i-1][1] + stairs[i];
            dp[i][1] = Math.max(dp[i-2][0] + stairs[i], dp[i-2][1] + stairs[i]);
        }

        return Arrays.stream(dp[steps-1]).max();
    }
}
