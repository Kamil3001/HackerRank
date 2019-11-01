import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        return mergeSortAndCount(arr, 0, arr.length-1);
    }

    private static long mergeSortAndCount(int[] arr, int l, int r) {
        long count = 0;

        if(l < r) {
            int mid = (l+r)/2;

            //left subarray
            count += mergeSortAndCount(arr, l, mid);

            //right subarray
            count += mergeSortAndCount(arr, mid+1, r);

            //count inversions during merge of both subarrays
            count += mergeAndCount(arr, l, mid, r);
        }

        return count;
    }

    private static long mergeAndCount(int[] arr, int l, int mid, int r) {

        int[] left = Arrays.copyOfRange(arr, l, mid+1);
        int[] right = Arrays.copyOfRange(arr, mid+1, r+1);
        int i = 0, j = 0, k = l;
        long swaps = 0;

        while (i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                arr[k++] = left[i++];
            }
            else {
                arr[k++] = right[j++];
                swaps += (mid + 1) - (l + i); //no. of swaps = no. of remaining elements in left subArray to compare
            }
        }

        //insert remaining elements after sorting
        while(i < left.length) {
            arr[k++] = left[i++];
        }

        while(j < right.length){
            arr[k++] = right[j++];
        }

        return swaps;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
