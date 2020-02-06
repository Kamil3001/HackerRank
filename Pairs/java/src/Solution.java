import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

//     two-pointer method approach (sorting adds O(n*logn) complexity)
//    static int pairs(int k, int[] arr) {
//        Arrays.sort(arr); //sorting first
//
//        // loop through sorted array using two-pointer method
//        int i=0, j=1, diff, count=0;
//        while(j < arr.length) {
//            diff = arr[j] - arr[i];
//
//            if(diff == k){ // if difference = k we increase count and move to next pair (since arr values are unique!)
//                count++;
//                i++;
//                j++;
//            }
//            else if(diff < k) // if difference is too small we increase j to increase it
//                j++;
//            else {
//                i++;
//            }
//        }
//        return count;
//    }

    // Set method approach yielding O(n) since no sorting required
    static int pairs(int k, Set<Integer> vals) {
        int count = 0;

        for(Integer val : vals) {
            if (vals.contains(val+k))
                count++;
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

//        int[] arr = new int[n]; //commented out for Set approach
        Set<Integer> values = new HashSet<>();

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
//            arr[i] = Integer.parseInt(arrItems[i]); //commented out for Set approach
            values.add(Integer.parseInt(arrItems[i]));
        }

//        int result = pairs(k, arr);
        int result = pairs(k, values);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
