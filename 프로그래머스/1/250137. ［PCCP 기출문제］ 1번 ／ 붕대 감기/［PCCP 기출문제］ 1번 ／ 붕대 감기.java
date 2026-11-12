class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0]; // 시전시간
        int x = bandage[1]; // 초당 회복량
        int y = bandage[2]; // 추가 회복량
        
        int cur = health;   // 현재 체력
        int streak = 0;     // 연속 성공 시간
        int idx = 0;        // 다음에 처리할 공격 번호
        int lastTime = attacks[attacks.length - 1][0]; // 마지막 공격 시각
            
        for (int time = 1; time <= lastTime; time++) {
            if (attacks[idx][0] == time) {
                // 공격받는 초: 피해를 입고 연속 성공 초기화 (회복 없음)
                cur -= attacks[idx][1];
                streak = 0;
                idx++;
                
                if(cur <= 0) {
                    return -1; // 사망
                }
            } else {
                // 공격이 없는 초: 회복
                cur = Math.min(health, cur + x);
                streak++;
                
                // t초 연속 성공하면 추가 회복
                if (streak == t) {
                    cur = Math.min(health, cur + y);
                    streak = 0;
                }
            }
        }
        
        return cur;
    }
}