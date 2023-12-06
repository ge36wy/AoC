import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int total = 0;
            while ((line = reader.readLine()) != null) {
                String info = line.split(": ")[1];
                String[] tries = info.split("; ");
                total += smaller(tries);
                System.out.println(smaller(tries));
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int smaller(String[] tries){
        int red = 0;
        int blue = 0;
        int green = 0;
        for(String s: tries){
            String[] colors = s.split(", ");
            for (String st: colors){
                String[] pairs = st.split(" ");
                    int num = Integer.parseInt(pairs[0]);
                    switch(pairs[1]) {
                        case "blue":
                            if(num > blue) blue = num;
                            break;
                        case "green":
                            if(num > green) green = num;
                            break;
                        case "red":
                            if(num > red) red = num;
                            break;
                }
            }
        }
        return red * green * blue;
    }
}