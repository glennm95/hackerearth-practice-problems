/*
Smallest Common Substrings

Question

You are given two strings A and B that are made of lowercase English alphabets. Find the number of different pairs
((i, j), (k,l)) such that the substrings A[i...j] andB[k...l] are equal and the value of j - i + 1 is minimum.

Input Format

The first line consists of a string denoting A.
The second line consists f a string denoting B.

Output Format

Print the number of different pairs ((i, j), (k,l)) such that the substrings A[i...j] and B[k...l] are equal and the value of j-i+1 is minimum.

Constraints

1<=|A|, |B|<=10^5

Sample Input
abdc
bd

Sample Output
2

Explanation
Pairs are ((1, 1), (0, 0)) and ((2, 2), (1, 1))

 */


import java.util.HashMap;
import java.util.Random;


public class SmallestCommonSubstring{

    public static void main(String[] args) {
        HashMap<String, Boolean> map = new HashMap<>();

        // generate random Strings from pool of lowercase English characters
        String alphas = "abcdefghijklmnopqrstuvwxyz";

        Random r = new Random();
        StringBuilder A = new StringBuilder(100);
        StringBuilder B = new StringBuilder(100);

        for(int i=0; i<100000; i++){
            A.append(alphas.charAt(r.nextInt(26)));
            B.append(alphas.charAt(r.nextInt(26)));
        }


        // store each character in String B in HashMap; Boolean initialized to false
        for(String x: B.toString().split("")){
            if(!map.containsKey(x))
                map.put(x, false);
        }

        int count = 0;      // to keep track of total distinct characters

        // loop through characters of String A and check if character is present in HashMap => character is in B
        for(String x: A.toString().split("")){
            if(map.containsKey(x) && !map.get(x)) {     // second condition ensures that String is checked for
                                                        // membership in HashMap only once
                                                        // !map.get(x) => character is not visited before
                count += 1;
                map.replace(x, true);                   // mark character as visited
            }
        }

        System.out.println(count);      // output the total number of distinct characters..
                                        // max count value will be 26 for 26 alphabets
    }
}
