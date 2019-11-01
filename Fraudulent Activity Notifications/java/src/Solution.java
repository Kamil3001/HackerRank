import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        //array for storing trailing days frequencies
        int[] trailingFreq = new int[201];
        for(int i=0; i<d; i++) {
            trailingFreq[expenditure[i]]++;
        }
        int notices = 0;

        //getMedian for first subarray
        int median = getMedian(trailingFreq, d);
        if(d%2 == 0) {
            if(expenditure[d] >= median)
                notices++;
        }
        else if(expenditure[d] >= 2*median) {
            notices++;
        }

        //getMedian for every other subarray
        int oldestIndex = expenditure[0];
        for(int i=d+1; i<expenditure.length; i++) {
            //remove oldest & update index
            trailingFreq[oldestIndex]--;
            oldestIndex = expenditure[i-d];

            //add newest
            trailingFreq[expenditure[i-1]]++;

            //getMedian for subarray
            median = getMedian(trailingFreq, d);
            if(d%2 == 0) {
                if(expenditure[i] >= median)
                    notices++;
            }
            else if(expenditure[i] >= 2*median) {
                notices++;
            }
        }

        return notices;
    }

    static int getMedian(int freq[], int d) {
        int[] prefixSum = new int[201];
        prefixSum[0] = freq[0];
        for(int i=1; i<201; i++) {
            prefixSum[i] = prefixSum[i-1] + freq[i];
        }
        int a=0, b=0;

        int med1 = d/2 + 1; //since count is non-zero indexed if d=5 then middle is at freq 3
        for(int i=0; i<201; i++) {
            if(med1 <= prefixSum[i]){
                a = i;
                break;
            }
        }

        if(d % 2 == 0) { //if even, get the second part of the median, d=6 => look at 3 and 4
            med1 -= 1;
            for(int i=0; i<201; i++) {
                if(med1 <= prefixSum[i]) {
                    b = i;
                    break;
                }
            }
        }

        return a+b;
    }
}