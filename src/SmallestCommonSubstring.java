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

import java.util.Random;

public class SmallestCommonSubstring{

    public static void main(String[] args) {
        int[] charA = new int[26];              // use arrays of size 26 to store count of each character in A and
        int[] charB = new int[26];              // similarly, for B

        // generate random Strings from pool of lowercase English characters
        String alphas = "abcdefghijklmnopqrstuvwxyz";

        Random r = new Random();
        StringBuilder A = new StringBuilder();
        StringBuilder B = new StringBuilder();

        for(int i=0; i<100000; i++){
            A.append(alphas.charAt(r.nextInt(26)));
            B.append(alphas.charAt(r.nextInt(26)));
        }

//        StringBuilder A = new StringBuilder("abcd");
//        StringBuilder B = new StringBuilder("bc");

//        System.out.println(A);
//        System.out.println(B);

        // Add character count of chars in A to charA
        for(int i=0; i<A.length(); i++){
            char curr = A.charAt(i);
            charA[curr - 'a'] += 1;
        }

        // Add character count of chars in B to charB
        for(int i=0; i<B.length(); i++){
            char curr = B.charAt(i);
            charB[curr - 'a'] += 1;
        }

        int count = 0;

        for(int i=0; i<26; i++)
            count += charA[i]*charB[i];         // for uncommon characters, ith position will be 0 either in
                                                // charA or charB hence will not contribute to count
        System.out.println(count);
    }
}
