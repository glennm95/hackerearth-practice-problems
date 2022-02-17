import java.util.ArrayList;
import java.util.HashMap;


public class SmallestCommonSubstring {
    public static void nSmallestCommonSubstring(String A, String B, HashMap<String, Boolean> map){

        map.put(A, B.contains(A));

        if(A.length() == 1)
            return;

        StringBuffer subA;

        for(int i=0; i<A.length(); i++){
            subA = new StringBuffer(A).deleteCharAt(i);
            if(!map.containsKey(subA.toString()))
                nSmallestCommonSubstring(subA.toString(), B, map);
        }

    }

    public static void main(String[] args) {
        HashMap<String, Boolean> map = new HashMap<>();

        nSmallestCommonSubstring("abcd", "bc", map);

        int min_size = 999999;

        for(String x: map.keySet())
            if(map.get(x) && x.length() < min_size)
                min_size = x.length();

        int num = 0;

        ArrayList<String> subs = new ArrayList<>();

        for(String x: map.keySet())
            if(x.length() == min_size && map.get(x)) {
                subs.add(x);
                num += 1;
            }

//        System.out.println(map);
        System.out.println(subs);

        System.out.println(num);
    }
}
