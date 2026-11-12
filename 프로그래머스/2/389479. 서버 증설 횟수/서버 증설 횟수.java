class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] add = new int[24]; // add[i]: i시에 증설한 서버 수
        int running = 0;       //  현재 운영 중인 증설 서버 수
        
        for (int i = 0; i < 24; i++) {
            // k시간 전에 증설한 서버는 반납
            if (i - k >= 0) {
                running -= add[i - k];
            }
        
            int need = players[i] / m; // 필요한 서버 수
            if (need > running) {      // 부족한면 부족한 만큼만 증설
                int diff = need - running;
                add[i] = diff;
                running += diff;
                answer += diff;
            }
        }
        return answer;
    }
}