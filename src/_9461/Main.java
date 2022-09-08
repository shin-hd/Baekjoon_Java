package _9461;

/* 파도반 수열
오른쪽 그림과 같이 삼각형이 나선 모양으로 놓여져 있다.
첫 삼각형은 정삼각형으로 변의 길이는 1이다.
그 다음에는 다음과 같은 과정으로 정삼각형을 계속 추가한다.
나선에서 가장 긴 변의 길이를 k라 했을 때, 그 변에 길이가 k인 정삼각형을 추가한다.
파도반 수열 P(N)은 나선에 있는 정삼각형의 변의 길이이다.
P(1)부터 P(10)까지 첫 10개 숫자는 1, 1, 1, 2, 2, 3, 4, 5, 7, 9이다.
N이 주어졌을 때, P(N)을 구하는 프로그램을 작성하시오.

2
6
12

3
16
 */

import java.io.*;

public class Main {
    private static final int MAXIMUM = 100;
    private final long[] dp = new long[MAXIMUM];

    public static void main(String[] args) {
        Main main = new Main();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder stringBuilder = new StringBuilder();
            int loopCount = Integer.parseInt(bufferedReader.readLine());

            while(loopCount-- > 0) {
                int number = Integer.parseInt(bufferedReader.readLine());
                stringBuilder.append(main.getPadovan(number));
                stringBuilder.append('\n');
            }

            bufferedWriter.write(stringBuilder.toString());
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    Main() {
        dp[0] = dp[1] = dp[2] = 1;
        dp[3] = dp[4] = 2;

        for (int i = 5; i < MAXIMUM; i++) {
            dp[i] = dp[i-5] + dp[i-1];
        }
    }

    long getPadovan(int number) {
        return dp[number-1];
    }
}
