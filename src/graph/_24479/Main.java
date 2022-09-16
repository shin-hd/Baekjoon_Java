package graph._24479;

/* DFS 1
오늘도 서준이는 깊이 우선 탐색(DFS) 수업 조교를 하고 있다.
아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.

N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다.
정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다.
정점 R에서 시작하여 깊이 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.

깊이 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 오름차순으로 방문한다.

dfs(V, E, R) {  # V : 정점 집합, E : 간선 집합, R : 시작 정점
    visited[R] <- YES;  # 시작 정점 R을 방문 했다고 표시한다.
    for each x ∈ E(R)  # E(R) : 정점 R의 인접 정점 집합.(정점 번호를 오름차순으로 방문한다)
        if (visited[x] = NO) then dfs(V, E, x);
}
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Traveler traveler = new Traveler();
        traveler.travel();
        System.out.println(traveler);
    }

    private static class Traveler {
        private final int r;
        private final List<Integer>[] edges;
        private final int[] visits;
        private int order = 1;

        Traveler() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                int[] inputs = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int v = inputs[0];
                int e = inputs[1];
                r = inputs[2];

                edges = new List[v];
                for (int i = 0; i < v; i++) {
                    edges[i] = new ArrayList<>();
                }
                for (int i = 0; i < e; i++) {
                    int[] edge = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    edges[edge[0]-1].add(edge[1]);
                    edges[edge[1]-1].add(edge[0]);
                }
                for (List<Integer> edge : edges)
                    Collections.sort(edge);


                visits = new int[v];
                Arrays.fill(visits, 0);

                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        @Override
        public String toString() {
            if (visits == null) return "";

            StringBuilder stringBuilder = new StringBuilder();
            for (int visit : visits) {
                stringBuilder.append(visit);
                stringBuilder.append('\n');
            }

            return stringBuilder.toString();
        }

        private void travel() {
            visits[r-1] = order++;

            for (int to : edges[r-1]) {
                if (visits[to-1] == 0) {
                    travel(to);
                }
            }
        }

        private void travel(int cur) {
            visits[cur-1] = order++;

            for (int to : edges[cur-1]) {
                if (visits[to-1] == 0) {
                    travel(to);
                }
            }
        }
    }
}
