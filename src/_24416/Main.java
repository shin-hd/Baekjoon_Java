package _24416;

/*
 * 피보나치 수 1
 *
 * 오늘도 서준이는 동적 프로그래밍 수업 조교를 하고 있다.
 * 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
 * 오늘은 n의 피보나치 수를 재귀호출과 동적 프로그래밍으로 구하는 알고리즘을 배웠다. 재귀호출에 비해 동적 프로그래밍이 얼마나 빠른지 확인해 보자.
 * 아래 의사 코드를 이용하여 n의 피보나치 수를 구할 경우 코드1 코드2 실행 횟수를 출력하자.
 *
 * 피보나치 수 재귀호출 의사 코드는 다음과 같다.
 * fib(n) {
 *     if (n = 1 or n = 2)
 *     then return 1;  # 코드1
 *     else return (fib(n - 1) + fib(n - 2));
 * }
 *
 * 피보나치 수 동적 프로그래밍 의사 코드는 다음과 같다.
 * fibonacci(n) {
 *     f[1] <- f[2] <- 1;
 *     for i <- 3 to n
 *         f[i] <- f[i - 1] + f[i - 2];  # 코드2
 *     return f[n];
 * }
 *
 * 입력
 * 첫째 줄에 n(5 ≤ n ≤ 40)이 주어진다.
 *
 * 출력
 * 코드1 코드2 실행 횟수를 한 줄에 출력한다.
 *
 * 5 >>>>> 5 3
 * 30 >>>>> 832040 28
 */

import java.io.*;

public class Main {
    private static final int MAX_NUMBER = 40;
    private final int[] f = new int[MAX_NUMBER + 1];
    private int fibCount = 1;
    private int fibonacciCount = 0;

    public static void main(String[] args) {
        try {
            Main main = new Main();
            int number;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            number = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();

            main.fib(number);
            main.fibonacci(number);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            bufferedWriter.write(main.toString());
            bufferedWriter.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return fibCount + " " + fibonacciCount;
    }

    /**
     * 피보나치 재귀함수
     */
    public int fib(int n) {
        if (n == 1 || n == 2)
            return 1;
        else {
            fibCount++;
            return fib(n - 1) + fib(n - 2);
        }
    }

    /**
     * 피보나치 DP함수
     */
    public int fibonacci(int n) {
        f[1] = f[2] = 1;
        for (int i = 3; i <= n; i++) {
            fibonacciCount++;
            f[i] = f[i-1] + f[i - 2];
        }
        return f[n];
    }
}
