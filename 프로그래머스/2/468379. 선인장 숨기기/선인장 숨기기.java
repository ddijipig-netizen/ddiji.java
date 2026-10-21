import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int INF = drops.length + 1; // 비가 안 내리는 칸을 의미하는 값
        
        // 1. 각 칸별 최초 착우 시점 기록
        int[][] rain = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(rain[i], INF);
        }
        for (int t = 0; t < drops.length; t++) {
            int r = drops[t][0];
            int c = drops[t][1];
            if (rain[r][c] == INF) {
                rain[r][c] = t;
            }
        }

        // 2. 가로 방향(w) 최소값 구하기 (m x (n - w + 1))
        int[][] rowMin = new int[m][n - w + 1];
        for (int r = 0; r < m; r++) {
            Deque<Integer> dq = new ArrayDeque<>();
            for (int c = 0; c < n; c++) {
                while (!dq.isEmpty() && rain[r][dq.peekLast()] >= rain[r][c]) {
                    dq.pollLast();
                }
                dq.offerLast(c);
                if (dq.peekFirst() <= c - w) {
                    dq.pollFirst();
                }
                if (c >= w - 1) {
                    rowMin[r][c - w + 1] = rain[r][dq.peekFirst()];
                }
            }
        }

        // 3. 세로 방향(h) 최소값 구하며 최적의 위치 찾기
        int maxTime = -1;
        int[] bestPos = new int[]{0, 0};

        for (int c = 0; c <= n - w; c++) {
            Deque<Integer> dq = new ArrayDeque<>();
            for (int r = 0; r < m; r++) {
                while (!dq.isEmpty() && rowMin[dq.peekLast()][c] >= rowMin[r][c]) {
                    dq.pollLast();
                }
                dq.offerLast(r);
                if (dq.peekFirst() <= r - h) {
                    dq.pollFirst();
                }
                if (r >= h - 1) {
                    int curTime = rowMin[dq.peekFirst()][c];
                    int startR = r - h + 1;

                    if (curTime > maxTime) {
                        maxTime = curTime;
                        bestPos[0] = startR;
                        bestPos[1] = c;
                    }
                }
            }
        }

        return bestPos;
    }
}