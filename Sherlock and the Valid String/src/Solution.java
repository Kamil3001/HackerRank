import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {

        if(s.length() <= 2)
            return "YES";

        int[] freq = new int[26];
        for(char c : s.toCharArray()) {
            freq[c - 97]++;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int value : freq) {
            if (value != 0) {
                // number of frequencies must be 2 or less
                if (!map.containsKey(value) && map.size() >= 2) {
                    return "NO";
                } else if (map.containsKey(value)) {
                    map.put(value, map.get(value) + 1);
                } else {
                    map.put(value, 1);
                }
            }
        }

        // if only one frequency OR freq=1 occurs once, trivial "YES"
        if(map.size() == 1 || map.containsKey(1) && map.get(1) == 1)
            return "YES";

        Iterator<Map.Entry<Integer, Integer>> i = map.entrySet().iterator();
        Map.Entry<Integer, Integer> entry1 = i.next();
        Map.Entry<Integer, Integer> entry2 = i.next();


        // if the SECOND frequency is larger by 1 and only one character has this frequency
        // OR
        // if the FIRST frequency is larger by 1 and only one character has this frequency
        // then we can remove that single character thus resulting in a Valid String
        if((entry1.getKey() - entry2.getKey() == -1 && entry2.getValue() == 1) ||
                (entry1.getKey() - entry2.getKey() == 1 && entry1.getValue() == 1)) {
            return "YES";
        }

        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
