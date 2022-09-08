package dynamicprogramming._9184;

/* 신나는 함수 실행
재귀 호출만 생각하면 신이 난다! 아닌가요?

다음과 같은 재귀함수 w(a, b, c)가 있다.
if a <= 0 or b <= 0 or c <= 0, then w(a, b, c) returns:
    1
if a > 20 or b > 20 or c > 20, then w(a, b, c) returns:
    w(20, 20, 20)
if a < b and b < c, then w(a, b, c) returns:
    w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c)
otherwise it returns:
    w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1)

위의 함수를 구현하는 것은 매우 쉽다. 하지만, 그대로 구현하면 값을 구하는데 매우 오랜 시간이 걸린다. (예를 들면, a=15, b=15, c=15)
a, b, c가 주어졌을 때, w(a, b, c)를 출력하는 프로그램을 작성하시오.
 */

import java.io.*;

public class Main {
    private static final int MAX_NUMBER = 21;
    private final int[][][] table = new int[MAX_NUMBER][MAX_NUMBER][MAX_NUMBER];

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder stringBuilder = new StringBuilder();
            Main main = new Main();

            for (String[] numbers = bufferedReader.readLine().split(" ");
                 Integer.parseInt(numbers[0]) != -1 || Integer.parseInt(numbers[1]) != -1 || Integer.parseInt(numbers[2]) != -1;
                 numbers = bufferedReader.readLine().split(" "))
            {
                int result = main.w(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]));

                stringBuilder.append("w(");
                stringBuilder.append(numbers[0]);
                stringBuilder.append(", ");
                stringBuilder.append(numbers[1]);
                stringBuilder.append(", ");
                stringBuilder.append(numbers[2]);
                stringBuilder.append(") = ");
                stringBuilder.append(result);
                stringBuilder.append('\n');
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            bufferedWriter.write(stringBuilder.toString());

            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Main() {
        for (int i = 0; i < MAX_NUMBER; i++) {
            for (int j = 0; j < MAX_NUMBER; j++) {
                for (int k = 0; k < MAX_NUMBER; k++)
                    table[i][j][k] = Integer.MIN_VALUE;
            }
        }
    }

    private int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0)
            return 1;
        else if (a > 20 || b > 20 || c > 20) {
            return w(20, 20, 20);
        }
        else if (table[a][b][c] == Integer.MIN_VALUE) {
            if (a < b && b < c)
                table[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
            else
                table[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
        }
        return table[a][b][c];
    }
}
