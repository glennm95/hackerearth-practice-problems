/*
You are given two string S1 and S2. You are required to convert the S1 string into a palindromic string
such that it contains the S2 string as its substring by using the minimum number of operations. You are
allowed to use only one type of operation, hence, you can replace any character of the S1 string with
any other character.

Input format:

    - First line: TEST denoting the number of test cases
    - For each test case:
            - First line: String S1
            - Second line: String S2

Note:
    - Both the strings can contain lower and upper case letters only
    - The upper case letter and its corresponding lower case letter are not the same. For example,
    A and a are considered as distinct characters.

Output format
In a single line, print the minimum number of operations that are required, if it is not possible to
get the desired output, then print -1.

Constraints
1<= TEST<=5
1<=Length(S1,S2)<=5000

Sample input
1
archit
ar
aaaaa
bbb

Sample output
3
3

Explanation:
In the first sample, we can get a string "accra" or "arhhra" as our final string which is both
palindromic as well as contains "ar" as substring. We can see that our final string differs
from the original string i.e. "archit" in exactly 3 places. So, minimum number of operations
required is 3.
 */

import java.io.*;
import java.util.ArrayList;

public class PalindromeSubstring {

    public static String buildPalindrome(String s, boolean palType){
        /*
        builds even length palindrome of String s if palType is true and odd length palindrome (middle character not
        repeated) if palType is false.
         */

        if(palType)
            return s.concat(new StringBuilder(s).reverse().toString());
        else
            return s.substring(0, s.length()-1).concat(new StringBuilder(s).reverse().toString());
    }

    public static boolean isEvenString(String s){
        return s.length()%2==0;
    }


    public static int getAppropriatePositionForS2InS1(String s1, String s2){
        int j = 0, palCharCount, minPalCharCount = 5001, posForS2 = 0;      // max length of s1 and s2 inputs is 5000

        ArrayList<Integer> palCharIndexes = new ArrayList<>();
        /*
        palCharIndexes is used to store indexes of s1 characters (left-half) that have a matching partner
        (palindrome character) in the right half of s1. While inserting s2 in s1, we want to place s2 in s1
        in such a way that s2 doesn't replace such palindrome characters which will thereby reduce the number of
        replacement operations to get to the output from the original input String s1.
         */

        for(int i=0; i<s1.length()/2; i++)
            if(s1.charAt(i) == s1.charAt(s1.length()-1-i))
                palCharIndexes.add(i);

        while((isEvenString(s1) && j+s2.length()-1 < s1.length()/2) ||
                (!isEvenString(s1) && j+s2.length()-1 <= s1.length()/2)){   //while loop runs until middle of s1
            palCharCount = 0;

            /*
            for every position j of s2 in left-half of s1 (until right edge of s2 is at mid of s1) loop through
            palCharIndexes and find the number of palindrome characters between j and j+s2.length()-1 both inclusive
            and finally output the position containing the least palindrome characters that will be affected by
            replacement. If we find a position for s2 that doesn't affect any palindrome chars then break out of the
            loop and return that position.
             */
            for(int i: palCharIndexes)
                if(i>=j && i<j+s2.length())
                    palCharCount += 1;

            if(palCharCount < minPalCharCount) {
                minPalCharCount = palCharCount;
                posForS2 = j;
            }
            if(minPalCharCount == 0)
                break;
            j += 1;
        }
        return posForS2;
    }

