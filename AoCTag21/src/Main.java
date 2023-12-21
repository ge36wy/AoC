import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    static HashMap<Point, Character> layout = new HashMap<>();
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int i = 0;
            Point start = null;
            while ((line = reader.readLine()) != null) {
                for (int j = 0; j < line.length(); j++){
                    layout.put(new Point(i, j), line.charAt(j));
                    if(line.charAt(j) == 'S') start = new Point(i, j);
                }
                i++;
            }
            i = 0;
            HashSet<Point> current = new HashSet<>();
            current.add(start);
            while (i < 64) {
                current = getNextPoints(current);
                i++;
            }
            System.out.println(current.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashSet<Point> getNextPoints(HashSet<Point> inputs){
        HashSet<Point> next = new HashSet<>();
        for(Point point: inputs){
            ArrayList<Point> neighbors = new ArrayList<>();
            neighbors.add(new Point(point.x - 1, point.y));
            neighbors.add(new Point(point.x + 1, point.y));
            neighbors.add(new Point(point.x, point.y - 1));
            neighbors.add(new Point(point.x, point.y + 1));
            for(Point p: neighbors){
                if(layout.containsKey(p) && (layout.get(p) == '.' || layout.get(p) == 'S')) next.add(p);
            }
        }
        return next;
    }
}