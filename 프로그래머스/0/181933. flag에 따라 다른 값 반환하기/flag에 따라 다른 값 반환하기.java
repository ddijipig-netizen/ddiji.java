class Solution {
    public int solution(int a, int b, boolean flag) {
        // flag가 true면 (a + b), false면 (a - b)를 반환
        return flag ? a + b : a - b;
    }
}