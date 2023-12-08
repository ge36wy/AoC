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
            String[] current = new String[0];
            for(int i = 2; i < lines.size(); i++){
                if(lines.get(i).startsWith("AAA")){
                    current = new String[]{lines.get(i).substring(0, 3), lines.get(i).substring(7, 10), lines.get(i).substring(12, 15)};
                }
               nodes.add(new String[]{lines.get(i).substring(0, 3), lines.get(i).substring(7, 10), lines.get(i).substring(12, 15)});
            }
            int counter = 0;
            int i = 0;

            while(!Objects.equals(current[0], "ZZZ")){
                System.out.println(current[0]);
                String instr = instructions.substring(i, i + 1);
                if(instr.equals("L")){
                    for (String[] node : nodes) {
                        if (Objects.equals(node[0], current[1])) {
                            current = node;
                            counter++;
                            break;
                        }
                    }
                }
                if(instr.equals("R")){
                    for (String[] node : nodes) {
                        if (Objects.equals(node[0], current[2])) {
                            current = node;
                            counter++;
                            break;
                        }
                    }
                }
                i = (i + 1) % instructions.length();
            }
            System.out.println(counter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}