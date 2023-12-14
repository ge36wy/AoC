import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Main {
    public static HashMap<RawRow, Long> map = new HashMap<>();
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            ArrayList<Row> rows = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split("\s");
                String[] nums = strings[1].split(",");
                ArrayList<Integer> numbers = new ArrayList<>();
                for(int i = 0; i < 5; i++) {
                    for (String s : nums) {
                        numbers.add(Integer.parseInt(s));
                    }
                }
                String springs = strings[0] + "?" + strings[0] + "?" + strings[0] + "?" +strings[0] + "?" + strings[0];
                int start = Math.max(0, Math.min(springs.indexOf('?'), springs.indexOf('#')));
                int end = Math.max(springs.length(), Math.max(springs.lastIndexOf('?'), springs.lastIndexOf('#')) + 1);
                Row r = new Row(new ArrayList<>(
                        springs.substring(start, end).replaceAll("\\.+", "\\.").chars()
                                .mapToObj(e -> (char) e)
                                .collect(
                                        Collectors.toList()
                                )
                ), numbers);
                rows.add(r);
            }
            for (Row row : rows) {
                total += recurs(row.springs, row.numbers, 0);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long recurs(ArrayList<Character> springs, ArrayList<Integer> numbers, int counter){
        if(map.containsKey(new RawRow(springs, numbers))){
            return map.get(new RawRow(springs, numbers));
        }
        long total = 0;
        if(springs.isEmpty()){
            if(numbers.isEmpty()) {
                total = total + 1L;
                return total;
            }
            return total;
        }
        if(springs.size() < numbers.size() + numbers.stream().mapToDouble(a -> a).sum() - 1) {
            return total;
        }
        if(numbers.isEmpty() && springs.contains('#')) {
            return total;
        }
        if(springs.get(0) == '.'){
            ArrayList<Character> a = new ArrayList<>(springs);
            a.remove(0);
            total += recurs(a, numbers, 0);
        }
        if(springs.get(0) == '?'){
            ArrayList<Character> a = new ArrayList<>(springs);
            a.remove(0);
            total += recurs(a, numbers, 0);
            ArrayList<Character> b = new ArrayList<>(springs);
            b.set(0, '#');
            total += recurs(b, numbers, 0);
        }
        if(springs.get(0) == '#'){
            if(counter == springs.size()){
                if(numbers.size() == 1 && numbers.get(0) == counter){
                    total = total + 1L;
                    return total;
                }
                return total;
            }
            if(springs.get(counter) == '#') {
                total += recurs(springs, numbers, counter + 1);
            }
            if(springs.get(counter) == '?') {
                ArrayList<Character> a = new ArrayList<>(springs);
                a.set(counter, '#');
                total += recurs(a, numbers, counter);
                ArrayList<Character> b = new ArrayList<>(springs);
                b.set(counter, '.');
                total += recurs(b, numbers, counter);
            }
            if(springs.get(counter) == '.') {
                if(counter != numbers.get(0)) return total;
                ArrayList<Integer> b = new ArrayList<>(numbers);
                b.remove(0);
                total += recurs(new ArrayList<>(springs.subList(counter, springs.size())), b, 0);
            }
        }
        map.put(new RawRow(springs, numbers), total);
        return total;
    }
}