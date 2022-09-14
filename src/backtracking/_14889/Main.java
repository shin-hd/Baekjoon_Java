package backtracking._14889;

/* 스타트와 링크
오늘은 스타트링크에 다니는 사람들이 모여서 축구를 해보려고 한다.
축구는 평일 오후에 하고 의무 참석도 아니다.
축구를 하기 위해 모인 사람은 총 N명이고 신기하게도 N은 짝수이다.
이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.

BOJ를 운영하는 회사 답게 사람에게 번호를 1부터 N까지로 배정했고, 아래와 같은 능력치를 조사했다.
능력치 Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다.
팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합이다. Sij는 Sji와 다를 수도 있으며,
i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.

 */

/* 풀이
각 사람별로 A팀에 속하거나 안속하거나로 분류
분류 결과 A팀 B팀 반반이라면 두 능력치 차이 계산
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int numOfPeople = Integer.parseInt(bufferedReader.readLine());
            int[][] ability = new int[numOfPeople][numOfPeople];
            for (int i = 0; i < numOfPeople; i++)
                ability[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            TeamSplitter splitter = new TeamSplitter(numOfPeople, ability);
            splitter.split();
            System.out.println(splitter.getMinDifferenceInAbility());

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static class TeamSplitter {
        private final int numOfPeople;
        private final int[][] ability;
        private final boolean[] team;
        private int minDifferenceInAbility = Integer.MAX_VALUE;

        TeamSplitter(int numOfPeople, int[][] ability) {
            this.numOfPeople = numOfPeople;
            team = new boolean[numOfPeople];
            Arrays.fill(team, false);
            this.ability = ability;
        }

        public int getMinDifferenceInAbility() {
            return minDifferenceInAbility;
        }

        public void split() {
            split(0, 0);
        }

        private void split(int cur, int members) {
            if (members == numOfPeople / 2) {
                calcDifference();
                return;
            }
            if (cur == numOfPeople) return;


            split(cur+1, members);
            team[cur] = true;
            split(cur+1, members+1);
            team[cur] = false;
        }

        private void calcDifference() {
            int team1 = 0;
            int team2 = 0;

            for (int i = 0; i < numOfPeople; i++) {
                for (int j = 0; j < numOfPeople; j++) {
                    if (team[i] && team[j]) team1 += ability[i][j];
                    if (!team[i] && !team[j]) team2 += ability[i][j];
                }
            }

            minDifferenceInAbility = Math.min(minDifferenceInAbility, Math.abs(team1-team2));
        }
    }
}
