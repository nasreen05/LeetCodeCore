class Solution {
    private int validUnsignIntLen(String s){
        for(char c: s.toCharArray())
            if(!(c >= '0' && c <= '9')) return -1;
        return s.length();
    }
    private int validSignIntLen(String s){
        if(s.length() == 0) return 0;
        if(s.charAt(0) == '-' || s.charAt(0) == '+')
            return validUnsignIntLen(s.substring(1));
        return validUnsignIntLen(s);
    }
    private int validSignDeciLen(String s){
        if(s.length() == 0) return 0;
        String parts[] = s.split("\\.", -1);
        if(parts.length > 2 || parts.length == 0) return -1;
        if(parts.length == 2) {
            int len1 = validSignIntLen(parts[0]);
            int len2 = validUnsignIntLen(parts[1]);
            if(len1 == -1 || len2 == -1) return -1;
            return len1 + len2;
        }
        return validSignIntLen(parts[0]);
    }
    public boolean isNumber(String s) {
        s = s.toLowerCase();
        String parts[] = s.split("e", -1);
        if(parts.length > 2 || parts.length == 0) return false;
        if(parts.length == 2) 
            return (validSignDeciLen(parts[0]) >= 1) 
                && (validSignIntLen(parts[1]) >= 1);
        return (validSignDeciLen(parts[0]) >= 1);
    }
}