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
            ArrayList<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            //Race[] races = {new Race(51, 377), new Race(69, 1171), new Race(98, 1224), new Race(78, 1505)};
            int counter = 0;
            for (int i = 1; i < 51699878; i++){
                if( (long)i * (51699878 - i) > 377117112241505L) counter++;
            }
            System.out.println(counter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}