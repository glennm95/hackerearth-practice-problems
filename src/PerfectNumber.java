/*
Definition of Perfect number:
A perfect number is a positive integer that is equal to the sum of its proper positive divisors excluding the number itself. By this definition, 1 is not a perfect number.

Input Format
The first line contains a single integer T denoting the number of testcases. Next T lines contains a single integer each.

Output Format
For each test case print the yes or no on a single line.

Constraint
1 ≤ T ≤ 105
0 ≤ x ≤ 1,000,000,000

Note Please consider 0 as perfect number and print Yes.

Sample Input
2
6
2

Sample Output
yes
no

Explanation
Take number 6. Proper positive divisors of 6 is 1,2,3 and their sum is 1+2+3=6. So, 6 is a perfect number. Similarly, 2 is not a perfect number.
 */


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
