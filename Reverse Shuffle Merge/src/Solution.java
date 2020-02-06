import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the reverseShuffleMerge function below.
    static String reverseShuffleMerge(char[] s) {
        int[] skips = new int[26];
        int[] taken = new int[26];

        for(char c : s)
            skips[c-'a']++;

        for(int i=0; i<26; i++) {
            skips[i] = skips[i] / 2;
            taken[i] = skips[i];
        }

        int smallest = smallest(taken);
        char bestSeen = 'z' + 1;
        int bestIndex = -1;
        StringBuilder sb = new StringBuilder();

        for(int i=s.length-1; i>-1 && sb.length() < s.length/2; i--) {
            if(taken[s[i]-'a'] == 0) {
                continue;
            }

            if(s[i]-'a' == smallest) {
                sb.append(s[i]);
                taken[s[i]-'a']--;
                smallest = smallest(taken);
                bestSeen = 'z' + 1;
            }
            else if(skips[s[i]-'a'] != 0) {
                if(s[i] < bestSeen) {
                    bestSeen = s[i];
                    bestIndex = i;
                }
                skips[s[i]-'a']--;
            }
            else {
                if(s[i] >= bestSeen) {
                    while(i < bestIndex) {
                        i++;
                        skips[s[i]-'a']++;
                    }
                }
                sb.append(s[i]);
                taken[s[i]-'a']--;
                bestSeen = 'z' + 1;
            }
        }

        return sb.toString();
    }

    static int smallest(int[] a) {
        for(int i=0; i<a.length; i++) {
            if(a[i] != 0)
                return i;
        }
        return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = reverseShuffleMerge(s.toCharArray());

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
