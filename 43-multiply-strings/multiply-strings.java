class Solution {
    public String multiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();

        // If either number is "0", return "0"
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        // Result can be at most length n1+n2
        int[] product = new int[n1 + n2];
        
        // Multiply each digit from right to left
        for (int i = n1 - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = n2 - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                int sum = digit1 * digit2 + product[i + j + 1];
                
                product[i + j + 1] = sum % 10;             // remainder at current position
                product[i + j] += sum / 10;                // carry to next left position
            }
        }

        // Convert product array to string, skipping leading zeros
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (index < product.length && product[index] == 0) {
            index++;
        }
        while (index < product.length) {
            result.append(product[index++]);
        }
        
        return result.toString();
    }
}