    public static String getS1PalindromeContainingS2(String s1, String s2) {

        String s2Reverse = new StringBuilder(s2).reverse().toString();
        int s1Length = s1.length(), s2Length = s2.length(), s1HalfLength = s1Length/2, s2HalfLength = s2Length/2,
                start, end;
        String halfPalindrome = "";
        String outputPalindrome = "";

        if(s1Length < s2Length)
            return "";

        if(!s1.contains(s2)){

            // if s2 is palindromic and s1 and s2 are both even or both odd, then place s2 in center of s1
            if(s2.equals(s2Reverse)){
                start = s1HalfLength - s2HalfLength;
                end = start + s2Length;

                // s1 and s2 are both even length strings
                if(isEvenString(s1) && isEvenString(s2)){
                    halfPalindrome = new StringBuilder(s1).replace(start, end, s2).substring(0, s1HalfLength);
                    outputPalindrome = buildPalindrome(halfPalindrome, true);
                }

                // s1 and s2 are both odd length strings
                else if(!isEvenString(s1) && !isEvenString(s2)){      // s1 and s2 are odd length strings
                    halfPalindrome = new StringBuilder(s1).replace(start, end, s2).substring(0, s1HalfLength + 1);
                    outputPalindrome = buildPalindrome(halfPalindrome, false);
                }


                else if(isEvenString(s1) && !isEvenString(s2)){
                    if(s2Length <= s1HalfLength){
                        start = getAppropriatePositionForS2InS1(s1, s2);
                        halfPalindrome = new StringBuilder(s1).replace(start, end, s2).substring(0, s1HalfLength);
                    }
                }
                else{   // s1 is odd and s2 is even
                    if(s2Length <= s1HalfLength+1){
                        start = getAppropriatePositionForS2InS1(s1, s2);
                        halfPalindrome = new StringBuilder(s1).replace(start, end, s2).substring(0, s1HalfLength + 1);
                    }

                }

            }

            else if((isEvenString(s1) && s1HalfLength >= s2Length) || (!isEvenString(s1) && s1HalfLength + 1 >= s2Length)){
                start = getAppropriatePositionForS2InS1(s1, s2);
                end = start + s2Length;
                halfPalindrome = new StringBuilder(s1).replace(start, end, s2).substring(0, s1HalfLength);

            }


        }

        else{
            // s1 contains s2, if s1 contains s2 then that automatically means that s2.length < s1.length/2

            // if s2 is completely in right half of s1
            // it must be remembered that indexOf returns the first occurrence of s2 in s1
            // therefore below we look from the rightmost side of s1, to make sure that we get the rightmost
            // occurrence of s2 if present
            if(s1.lastIndexOf(s2) >= s1HalfLength)
                halfPalindrome = new StringBuilder(s1.substring(s1HalfLength, s1Length)).reverse().toString();
            //above works for both even and odd lengths of s1

            // if s2 is completely in left half of s1 based on whether s1 is even or odd
            else if(isEvenString(s1) && s1.indexOf(s2) + s2Length - 1 < s1HalfLength){
                    halfPalindrome = s1.substring(0, s1HalfLength);
            }
            else if(!isEvenString(s1) && s1.indexOf(s2) + s2Length - 1 <= s1HalfLength){
                    halfPalindrome = s1.substring(0, s1HalfLength+1);
            }
            // if all the three above cases are not satisfied, it means that s2 is not present in right half of s1
            // and s2 is not present in the left half of s1 such that first index of s2 + length of s2 < mid of s1
            // so the final possibility is that s2 is in s1 but crosses mid of s1
            // in such case, first check if s2 < s1/2 - will be different for even and odd s1
            // if true then select the best hole to accommodate s2 and place s2 there
            else{
                int posForS2 = getAppropriatePositionForS2InS1(s1, s2);
                if(isEvenString(s1) && s2Length <= s1HalfLength){
                    halfPalindrome = new StringBuilder(s1).replace(posForS2, posForS2+s2Length, s2).substring(0, s1HalfLength);
                }
                else if(!isEvenString(s1) && s2Length <= s1HalfLength+1){
                    halfPalindrome = new StringBuilder(s1).replace(posForS2, posForS2+s2Length, s2).substring(0, s1HalfLength+1);
                }
            }


        }


        if(!halfPalindrome.equals("")) {
            if (isEvenString(s1))
                outputPalindrome = buildPalindrome(halfPalindrome, true);
            else if (!isEvenString(s1))
                outputPalindrome = buildPalindrome(halfPalindrome, false);
        }

        return outputPalindrome;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nCases = Integer.parseInt(br.readLine());

        while(nCases !=0){
            String s1 = br.readLine();
            String s2 = br.readLine();

            String out = getS1PalindromeContainingS2(s1,s2);

            int nOperations = 0;

//            System.out.println(out);
//            System.out.println(s1.length());
//            System.out.println(s2.length());
//            System.out.println(out.length());

            if(!out.equals("")) {
                for (int i = 0; i < s1.length(); i++)
                    if (out.charAt(i) != s1.charAt(i))
                        nOperations += 1;
                bw.write(nOperations + "\n");
            }
            else
                bw.write("-1\n");

            bw.close();

            nCases -= 1;
        }

    }
}
