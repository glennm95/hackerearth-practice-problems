/*
Smallest subset

You are given an array, and you are required to select the smallest subset of the array such that the bitwise OR of the
array is maximum. Write a program that prints the smallest size of the subset.

Input format

    First line: T that denotes the number of test cases
    For each test case
        First line: An integer N that denotes the size of the array
        Second line: N space-separated integers that denote the elements of the array

Output format
Print a single integer corresponding to the minimum size of the subset which will result in the same OR as the
original array A. For each test case, print the answer in a separate line.

Constraints

1 <= T <= 3
1 <= N <= 10^5
1 <= A[i] <= 10^6 - A[i] represents the elements of the array

Sample input
1
5
1 2 3 4 5

Sample output
2

Explanation
Original OR of the array - or(1,2,3,4,5) = 7
However picking up only (3,4) or (5,2) will give you the same result
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MaxBitwiseOR {

    // Method to find number of 0 to 1 bit flips that will occur when n1 is ORed with n2
    // Here # of bits in n2 <= # of bits in n1
    public static int getNumberOf0To1BitFlips(int n1, int n2){
        // convert n1 and n2 to reversed bit form and store in char array
        // binary form of n1 and n2 are reversed for proper bitwise comparison
        String n1ReversedBinary = getReversedBinaryString(n1);
        String n2ReversedBinary = getReversedBinaryString(n2);

        int countFlips = 0;

        for(int i=0; i<n2ReversedBinary.length(); i++){
            if(n1ReversedBinary.charAt(i) == '0')
                if(n2ReversedBinary.charAt(i) == '1')
                    countFlips += 1;
        }

        return countFlips;
    }

    public static String getReversedBinaryString(int number){
        StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(number));
        // used StringBuilder to easily reverse binaryString

        binaryString.reverse();
        return binaryString.toString();
    }

    // get OR of entire ArrayList
    public static int getListOR(ArrayList<Integer> inArray){
        int listOR = 0;
        for(int x: inArray)
            listOR |= x;
        return listOR;
    }

    // move element from a1 to a2 i.e remove from a1 and add to a2
    public static void moveEle(ArrayList<Integer> a1, ArrayList<Integer> a2, Integer ele){
        a2.add(ele);
        a1.remove(ele);
    }

    public static int getSmallestSubsetSize(ArrayList<Integer> inArray, int arrSize){
        int totalOR = getListOR(inArray);

        ArrayList<Integer> resultSet = new ArrayList<>();

        moveEle(inArray, resultSet, Collections.max(inArray));

        if(totalOR == getListOR(resultSet))
            return 1;

        for(int i=0; i<arrSize-1; i++){
            int maxFlips = 0;
            int ele = -1;
            for(int j: inArray){
                int numFlips = getNumberOf0To1BitFlips(getListOR(resultSet),j);
                if(numFlips > maxFlips) {
                    maxFlips = numFlips;
                    ele = j;
                }
            }

            moveEle(inArray, resultSet, ele);

            if(getListOR(resultSet) == totalOR) {
                System.out.println(resultSet);
                return resultSet.size();
            }
        }

        /* if above for loop exhausts without returning on last if statement, then smallest subset size equals the
        size of input array i.e. no smaller subset exists therefore return original array size */
        return arrSize;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nTestCases = Integer.parseInt(br.readLine());

        while(nTestCases != 0){
            int arrSize = Integer.parseInt(br.readLine());
            ArrayList<Integer> inArray = new ArrayList<>();

            /*
            Random rand = new Random();

            for(int i=0; i<10; i++)
                inArray.add(rand.nextInt(1000000)+1);

            bw.write(Integer.toString(getSmallestSubsetSize(inArray, 100000)) + "\n");

             */

            String[] arr = br.readLine().split(" ");

            for(int i=0; i<arrSize; i++)
                inArray.add(Integer.parseInt(arr[i]));

            bw.write(getSmallestSubsetSize(inArray, arrSize) + "\n");

            // need to fill the array after this
            nTestCases -= 1;
        }

        br.close();
        bw.close();

    }
}
