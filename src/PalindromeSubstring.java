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

public class PalindromeSubstring {

    public static int getCost(String s, String sPal){
        /*
        returns Cost i.e. no. of character replacement operations in s required to transform s to sPal
         */

        int cost = 0;

        for(int i=0; i<s.length(); i++)
            if(s.charAt(i) != sPal.charAt(i))
                cost += 1;

        return cost;
    }

    public static String buildPalindrome(String s, boolean part){
        /*
        Takes string s and boolean, part, to indicate which part of s is to be retained while constructing palindrome
        If part is true then left-half of s is retained and if false, then right-half is retained.
        Eg.:  s: london, part: true => output: lonnol
              s: paris, part: false => output: siris
         */

        String halfString;

        // retain left part of s
        if(part){
            halfString = s.substring(0, s.length()/2);
            if(isEvenString(s))
                return halfString.concat(new StringBuilder(halfString).reverse().toString());
            else
                return halfString.concat(new StringBuilder(s.substring(0, s.length()/2+1)).reverse().toString());
        }

        // retain right part s
        else {
            halfString = s.substring(s.length()/2);
            if (isEvenString(s)) {
                return new StringBuilder(halfString).reverse().toString().concat(halfString);
            } else {
                return new StringBuilder(halfString).reverse().toString().concat(halfString.substring(1));
            }
        }
    }

    public static boolean isEvenString(String s){
        return s.length()%2==0;
    }

    public static boolean containsEqualChars(String s){
        /*
        If all characters in String s are equal then return true
         */

        char c = s.charAt(0);
        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) != c)
                return false;
        }
        return true;
    }

    public static String getPalindromicSubstring(String s1, String s2){

        String palindromicSubstring, s1Pal, s2Rev = new StringBuilder(s2).reverse().toString();
        int i=0, cost, minCost=5001, minCostPos = -1;

        /*
        if substring length is greater than original string or
        if substring is not a palindrome and substring length is greater than half-length of original string then
        output not possible, handled separately for both even and odd cases
         */
        if (s1.length() < s2.length() || (!s2.equals(s2Rev) && s2.length()>s1.length()/2 && isEvenString(s1)) ||
                (!s2.equals(s2Rev) && s2.length()>s1.length()/2+1 && !isEvenString(s1)))
            return "";

        // if substring is a palindrome and s1 does not contain s2
        if(s2.equals(s2Rev) && !s1.contains(s2)){

            /*
            if s2 is a substring of just one repeated character eg.: bbb, therefore s2 is a palindrome and if
            s1 and s2 are either even or odd or vice-versa respectively then increment s2 by one more character and
            eventually place s2 in s1 (s2 retains its original substring form and is placed in s1 with minimum
            replacement operations)
             */
            if(containsEqualChars(s2) &&
                    ((isEvenString(s1) && !isEvenString(s2)) || (!isEvenString(s1) && isEvenString(s2))))
                s2 = s2.concat(s2.substring(0,1));

            /*
            if s1 and s2 are both even or both odd lengths then place s2 in middle of s1
             */
            int start = s1.length()/2 - s2.length()/2;
            int end = start + s2.length();
            if((isEvenString(s1) && isEvenString(s2)) || (!isEvenString(s1) && !isEvenString(s2))) {
                palindromicSubstring =
                        buildPalindrome(new StringBuilder(s1).replace(start, end, s2).toString(), true);
                return palindromicSubstring;
            }
        }

        /*
        Try placing s2 at each position in s1 and calculate the cost of such a placement. However, substring should not
        be placed such that it crosses the middle of s1 (If so, then part of substring s2 will be lost).
        Finally, return the placement with minimum cost (replacement operations).
         */
        while(i <= s1.length()-s2.length()){

            // place s2 at each position on left half of s1
            if((isEvenString(s1) && i+s2.length()-1 < s1.length()/2) ||
                    (!isEvenString(s1) && i+s2.length()-1 <= s1.length()/2))
                s1Pal = buildPalindrome(new StringBuilder(s1).replace(i, i+s2.length(), s2).toString(), true);

            // place s2 at each position on right half of s1
            else
                s1Pal = buildPalindrome(new StringBuilder(s1).replace(i, i + s2.length(), s2).toString(), false);

            cost = getCost(s1, s1Pal);
            if (cost < minCost){
                minCost = cost;
                minCostPos = i;
            }

            i += 1;
            if(i+s2.length()-1 == s1.length()/2)
                i = s1.length()/2;
        }

//        separate while loops for left and right halves of s1
//        while((i+s2.length()-1 < s1.length()/2 && isEvenString( s1)) ||
//                (i+s2.length()-1 <= s1.length()/2 && !isEvenString(s1))){
//            s1Pal = buildPalindrome(new StringBuilder(s1).replace(i, i+s2.length(), s2).toString(), true);
//            cost = getCost(s1, s1Pal);
//            if (cost < minCost){
//                minCost = cost;
//                minCostPos = i;
//            }
//
//            i += 1;
//        }
//
//
//        // do another while loop for the right half of s1 too.
//        i = s1.length()/2;
//        while(i+s2.length()-1 < s1.length()){
//            s1Pal = buildPalindrome(new StringBuilder(s1).replace(i, i+s2.length(), s2).toString(), false);
//            cost = getCost(s1, s1Pal);
//            if (cost < minCost){
//                minCost = cost;
//                minCostPos = i;
//            }
//
//            i += 1;
//        }

        if(minCostPos < s1.length()/2)
            palindromicSubstring = buildPalindrome(new StringBuilder(s1).replace(minCostPos, minCostPos + s2.length(), s2).toString(), true);
        else
            palindromicSubstring = buildPalindrome(new StringBuilder(s1).replace(minCostPos, minCostPos + s2.length(), s2).toString(), false);

        return palindromicSubstring;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nCases = Integer.parseInt(br.readLine());

        while(nCases !=0){
            String s1 = br.readLine();
            String s2 = br.readLine();

            String out = getPalindromicSubstring(s1, s2);

            if(out.equals(""))
                bw.write("-1\n");

            else
                bw.write(getCost(out, s1) + "\n");

//            System.out.println("output string = " + out);
//            System.out.println("original size = "+s1.length());
//            System.out.println("output size = "+out.length());
//            System.out.println("substring size = "+s2.length());
//            System.out.println("replacements = " + getCost(out, s1));

            nCases -= 1;
        }
        bw.close();

    }
}