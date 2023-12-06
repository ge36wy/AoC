import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int total = 0;
            int[] amount = new int[211];
            Arrays.fill(amount, 1);
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                String allNums = line.split(": ")[1];
                String[] list = allNums.split(" \\| ");
                ArrayList<String> winners = new ArrayList<>(Arrays.asList(list[0].split("\s+")));
                ArrayList<Integer> winnersInt = new ArrayList<>();
                for(String s: winners){
                    if(!Objects.equals(s, "")) {
                        winnersInt.add(Integer.parseInt(s));
                    }
                }
                ArrayList<String> myNumbers = new ArrayList<>(Arrays.asList(list[1].split("\s+")));
                ArrayList<Integer> myNumbersInt = new ArrayList<>();
                for(String s: myNumbers){
                    if(!Objects.equals(s, "")) {
                        myNumbersInt.add(Integer.parseInt(s));
                    }
                }
                int before = myNumbersInt.size();
                for(Integer i: winnersInt){
                    myNumbersInt.remove(i);
                }
                int wins = before - myNumbersInt.size();
                for(int i = counter + 1; i <= (counter + wins) && i < amount.length; i++){
                    amount[i] += amount[counter];
                }
                counter++;
            }
            total = IntStream.of(amount).sum();
            for(int i: amount){
                System.out.println(i);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}