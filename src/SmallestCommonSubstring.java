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

// need to push code to git and answer corresponding leetcode query online


import java.util.HashMap;
import java.util.Random;


public class SmallestCommonSubstring{

    public static void main(String[] args) {
        HashMap<Character, Integer> mapA = new HashMap<>();     // HashMap from character to # times the
        HashMap<Character, Integer> mapB = new HashMap<>();     // character appears in the String

        // generate random Strings from pool of lowercase English characters
        String alphas = "abcdefghijklmnopqrstuvwxyz";

        Random r = new Random();
        StringBuilder A = new StringBuilder(100);
        StringBuilder B = new StringBuilder(100);

        for(int i=0; i<100000; i++){
            A.append(alphas.charAt(r.nextInt(26)));
            B.append(alphas.charAt(r.nextInt(26)));
        }

//        StringBuilder A = new StringBuilder("abcd");
//        StringBuilder B = new StringBuilder("bc");

//        System.out.println(A);
//        System.out.println(B);

        // Add characters from A into HashMap
        for(int i=0; i<A.length(); i++){
            char curr = A.charAt(i);
            if(!mapA.containsKey(curr))
                mapA.put(curr, 1);                          // character appears once in the String (first encounter)
            else
                mapA.replace(curr, mapA.get(curr)+1);       // character encountered again, increment map value by 1
        }

        // Add characters from B into HashMap
        for(int i=0; i<B.length(); i++){
            char curr = B.charAt(i);
            if(!mapB.containsKey(curr))
                mapB.put(curr, 1);
            else
                mapB.replace(curr, mapB.get(curr)+1);
        }

//        System.out.println(mapA);
//        System.out.println(mapB);

        int count = 0;

        for(char z: mapA.keySet()){
            if(mapB.containsKey(z)){
                count += mapA.get(z) * mapB.get(z);         // total number of different pairs of ((i,j),(k,l)) will be
            }                                               // (# of positions z is present in A) * (# of positions z
        }                                                   // is present in B) Eg. if 'c' is present in A 3 times
                                                            // & 'c' is present in B 4 times then the total #
                                                            // ((i,j),(k,l)) pairs are 3*4=12
        System.out.println(count);

        
    }
}
