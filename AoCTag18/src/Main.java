import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            ArrayList<ArrayList<Character>> ground = new ArrayList<>();
            ArrayList<Character> row = new ArrayList<>();
            row.add('#');
            ground.add(row);
            ArrayList<Instruction> instructions = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] s = line.split("\s");
                instructions.add(new Instruction(Direction.valueOf(s[0]), Integer.parseInt(s[1]), s[2]));
            }
            int currentX = 0;
            int currentY = 0;
            for (Instruction instruction: instructions) {
                for (int i = 0; i < instruction.distance; i++){
                    switch (instruction.direction){
                        case U -> {
                            if(currentX > 0){
                                currentX--;
                                ground.get(currentX).set(currentY, '#');
                            }else{
                                ArrayList<Character> newline = new ArrayList<>();
                                for(Character c: ground.get(0)){
                                    newline.add('.');
                                }
                                newline.set(currentY, '#');
                                ground.add(0, newline);
                            }
                        }
                        case D -> {
                            currentX++;
                            if(currentX >= ground.size()){
                                ArrayList<Character> newline = new ArrayList<>();
                                for(Character c: ground.get(0)){
                                    newline.add('.');
                                }
                                ground.add(newline);
                            }
                            ground.get(currentX).set(currentY, '#');
                        }
                        case L -> {
                            if(currentY > 0){
                                currentY--;
                            }else{
                                for(ArrayList<Character> c: ground){
                                    c.add(0, '.');
                                }
                            }
                            ground.get(currentX).set(currentY, '#');
                        }
                        case R -> {
                            currentY++;
                            if(currentY >= ground.get(0).size()){
                                for(ArrayList<Character> c: ground){
                                    c.add('.');
                                }
                            }
                            ground.get(currentX).set(currentY, '#');
                        }
                    }
                }
            }
            //border
            ArrayList<Character> newLine = new ArrayList<>();
            ArrayList<Character> newLine2 = new ArrayList<>();
            for (Character c: ground.get(0)){
                newLine.add('_');
                newLine2.add('_');
            }
            ground.add(0, newLine);
            ground.add(newLine2);
            for (ArrayList<Character> c: ground){
                c.add('_');
                c.add(0, '_');
            }
            int replaced = 1;
            while (replaced > 0){
                replaced = 0;
                for(int i = 1; i < ground.size() - 1; i++){
                    for (int j = 1; j < ground.get(0).size() - 1; j++){
                        if(ground.get(i).get(j) == '.'){
                            if((ground.get(i - 1).get(j) == '_') || (ground.get(i + 1).get(j) == '_') || (ground.get(i).get(j - 1) == '_') || (ground.get(i).get(j + 1) == '_')){
                                replaced++;
                                ground.get(i).set(j, '_');
                            }
                        }
                    }
                }
            }
            for(int i = 1; i < ground.size() - 1; i++){
                for (int j = 1; j < ground.get(0).size() - 1; j++) {
                    if(ground.get(i).get(j) == '.') ground.get(i).set(j, '#');
                }
            }
            for(ArrayList<Character> c: ground){
                total += Collections.frequency(c, '#');
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}