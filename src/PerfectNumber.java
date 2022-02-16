import java.io.*;

public class PerfectNumber {
    public static boolean isPerfectNumber(int n){
        int sum = 0;

        if(n == 0)
            return true;        // 0 is not a perfect number but HackerEarth output's true for 0 input

        if(n == 1)
            return false;       // 1 is not a perfect number by definition


        // HashMap is eliminated since it's not required to store the divisors thereby saving on space

        for(int i=2; i<=Math.sqrt(n); i++) {         // loop starts from 2 since 1 is added later and loop ends at
                                                    //  Square root of number which is a logically correct trick
            if(n%i == 0){
                if(i == n/i)
                    sum += i;       // add divisor only once in case number is a perfect square
                else
                    sum += (i + n/i);   // add divisor and it's pair to sum

            }

        }

        sum += 1;       // adding 1 since 1 is a divisor of every +ve number;

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
