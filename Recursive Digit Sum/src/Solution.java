import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the superDigit function below.
    static long superDigit(String n, int k) {

        if(n.length() == 1)
            return Long.valueOf(n);

        long sum=0;
        for(int i=0; i<n.length(); i++) {
            sum += n.charAt(i) - 48;
        }

        sum *= k;
        String s = String.valueOf(sum);
        return superDigit(s, 1);
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        String n = nk[0];

        int k = Integer.parseInt(nk[1]);

        long result = superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
