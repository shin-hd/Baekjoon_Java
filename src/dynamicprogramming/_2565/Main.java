package dynamicprogramming._2565;

/* 전깃줄
두 전봇대 A와 B 사이에 하나 둘씩 전깃줄을 추가하다 보니 전깃줄이 서로 교차하는 경우가 발생하였다.
합선의 위험이 있어 이들 중 몇 개의 전깃줄을 없애 전깃줄이 교차하지 않도록 만들려고 한다.
예를 들어, < 그림 1 >과 같이 전깃줄이 연결되어 있는 경우 A의 1번 위치와 B의 8번 위치를 잇는 전깃줄,
A의 3번 위치와 B의 9번 위치를 잇는 전깃줄, A의 4번 위치와 B의 1번 위치를 잇는 전깃줄을 없애면
남아있는 모든 전깃줄이 서로 교차하지 않게 된다.

8
1 8
3 9
2 2
4 1
6 4
10 10
9 7
7 6

3
 */

/* 풀이
전깃줄 왼쪽에 걸린 줄을 기준으로 정렬하면 왼쪽은 곂침에 고려하지 않아도 됨
왼쪽 기준으로 정렬된 오른쪽 위치의 최대증가부분수열 길이를 구하면 남길 수 있는 전깃줄 수
제거할 전깃줄 수 = 전체 전깃줄 수 - 남길 수 있는 전깃줄 수
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            int num = Integer.parseInt(bufferedReader.readLine());
            Map<Integer, Integer> wires = new HashMap<>();
            int[] left = new int[num];
            for (int i = 0; i < num; i++) {
                int[] wire = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                left[i] = wire[0];
                wires.put(wire[0], wire[1]);
            }

            int min = getMinOfWireCuts(num, left, wires);
            System.out.println(min);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static int getMinOfWireCuts(int num, int[] left, Map<Integer, Integer> wires) {
        Arrays.sort(left);
        int[] right = new int[num];

        for (int i = 0; i < num; i++) {
            right[i] = wires.get(left[i]);
        }

        int[] dp = new int[num];
        Arrays.fill(dp, 1);
        for (int i = 1; i < num; i++) {
            for (int j = 0; j < i; j++) {
                if (right[i] > right[j] && dp[i] <= dp[j])
                    dp[i] = dp[j] + 1;
            }
        }

        OptionalInt max = Arrays.stream(dp).max();
        return max.isPresent() ? num - Arrays.stream(dp).max().getAsInt() : num;
    }
}
