class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        // num 상자의 층과 열 구하기 (0-based index)
        int targetIdx = num - 1;
        int targetRow = targetIdx / w;
        int targetCol = (targetRow % 2 == 0) ? (targetIdx % w) : (w - 1 - (targetIdx % w));
        
        // num 상자가 속한 층부터 위쪽 층들을 탐색
        int maxRow = (n - 1) / w; // 가장 높은 층
        
        for (int r = targetRow; r <= maxRow; r++) {
            // r층의 targetCol 위치에 있는 상자의 번호 계산
            int boxIdx;
            if (r % 2 == 0) {
                // 짝수 층 (왼쪽 -> 오른쪽)
                boxIdx = r * w + targetCol;
            } else {
                // 홀수 층 (오른쪽 -> 왼쪽)
                boxIdx = r * w + (w - 1 - targetCol);
            }
            
            // 해당 위치에 실제로 상자가 존재하면 (n개 이하이면)
            if (boxIdx < n) {
                answer++;
            }
        }
        
        return answer;
    }
}