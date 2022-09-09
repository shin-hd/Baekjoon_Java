package dynamicprogramming._1463;

/* 1로 만들기
정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
- X가 3으로 나누어 떨어지면, 3으로 나눈다.
- X가 2로 나누어 떨어지면, 2로 나눈다.
- 1을 뺀다.
정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
연산을 사용하는 횟수의 최솟값을 출력하시오.

입력
2   10

출력
1   3
 */

/* 풀이
n까지 최소연산 수 = n*3까지 최소연산, n*2까지 최소연산, n+1까지 최소연산 중 최소값 + 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int inputNumber = Integer.parseInt(bufferedReader.readLine());

            int min = getMin(inputNumber);
            System.out.println(min);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    static int getMin(int number) {
        int[] dp = new int[number*3];
        Arrays.fill(dp, Integer.MAX_VALUE - 10);
        dp[number] = 0;

        for (int i = number-1; i > 0; i--) {
            dp[i] = Math.min(dp[i*3], Math.min(dp[i*2], dp[i+1])) + 1;
        }

        return dp[1];
    }
}
