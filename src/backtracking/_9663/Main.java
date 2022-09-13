package backtracking._9663;

/* N-Queen
N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 */

/* 풀이
x,y에 퀸 놓을 수 있다면 놓고 x+열로 진행
x,y에 놓았던 퀸 롤백한 다음 y+행으로 진행
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private final int size;
    private int maxWay = 0;
    private final int[][] dp;

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int size = Integer.parseInt(bufferedReader.readLine());
            Main main = new Main(size);

            main.findMaxWay();
            System.out.println(main.getMaxWay());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    Main(int size) {
        this.size = size;
        dp = new int[size][size];

        for (int i = 0; i < size; i++)
            Arrays.fill(dp[i], 0);
    }

    private int getMaxWay() {
        return maxWay;
    }

    private void findMaxWay() {
        if (size == 1) {
            maxWay = 1;
        } else {
            for (int i = 0; i < size; i++) {
                putQueen(0, i);
                findMaxWay(1);
                rollbackQueen(0, i);
            }
        }
    }

    private void findMaxWay(int cur) {
        for (int i = 0; i < size; i++) {
            if (cur == size-1 && dp[i][cur] == 0) {
                maxWay++;
            }
            else if (dp[i][cur] == 0) {
                putQueen(cur, i);
                findMaxWay(cur + 1);
                rollbackQueen(cur, i);
            }
        }
    }

    private void putQueen(int x, int y) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == y || j == x || Math.abs(y-i) == Math.abs(x-j))
                    dp[i][j]++;
            }
        }
    }

    private void rollbackQueen(int x, int y) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == y || j == x || Math.abs(y-i) == Math.abs(x-j))
                    dp[i][j]--;
            }
        }
    }
}
