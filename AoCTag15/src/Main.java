import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            ArrayList<char[]> layout = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] codes = line.split(",");
                for(String s: codes) layout.add(s.toCharArray());
            }
            for(char[] l: layout){
                total += calcHash(l);
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