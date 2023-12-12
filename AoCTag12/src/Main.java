import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0L;
            ArrayList<Row> rows = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split("\s");
                String[] nums = strings[1].split(",");
                ArrayList<Integer> numbers = new ArrayList<>();
                for (String s: nums){
                    numbers.add(Integer.parseInt(s));
                }
                int start = Math.max(0, Math.min(strings[0].indexOf('?'), strings[0].indexOf('#')));
                int end = Math.max(strings[0].length(), Math.max(strings[0].lastIndexOf('?'), strings[0].lastIndexOf('#')) + 1);
                Row r = new Row(new ArrayList<>(
                        strings[0].substring(start, end).replaceAll("\\.+", "\\.").chars()
                                .mapToObj(e -> (char) e)
                                .collect(
                                        Collectors.toList()
                                )
                ), numbers);
                r.getAllCombinations();
                rows.add(r);
            }
            for(Row r: rows){
                for (ArrayList<Integer> integers: r.comb) {
                    if(new Row(r.replace(integers), r.numbers).correct()){
                        total++;
                    }
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}