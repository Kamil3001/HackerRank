import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    static class Point {
        char aChar;
        long freq;

        Point(char aChar, long freq) {
            this.aChar = aChar;
            this.freq = freq;
        }
    }

    // Complete the substrCount function below.
    static long substrCount(int n, char[] s) {

        //keep track of last 3 substrings
        Point curr = new Point(s[0], 1);
        Point prev1 = null;
        Point prev2 = null;
        long count = 0;

        // every time we find a new character we complete the previous
        // substring and increase count using n*(n+1)/2 as well as
        // accounting for the special case
        for(int i=1; i<n; i++) {
            if(s[i] == curr.aChar)
                curr.freq++;
            else {
                count += ((curr.freq * (curr.freq + 1)) / 2)
                        + specialCase(prev1, prev2, curr);

                prev2 = prev1;
                prev1 = curr;

                curr = new Point(s[i], 1);
            }
        }

        // Don't forget the last character!!!
        return count + ((curr.freq * (curr.freq + 1)) / 2)
                + specialCase(prev1, prev2, curr);
    }

    // the special case described in the problem
    static long specialCase(Point prev1, Point prev2, Point curr) {
        if(prev1 != null && prev2 != null && prev1.freq == 1 && prev2.aChar == curr.aChar) {
            return Math.min(prev2.freq, curr.freq);
        }

        return 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s.toCharArray());

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
