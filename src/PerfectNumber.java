import java.util.ArrayList;
import java.util.Scanner;

public class PerfectNumber {
    public static boolean isPerfectNumber(int n){
        int sum = 0;
        ArrayList<Integer> divisors = new ArrayList<>();

        for(int i=1; i<=n-1; i++)
            if(n%i == 0)
                divisors.add(i);


        for(int x: divisors)
            sum += x;

        System.out.println(divisors);

        return sum == n;

    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n_testCases = in.nextInt();

        for(int i=0; i<n_testCases; i++){
            if(isPerfectNumber(in.nextInt()))
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }
}
