class MovieRentingSystem {
    Map<Integer, Set<int[]>> unrented;
    Set<int[]> rented;
    Map<Integer,Map<Integer,Integer>> prices;

    public MovieRentingSystem(int n, int[][] entries) {
        unrented= new HashMap<>();
        rented= new TreeSet<>((a,b)->{
            if (a[2] != b[2]) return a[2] - b[2];
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];  
        });
        prices = new HashMap<>();
        for(int[] entry: entries){
            unrented.putIfAbsent(entry[1], new TreeSet<>((a,b)->{
                if(a[2]==b[2]) return a[0] - b[0];
                return a[2]-b[2];
            }));
            unrented.get(entry[1]).add(entry);
            prices.putIfAbsent(entry[0], new HashMap<>());
            prices.get(entry[0]).put(entry[1],entry[2]);
        }
    }
    
    public List<Integer> search(int movie) {
        List<Integer> list= new ArrayList<>();
        if(unrented.get(movie)==null) return list;
        for(int[] entry : unrented.get(movie)){
            list.add(entry[0]);
            if(list.size()==5) break;
        }
        return list;
    }
    
    public void rent(int shop, int movie) {
        int price=prices.get(shop).get(movie);
        Set<int[]> movies= unrented.get(movie);
        int[] entry=new int[]{shop,movie,price};
        movies.remove(entry);
        rented.add(entry);
    }
    
    public void drop(int shop, int movie) {
        int price=prices.get(shop).get(movie);
        int[] entry=new int[]{shop,movie,price};
        rented.remove(entry);
        unrented.get(movie).add(entry);
    }
    
    public List<List<Integer>> report() {
        List<List<Integer>> res= new ArrayList<>();
        for(int[] entry : rented){
            List<Integer> list= new ArrayList<>();
            list.add(entry[0]);
            list.add(entry[1]);
            res.add(list);
            if(res.size()==5) break;
        }
        return res;
    }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */