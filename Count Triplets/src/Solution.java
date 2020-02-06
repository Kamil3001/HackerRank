import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long, Long> oneThirdTriplet = new HashMap<>(); //stores the number of doubles that would be completed by given key
        HashMap<Long, Long> twoThirdsTriplet = new HashMap<>(); //stores the number of triplets that would be completed by given key

        long count = 0L;

        // only need to go through the array once due to the dynamic nature of the solution
        for(long l : arr) {

            // if this value completes a triplet, we increase the count by the number of potential triplets found up until now
            if(twoThirdsTriplet.containsKey(l)) {
                count += twoThirdsTriplet.get(l);
            }

            // if this value is the second value in a potential triplet we increase increase the number of 2/3 completed triplets
            if(oneThirdTriplet.containsKey(l)) {

                // increase by the number of 1/3 of a triplet found previously
                long val = oneThirdTriplet.get(l);

                if(twoThirdsTriplet.containsKey(l*r))
                    twoThirdsTriplet.put(l*r, twoThirdsTriplet.get(l*r)+val);
                else
                    twoThirdsTriplet.put(l*r, val);
            }

            // increase the amount of triplets that this value could start
            if(oneThirdTriplet.containsKey(l*r))
                oneThirdTriplet.put(l*r, oneThirdTriplet.get(l*r)+1);
            else
                oneThirdTriplet.put(l*r, 1L);
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
