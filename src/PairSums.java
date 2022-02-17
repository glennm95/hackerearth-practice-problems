/*
Problem
You have been given an integer array A and a number K. Now, you need to find out whether any two different elements of the array A sum to the number K. Two elements are considered to be different if they lie at different positions in the array. If there exists such a pair of numbers, print "YES" (without quotes), else print "NO" without quotes.

Input Format:

The first line consists of two integers N, denoting the size of array A and K. The next line consists of N space separated integers denoting the elements of the array A.

Output Format:

Print the required answer on a single line.

Constraints:
1<=N<=10^6
1<=K<=2*10^6
1<=A[i]<=10^6

Sample Input
5 9
1 2 3 4 5

Sample Output
YES

Explanation
Here, A[4] + A[5] = 4 + 5 = 9. So, the answer is YES.

 */

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class PairSums {
    public static boolean isPairSum(int[] inArray, int targetSum){
        HashMap<Integer,String> map = new HashMap<>();

        for(int x: inArray){                // O(n)
            if(map.containsKey(x))          // O(1), HashMap constant lookup
                return true;                // if element is found in HashMap => complement of element is present in
                                            // input array => pair of nos. summing to target is found in input
            map.put(targetSum - x,"");      // store complement of number in HashMap
        }

        return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = br.readLine().split(" ");
        int arraySize = Integer.parseInt(firstLine[0]);
        int targetSum = Integer.parseInt(firstLine[1]);

        int[] inArray = new int[arraySize];

        String[] secondLine = br.readLine().split(" ");

        for(int i=0; i<arraySize; i++)
            inArray[i] = Integer.parseInt(secondLine[i]);

        if(isPairSum(inArray, targetSum))
            bw.write("YES\n");
        else
            bw.write("NO\n");

        bw.close();
    }
}
