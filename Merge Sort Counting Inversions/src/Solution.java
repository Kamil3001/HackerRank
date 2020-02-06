import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {

        //pass in a single temporary array to keep space complexity from increasing with recursive calls
        return mergeSort(arr, new int[arr.length], 0, arr.length-1);
    }


    //sort array given [leftIndex, rightIndex] of array
    static long mergeSort(int[] arr, int[] tmp, int left, int right) {
        long count = 0;

        if(left < right) {
            int middle = (left + right) / 2;
            count += mergeSort(arr, tmp, left, middle);
            count += mergeSort(arr, tmp, middle+1, right);
            count += merge(arr, tmp, left, right);
        }

        return count;
    }

    static long merge(int[] arr, int[] tmp, int leftStart, int rightEnd) {
        int leftEnd = (rightEnd + leftStart) / 2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        long inversionCount = 0L;

        while(left <= leftEnd && right <= rightEnd) {
            if(arr[left] <= arr[right])
                tmp[index++] = arr[left++];
            else {
                tmp[index++] = arr[right++];
                inversionCount += (leftEnd - left + 1);
            }
        }

        System.arraycopy(arr, left, tmp, index, leftEnd - left + 1);
        System.arraycopy(arr, right, tmp, index, rightEnd - right + 1);
        System.arraycopy(tmp, leftStart, arr, leftStart, size);

        return inversionCount;
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
