import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            ArrayList<ArrayList<String>> patterns = new ArrayList<>();
            ArrayList<String> pattern = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if(line.length() == 0){
                    patterns.add(pattern);
                    pattern = new ArrayList<>();
                }else{
                    pattern.add(line);
                }
            }
            patterns.add(pattern);
            //create flipped
            ArrayList<ArrayList<String>> flipped = new ArrayList<>();
            for(ArrayList<String> patt: patterns) {
                flipped.add(flip(patt));
            }
            for(int i = 0; i < patterns.size(); i++) {
                boolean found = false;
                ArrayList<String> nor = patterns.get(i);
                for(int j = 0; j < nor.size() - 1; j++){
                    if(!found) {
                        if (countDiffs(nor, j) == 1) {
                            found = true;
                            total += (j + 1) * 100L;
                        }
                    }
                }
                ArrayList<String> fl = flipped.get(i);
                for(int j = 0; j < fl.size() - 1; j++){
                    if(!found) {
                        if (countDiffs(fl, j) == 1) {
                            found = true;
                            total += j + 1;
                        }
                    }
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int countDiffs(ArrayList<String> pattern, int index){
        int differences = 0;
        int count = Math.min(index, pattern.size() - (index + 2)) + 1;
        ArrayList<String> before = new ArrayList<>(pattern.subList(index + 1 - count, index + 1));
        ArrayList<String> after = new ArrayList<>(pattern.subList(index + 1, index + count + 1));
        Collections.reverse(before);
        for (int i = 0; i < before.size(); i++){
            for (int j = 0; j < before.get(i).length(); j++){
                if(before.get(i).charAt(j) != after.get(i).charAt(j)) differences++;
            }
        }
        return differences;
    }

    public static ArrayList<String> flip(ArrayList<String> original){
        ArrayList<String> st = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < original.get(0).length(); i++){
            for(String li: original){
                s.append(li.charAt(i));
            }
            st.add(String.valueOf(s));
            s = new StringBuilder();
        }
        return st;
    }
}