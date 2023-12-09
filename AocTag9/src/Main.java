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
            ArrayList<String> lines = new ArrayList<>();
            Long total = 0L;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            for(String s: lines){
                String[] nums = s.split("\s");
                ArrayList<ArrayList<Long>> listOfNums = new ArrayList<>();
                ArrayList<Long> numbers = new ArrayList<>();
                for(String s2: nums){
                    numbers.add(Long.parseLong(s2));
                }
                Collections.reverse(numbers);
                listOfNums.add(numbers);
                while(!listOfNums.get(listOfNums.size() - 1).stream().allMatch(e -> e.equals(0L))){
                    ArrayList<Long> current = listOfNums.get(listOfNums.size() - 1);
                    ArrayList<Long> next = new ArrayList<>();
                    for(int i = 0; i < current.size() - 1; i++){
                        next.add(current.get(i + 1) - current.get(i));
                    }
                    listOfNums.add(next);
                }
                listOfNums.get(listOfNums.size() - 1).add(0L);
                for(int i = listOfNums.size() - 2; i >= 0; i--){
                    listOfNums.get(i).add(listOfNums.get(i).get(listOfNums.get(i).size() - 1) + listOfNums.get(i + 1).get(listOfNums.get(i + 1).size() - 1));
                }
                total += listOfNums.get(0).get(listOfNums.get(0).size() - 1);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}