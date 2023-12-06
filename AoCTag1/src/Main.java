import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        Tupel[] list = {new Tupel("one", 1), new Tupel("two", 2), new Tupel("three", 3), new Tupel("four", 4), new Tupel("five", 5), new Tupel("six", 6), new Tupel("seven", 7), new Tupel("eight", 8), new Tupel("nine", 9)};
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = null;
            int total = 0;
            while ((line = reader.readLine()) != null) {
                int number = 0;
                int i = 0;
                while (i < line.length()){
                    if(line.substring(i, i + 1).matches("\\d")){
                        total += Integer.parseInt(line.substring(i, i + 1)) * 10;
                        number += Integer.parseInt(line.substring(i, i + 1)) * 10;
                        break;
                    }
                    int num = findStringVor(line, i, list);
                    if (num > -1){
                        total += num * 10;
                        number += num * 10;
                        break;
                    }
                    i++;
                }
                i = line.length();
                while (i > 0){
                    if(line.substring(i - 1, i).matches("\\d")){
                        total += Integer.parseInt(line.substring(i - 1, i));
                        number += Integer.parseInt(line.substring(i - 1, i));
                        break;
                    }
                    int num = findStringRueck(line, i, list);
                    if (num > -1){
                        total += num;
                        number += num;
                        break;
                    }
                    i--;
                }
                System.out.println(line);
                System.out.println(number);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int findStringVor(String line, int i, Tupel[] list){
        for (Tupel t: list) {
            if(i + t.chars <= line.length() && line.substring(i, i + t.chars).equals(t.str)){
                return t.value;
            }
        }
        return -1;
    }

    public static int findStringRueck(String line, int i, Tupel[] list){
        for (Tupel t: list) {
            if(i - t.chars >= 0 && line.substring(i - t.chars, i).equals(t.str)){
                return t.value;
            }
        }
        return -1;
    }
}