import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    public static void main(String[] args) {

        
        String[] strs = {"eat","tea","tan","ate","nat","bat", "", ""};
        System.out.println(strs[0].toCharArray());
        groupAnagrams(strs);
    }
    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> outpuList = new ArrayList<List<String>>();


        HashMap<Integer, List<String>> wordToValue = new HashMap<>();

        System.out.println(strs); //PRINT
        for (String word : strs) {
            int wordVal = 0;
            for (int i = 0; i < word.length(); i ++) {
                wordVal += Character.getNumericValue(word.charAt(i));
            }

            if (!wordToValue.containsKey(wordVal)) { 
                List<String> vals = new ArrayList<>();
                wordToValue.put(wordVal, vals);
            }
            
            List<String> vals = wordToValue.get(wordVal);
            vals.add(word);
            wordToValue.put(wordVal, vals);
            
            
        }
        System.out.println(wordToValue); // PRINT
        for (List<String> val : wordToValue.values()) {
            outpuList.add(val);
        }
        System.out.println(outpuList); //PRINT
        return outpuList;
       
    }
}