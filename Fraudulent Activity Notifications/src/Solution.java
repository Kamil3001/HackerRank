import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {

    // NOTE: Since expenditure[i] <= 200 one can use a frequency table
    // with counting sort
    // Another neat solution is the use of heaps!

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        ArrayList<Integer> data = new ArrayList<Integer>();
        for(int i=0; i<d; i++) {
            data.add(expenditure[i]); // O(d)
        }
        Collections.sort(data);

        int count = 0;
        for(int i=d; i<expenditure.length; i++) { // O (n)
            if(expenditure[i] >= 2 * medianOf(data))
                count++;

            if(expenditure[i] != expenditure[i-d]) {
                data.remove(Collections.binarySearch(data, expenditure[i - d])); // O(log d)
                int addingIndex = Collections.binarySearch(data, expenditure[i]); // O(log d)

                if(addingIndex < 0)
                    addingIndex = -(addingIndex+1);

                data.add(addingIndex, expenditure[i]); // O(d)
            }
        }

        return count;
    }

    private static double medianOf(ArrayList<Integer> data) {
        if(data.size() % 2 == 0) {
            return (data.get(data.size()/2)+data.get(data.size()/2 - 1)) / 2.0;
        }
        else {
            return data.get(data.size()/2);
        }
    }



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
}
