class Solution {
    public String simplifyPath(String path) {
        path = path.replaceAll("/+","/");
        String data[] = path.split("/");
        int n = data.length;

        Stack<String> stack = new Stack<>();
        for(int i=0;i<n;i++){
            if(data[i].equals("..") && !stack.isEmpty()) stack.pop();
            else if(!data[i].equals("..") && !data[i].equals(".") && !data[i].matches(".*\\/{2,}.*")) stack.push(data[i]);
        }

        String results = "";
        while(!stack.isEmpty()){
            if(stack.peek().length() > 0) results = "/" + stack.pop() + results;
            else stack.pop();
        }

        if(results.length() == 0) return "/";
        return results;
    }
}