package backtracking._14888;

/* 연산자 끼워넣기
N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다.
연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.

우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다.
이때, 주어진 수의 순서를 바꾸면 안 된다.

예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고,
주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는
총 60가지의 식을 만들 수 있다.

예를 들어, 아래와 같은 식을 만들 수 있다.
- 1+2+3-4×5÷6
- 1÷2+3+4-5×6
- 1+2÷3×4-5+6
- 1÷2×3-4+5+6

식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다.
또, 나눗셈은 정수 나눗셈으로 몫만 취한다.
음수를 양수로 나눌 때는 C++14의 기준을 따른다.
즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다.
이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.
- 1+2+3-4×5÷6 = 1
- 1÷2+3+4-5×6 = 12
- 1+2÷3×4-5+6 = 5
- 1÷2×3-4+5+6 = 7

N개의 수와 N-1개의 연산자가 주어졌을 때,
만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int len = Integer.parseInt(bufferedReader.readLine());
            int[] numbers = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Operators operators = new Operators(Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());

            Interleaver interleaver = new Interleaver(len, numbers, operators);
            interleaver.run();
            System.out.print(interleaver);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Interleaver {
        private final int len;
        private final int[] numbers;
        private final Operators operators;
        private int max = Integer.MIN_VALUE;
        private int min = Integer.MAX_VALUE;

        Interleaver(int len, int[] numbers, Operators operators) {
            this.len = len;
            this.numbers = numbers;
            this.operators = operators;
        }

        @Override
        public String toString() {
            return max + "\n" + min + "\n";
        }

        public void run() {
            calc(1, numbers[0]);
        }

        private void calc(int cur, int result) {
            if (cur == len) {
                max = Math.max(result, max);
                min = Math.min(result, min);
                return;
            }

            if (operators.plus > 0) {
                operators.plus--;
                calc(cur+1, result + numbers[cur]);
                operators.plus++;
            }
            if (operators.minus > 0) {
                operators.minus--;
                calc(cur+1, result - numbers[cur]);
                operators.minus++;
            }
            if (operators.multiply > 0) {
                operators.multiply--;
                calc(cur+1, result * numbers[cur]);
                operators.multiply++;
            }
            if (operators.divide > 0) {
                operators.divide--;
                calc(cur+1, result / numbers[cur]);
                operators.divide++;
            }
        }
    }

    private static class Operators {
        int plus;
        int minus;
        int multiply;
        int divide;

        Operators(int[] operators) {
            this.plus = operators[0];
            this.minus = operators[1];
            this.multiply = operators[2];
            this.divide = operators[3];
        }
    }
}
