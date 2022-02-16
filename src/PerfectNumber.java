import java.io.*;
import java.util.HashMap;

public class PerfectNumber {
    public static boolean isPerfectNumber(int n){
        int sum = 0;
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"");  // 1 is divisor of every +ve integer

        for(int i=2; i<=n/2; i++) {         // loop starts from 2 since 1 is already added
            if (map.containsKey(i))
                break;
            if (n % i == 0) {
                map.put(i, "");             // pair-wise addition of divisors
                map.put(n / i, "");
            }
        }


        for(int x: map.keySet())
            sum += x;

        System.out.println(map.keySet());

        return sum == n;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n_testCases = Integer.parseInt(br.readLine());

        for(int i=0; i<n_testCases; i++){
            if(isPerfectNumber(Integer.parseInt(br.readLine())))
                bw.write("yes\n");
            else
                bw.write("no\n");
        }

        br.close();
        bw.close();
    }
}
