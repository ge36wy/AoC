import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    static ArrayList<String[]> nodes = new ArrayList<>();
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
            String instructions = lines.get(0);
            ArrayList<String[]> current = new ArrayList<>();
            for(int i = 2; i < lines.size(); i++){
                if(lines.get(i).substring(0, 3).endsWith("A")){
                    current.add(new String[]{lines.get(i).substring(0, 3), lines.get(i).substring(7, 10), lines.get(i).substring(12, 15)});
                }
               nodes.add(new String[]{lines.get(i).substring(0, 3), lines.get(i).substring(7, 10), lines.get(i).substring(12, 15)});
            }
            int counter;
            int i = 0;
            ArrayList<Integer> count = new ArrayList<>();
            for (int j = 0; j < current.size(); j++) {
                counter = 0;
                while(!current.get(j)[0].endsWith("Z")) {
                    String instr = instructions.substring(i, i + 1);
                    if (instr.equals("L")) {
                        for (String[] node : nodes) {
                            if (Objects.equals(node[0], current.get(j)[1])) {
                                current.set(j, node);
                                counter++;
                                break;
                            }
                        }
                    }
                    if (instr.equals("R")) {
                        for (String[] node : nodes) {
                            if (Objects.equals(node[0], current.get(j)[2])) {
                                current.set(j, node);
                                counter++;
                                break;
                            }
                        }
                    }
                    i = (i + 1) % instructions.length();
                }
                count.add(counter);
            }
                System.out.println(lcm(count));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }
    public static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }

    public static long lcm(ArrayList<Integer> input)
    {
        long result = input.get(0);
        for(int i = 1; i < input.size(); i++) result = lcm(result, input.get(i));
        return result;
    }
}