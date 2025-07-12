import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
  
    /* build a hashmap <String, Integer> for a list of strings
        key: string,
        value: number of ocurrencies of that string
    */    
    public static Map<String, Integer> buildMap(List<String> text) {
        Map<String, Integer> hashMap = new HashMap<>();
        for (String word : text) {
            if(hashMap.containsKey(word)) {
                int occurrences = hashMap.get(word);
                occurrences++;
                hashMap.put(word, occurrences);
            }
            else {
                hashMap.put(word, 1);   
            }
        }
        return hashMap;
    }
    
    // check if we can write a text using words from a magazine (case sensetive)
    public static void checkMagazine(List<String> magazine, List<String> note) {
        Map<String, Integer> hashMapMagazine = buildMap(magazine);
        Map<String, Integer> hashMapNote = buildMap(note);
        
        for(Map.Entry<String, Integer> entry : hashMapNote.entrySet()) {
            if(!(hashMapMagazine.containsKey(entry.getKey()) && (hashMapMagazine.get(entry.getKey()) >= entry.getValue()))){
                System.out.println("No");
                return;
            }
        }
        
        System.out.println("Yes");  
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        List<String> magazine = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        List<String> note = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        Result.checkMagazine(magazine, note);

        bufferedReader.close();
    }
}
