class Solution {
    public int calculate(String s) {

        Stack<Long> stack = new Stack<>();

        long res = 0;
        long curr = 0;
        long sign = 1;

        for (char c : s.toCharArray()) {

            if (Character.isDigit(c)) {
                curr = curr * 10 + (c - '0');
            } 
            else if (c == '+') {
                res += curr * sign;
                sign = 1;
                curr = 0;
            } 
            else if (c == '-') {
                res += curr * sign;
                sign = -1;
                curr = 0;
            } 
            else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
                curr = 0;
            } 
            else if (c == ')') {
                res += curr * sign;
                curr = 0;
                res *= stack.pop();
                res += stack.pop();
            }
        }

        res += sign * curr;
        return (int) res;
    }
}