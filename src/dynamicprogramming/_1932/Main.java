package dynamicprogramming._1932;

/* 정수 삼각형
        7
      3   8
    8   1   0
  2   7   4   4
4   5   2   6   5

위 그림은 크기가 5인 정수 삼각형의 한 모습이다.
맨 위층 7부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때,
이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라.
아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
삼각형의 크기는 1 이상 500 이하이다.
삼각형을 이루고 있는 각 수는 모두 정수이며, 범위는 0 이상 9999 이하이다.

5
7
3 8
8 1 0
2 7 4 4
4 5 2 6 5

30
 */

/* 풀이
현재 위치 최대값 = max(현재 위치 값 + 이전 값 두개 중 하나)
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
            int size = Integer.parseInt(bufferedReader.readLine());
            int[][] triangle = new int[size][];

            for (int i = 0; i < size; i++) {
                triangle[i] = Arrays.stream(bufferedReader.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            OptionalInt maxSum = getMaxSum(size, triangle);
            if(maxSum.isPresent())
                System.out.println(maxSum.getAsInt());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    static OptionalInt getMaxSum(int size, int[][] triangle) {
        for (int i = 1; i < size; i++) {
            int len = triangle[i].length;

            triangle[i][0] += triangle[i-1][0];
            triangle[i][len-1] += triangle[i-1][len-2];
            for (int j = 1; j < len-1; j++) {
                triangle[i][j] = Math.max(triangle[i-1][j-1] + triangle[i][j], triangle[i-1][j] + triangle[i][j]);
            }
        }

        return Arrays.stream(triangle[size-1]).max();
    }
}
