import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            HashMap<Integer,ArrayList<String>> map = new HashMap<>();
            for(int i = 0; i < 256; i++){
                map.put(i, new ArrayList<>());
            }
            ArrayList<String> layout = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] codes = line.split(",");
                layout.addAll(Arrays.asList(codes));
            }
            for(String l: layout){
                int last = Math.max(l.indexOf('='), l.indexOf('-'));
                String label = l.substring(0, last);
                int b = calcHash(label.toCharArray());
                if(l.charAt(last) == '-'){
                    map.get(b).removeIf(s -> s.startsWith(label + " "));
                }else{
                    if(map.get(b).stream().anyMatch(p -> p.startsWith(label + " "))) {
                        ArrayList<String> box = map.get(b);
                        int i;
                        for (i = 0; i < box.size(); i++) {
                            if(box.get(i).startsWith(label + " ")) break;
                        }
                        map.get(b).set(i, label + " " + l.charAt(last + 1));
                    }else{
                        map.get(b).add(label + " " + l.charAt(last + 1));
                    }
                }
            }
            for(int i = 0; i < 256; i++){
                for(int j = 0; j < map.get(i).size(); j++){
                    total += (long) (i + 1) * (j + 1) * Integer.parseInt(map.get(i).get(j).split("\s")[1]);
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int calcHash(char[] input){
        int total = 0;
        for (char c : input) {
            total += c;
            total *= 17;
            total = total % 256;
        }
        return total;
   }
}