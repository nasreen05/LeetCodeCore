class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>>result =new ArrayList<>();
        List<Integer> ds =new ArrayList<>();
        function(n,k,result,ds,1);
        return result;

    }

    public static void function(int n,int k,List<List<Integer>>result,List<Integer>ds,int idx){
        if(ds.size()==k){
            result.add(new ArrayList<>(ds));
            return;
        }

        for(int i=idx;i<=n;i++){
            ds.add(i);
            function(n,k,result,ds,i+1);
            ds.remove(ds.size()-1);
        }
        
}
}