import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {

        HashMap<String, Integer> signatures = new HashMap<String, Integer>();

        for(int size = 1; size < s.length(); size++) {
            for(int beginIndex = 0; beginIndex <= s.length()-size; beginIndex++) {
                int[] sig = new int[26];

                for(char c : s.substring(beginIndex, beginIndex+size).toCharArray()) {
                    sig[c-97]++;
                }

                StringBuilder sb = new StringBuilder();
                for(int i=0; i<26; i++) {
                    sb.append(sig[i]);
                }
                String sigString = sb.toString();

                if(signatures.containsKey(sigString))
                    signatures.put(sigString, signatures.get(sigString) + 1);
                else
                    signatures.put(sigString, 1);

            }
        }

        int pairCount = 0;

        for(int freq : signatures.values()) {
            System.out.println(freq);
            pairCount += (freq * (freq - 1)) / 2; // n choose 2 formula
        }

        return pairCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
