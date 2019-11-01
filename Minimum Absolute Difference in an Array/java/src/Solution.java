import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumAbsoluteDifference function below.
    static int minimumAbsoluteDifference(int[] arr) {
        int minDiff = Integer.MAX_VALUE;

//        simplest algorithm with O(N^2) complexity
//        for(int i=0; i<arr.length-1; i++) {
//            for(int j=i+1; j<arr.length; j++) {
//                currDiff = Math.abs(arr[i]-arr[j]);
//                if(currDiff < minDiff)
//                    minDiff = currDiff;
//
//                if(minDiff == 0)
//                    return 0;
//            }
//        }

        Arrays.sort(arr);
        for(int i=1; i< arr.length; i++) {
            minDiff = Math.min(minDiff, Math.abs(arr[i]-arr[i-1]));
        }

        return minDiff;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = minimumAbsoluteDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
