import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the commonChild function below.
    static int commonChild(char[] s1, char[] s2) {

        int[][] dp = new int[2][s2.length+1];

        for(int i=0; i<s1.length; i++) {
            for(int j=0; j<s2.length; j++) {
                if(s1[i] == s2[j]) {
                    dp[1][j+1] = dp[0][j] + 1;
                }
                else {
                    dp[1][j+1] = Math.max(dp[0][j+1], dp[1][j]);
                }
                dp[0][j] = dp[1][j];
            }
            dp[0][s2.length] = dp[1][s2.length];
        }

        return dp[0][s2.length];
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1.toCharArray(), s2.toCharArray());

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
