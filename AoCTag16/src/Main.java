import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static ArrayList<boolean[]> illuminated = new ArrayList<>();
    public static ArrayList<State> states = new ArrayList<>();
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            ArrayList<char[]> layout = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                layout.add(line.toCharArray());
                boolean[] b = new boolean[line.length()];
                Arrays.fill(b, false);
                illuminated.add(b);
            }
            getnext(Direction.RIGHT, 0, 0, layout);
            for(boolean[] b: illuminated){
                for (boolean value : b) {
                    if (value) total++;
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getnext(Direction direction, int x, int y, ArrayList<char[]> layout){
        if (states.contains(new State(direction, x, y))){
            return;
        }
        states.add(new State(direction, x, y));
        if(x < 0 || y < 0 || x >= layout.size() || y >= layout.get(0).length) return;
        illuminated.get(x)[y] = true;
        char current = layout.get(x)[y];
        switch (direction){
            case UP -> {
                if(current == '.' || current == '|'){
                    x--;
                    while(x >= 0 && (layout.get(x)[y] == '.' || layout.get(x)[y] == '|')){
                        illuminated.get(x)[y] = true;
                        x--;
                    }
                    getnext(Direction.UP, x, y, layout);
                }
                if(current == '/') getnext(Direction.RIGHT, x, y + 1, layout);
                if(current == '\\') getnext(Direction.LEFT, x, y - 1, layout);
                if(current == '-') {
                    getnext(Direction.LEFT, x, y - 1, layout);
                    getnext(Direction.RIGHT, x, y + 1, layout);
                }
            }
            case DOWN -> {
                if(current == '.' || current == '|'){
                    x++;
                    while(x < layout.size() && (layout.get(x)[y] == '.' || layout.get(x)[y] == '|')){
                        illuminated.get(x)[y] = true;
                        x++;
                    }
                    getnext(Direction.DOWN, x, y, layout);
                }
                if(current == '\\') getnext(Direction.RIGHT, x, y + 1, layout);
                if(current == '/') getnext(Direction.LEFT, x, y - 1, layout);
                if(current == '-') {
                    getnext(Direction.LEFT, x, y - 1, layout);
                    getnext(Direction.RIGHT, x, y + 1, layout);
                }
            }
            case LEFT -> {
                if(current == '.' || current == '-'){
                    y--;
                    while(y > 0 && (layout.get(x)[y] == '.' || layout.get(x)[y] == '-')){
                        illuminated.get(x)[y] = true;
                        y--;
                    }
                    getnext(Direction.LEFT, x, y, layout);
                }
                if(current == '\\') getnext(Direction.UP, x - 1, y, layout);
                if(current == '/') getnext(Direction.DOWN, x + 1, y, layout);
                if(current == '|') {
                    getnext(Direction.UP, x - 1, y, layout);
                    getnext(Direction.DOWN, x + 1, y, layout);
                }
            }
            case RIGHT -> {
                if(current == '.' || current == '-'){
                    y++;
                    while(y < layout.get(0).length && (layout.get(x)[y] == '.' || layout.get(x)[y] == '-')){
                        illuminated.get(x)[y] = true;
                        y++;
                    }
                    getnext(Direction.RIGHT, x, y, layout);
                }
                if(current == '/') getnext(Direction.UP, x - 1, y, layout);
                if(current == '\\') getnext(Direction.DOWN, x + 1, y, layout);
                if(current == '|') {
                    getnext(Direction.UP, x - 1, y, layout);
                    getnext(Direction.DOWN, x + 1, y, layout);
                }
            }
        }
    }
}