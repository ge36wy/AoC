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
                ArrayList<Integer> counts = new ArrayList<>(map.values());
                counts.sort(Comparator.reverseOrder());
                hands.add(new Hand(counts, s, val));
            }
            hands.sort(Hand::compare);
            for (int i = 0; i < hands.size(); i++){
                total += hands.get(i).val * (i + 1);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}