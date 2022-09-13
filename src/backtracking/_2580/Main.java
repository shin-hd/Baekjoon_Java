package backtracking._2580;

/* 스도쿠
스도쿠는 18세기 스위스 수학자가 만든 '라틴 사각형'이랑 퍼즐에서 유래한 것으로 현재 많은 인기를 누리고 있다.
이 게임은 아래 그림과 같이 가로, 세로 각각 9개씩 총 81개의 작은 칸으로 이루어진 정사각형 판 위에서 이뤄지는데, 게임 시작 전 일부 칸에는 1부터 9까지의 숫자 중 하나가 쓰여 있다.
나머지 빈 칸을 채우는 방식은 다음과 같다.
- 각각의 가로줄과 세로줄에는 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
- 굵은 선으로 구분되어 있는 3x3 정사각형 안에도 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
위의 예의 경우, 첫째 줄에는 1을 제외한 나머지 2부터 9까지의 숫자들이 이미 나타나 있으므로 첫째 줄 빈칸에는 1이 들어가야 한다.
게임 시작 전 스도쿠 판에 쓰여 있는 숫자들의 정보가 주어질 때 모든 빈 칸이 채워진 최종 모습을 출력하는 프로그램을 작성하시오.
 */

import java.io.*;
import java.util.*;

public class Main {
    private static final int SIZE = 9;
    private final boolean[] checker = new boolean[9];
    public final int[][] board;
    private boolean foundAnswer = false;
    private final List<Pos> posList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int[][] board = new int[SIZE][SIZE];

            for (int i = 0; i < SIZE; i++) {
                board[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            Main main = new Main(board);
            main.fillBoard(0);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

            bufferedWriter.write(main.toString());

            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    Main(int[][] board) {
        this.board = board;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0)
                    posList.add(new Pos(j, i));
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                stringBuilder.append(board[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    private void fillBoard(int cur) {
        if (cur == posList.size()) {
            foundAnswer = true;
            return;
        }

        Pos pos = posList.get(cur);
        for (int i = 1; i < 10; i++) {
            board[pos.y][pos.x] = i;
            if (!hasDuplicateNumbers(pos)) {
                fillBoard(cur+1);
                if (foundAnswer)
                    return;
            }
        }
        board[pos.y][pos.x] = 0;
    }

    private boolean hasDuplicateNumbers(Pos pos) {
        return !checkRow(pos) || !checkCol(pos) || !checkSquare(pos);
    }

    private boolean checkSquare(Pos pos) {
        Arrays.fill(checker, false);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int n = board[(pos.y/3)*3+i][(pos.x/3)*3+j];

                if (n > 0) {
                    if (checker[n - 1])
                        return false;
                    else checker[n - 1] = true;
                }
            }
        }
        return true;
    }

    private boolean checkRow(Pos pos) {
        Arrays.fill(checker, false);
        for (int i = 0; i < SIZE; i++) {
            int n = board[pos.y][i];

            if (n > 0) {
                if (checker[n - 1])
                    return false;
                else checker[n - 1] = true;
            }
        }
        return true;
    }

    private boolean checkCol(Pos pos) {
        Arrays.fill(checker, false);
        for (int i = 0; i < SIZE; i++) {
            int n = board[i][pos.x];

            if (n > 0) {
                if (checker[n - 1])
                    return false;
                else checker[n - 1] = true;
            }
        }
        return true;
    }

    private static class Pos {
        public int x;
        public int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
