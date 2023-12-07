import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            int total = 0;
            String line;
            ArrayList<Hand> hands = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("\s");
                int val = Integer.parseInt(split[1]);
                Map<Character, Integer> map = new HashMap<>();
                String s = split[0];
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    map.merge(c, 1, Integer::sum);
                }
                int j = 0;
                if (map.containsKey('J')) {
                    j = map.get('J');
                }
                map.remove('J');
                ArrayList<Integer> countsNoJ = new ArrayList<>(map.values());
                countsNoJ.sort(Comparator.reverseOrder());
                if(countsNoJ.isEmpty()){
                    countsNoJ.add(5);
                }else{
                    countsNoJ.set(0, countsNoJ.get(0) + j);
                }
                hands.add(new Hand(countsNoJ, s, val));
            }
            hands.sort(Hand::compare);
            for (int i = 0; i < hands.size(); i++){
                System.out.println(hands.get(i).hand + ", " + hands.get(i).countsNoJ);
                total += hands.get(i).val * (i + 1);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}