import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    //NOTE: this code is difficult to understand, but
    // I'm not bothered cleaning it up. Sorry about that

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        int op, data;

        HashMap<Integer, Integer> regFreq = new HashMap<>();
        HashMap<Integer, Integer> freqFreq = new HashMap<>();

        List<Integer> output = new ArrayList<>();

        for(List<Integer> query : queries) {
            op = query.get(0);
            data = query.get(1);

            if(op == 1) {
                mapsInsert(regFreq, freqFreq, data);
            }
            else if(op == 2) {
                mapsRemove(regFreq, freqFreq, data);
            }
            else if(op == 3) {
                if(freqFreq.containsKey(data))
                    output.add(1);
                else
                    output.add(0);
            }
        }

        return output;
    }

    static void mapsInsert(HashMap<Integer, Integer> regFreq, HashMap<Integer, Integer> freqFreq, int data) {
        int freqToBeIncreased = 1;

        if (regFreq.containsKey(data)) {
            int freqBeforeInsert = regFreq.get(data);

            regFreq.put(data, freqBeforeInsert+1);

            int freqFreqBeforeInsert = freqFreq.get(freqBeforeInsert);

            if (freqFreqBeforeInsert <= 1) {
                freqFreq.remove(freqBeforeInsert);
            }
            else {
                freqFreq.put(freqBeforeInsert, freqFreqBeforeInsert-1);
            }

            freqToBeIncreased = freqBeforeInsert+1;
        }
        else {
            regFreq.put(data, 1);
        }

        if (freqFreq.containsKey(freqToBeIncreased)) {
            freqFreq.put(freqToBeIncreased, freqFreq.get(freqToBeIncreased)+1);
        }
        else {
            freqFreq.put(freqToBeIncreased, 1);
        }
    }

    static void mapsRemove(HashMap<Integer, Integer> regFreq, HashMap<Integer, Integer> freqFreq, int data) {
        if (regFreq.containsKey(data)) {
            int freqBeforeRemoval = regFreq.get(data);
            if (freqBeforeRemoval <= 1) {
                regFreq.remove(data);
            }
            else {
                regFreq.put(data, freqBeforeRemoval-1);

                if(freqFreq.containsKey(freqBeforeRemoval-1))
                    freqFreq.put(freqBeforeRemoval-1, freqFreq.get(freqBeforeRemoval-1)+1);
                else
                    freqFreq.put(freqBeforeRemoval-1, 1);
            }

            int freqFreqBeforeRemoval = freqFreq.get(freqBeforeRemoval);
            if (freqFreqBeforeRemoval <= 1) {
                freqFreq.remove(freqBeforeRemoval);
            }
            else {
                freqFreq.put(freqBeforeRemoval, freqFreqBeforeRemoval-1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
