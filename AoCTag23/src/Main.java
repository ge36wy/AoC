import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<Point, Character> layout = new HashMap<>();
    static HashMap<Point, ArrayList<Point>> neighbors = new HashMap<>();
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            Point start = null;
            Point finish = null;
            int c = 0;
            while ((line = reader.readLine()) != null) {
                    for(int i = 0; i < line.length(); i++){
                        Point curr = new Point(c, i);
                        if(c == 0 && line.charAt(i) == '.') start = curr;
                        if(c == 140 && line.charAt(i) == '.') finish = curr;
                        layout.put(curr, line.charAt(i));
                    }
                c++;
            }
            ArrayList<Point> directions = new ArrayList<>();
            directions.add(new Point(1, 0));
            directions.add(new Point(-1, 0));
            directions.add(new Point(0, 1));
            directions.add(new Point(0, -1));
            for(Map.Entry<Point, Character> entry : layout.entrySet()){
                ArrayList<Point> neigh = new ArrayList<>();
                if(entry.getValue() == '.'){
                    for (Point p: directions){
                        Point n = new Point(entry.getKey().x + p.x, entry.getKey().y + p.y);
                        if(layout.get(n) != null && layout.get(n) != '#'){
                            neigh.add(n);
                        }
                    }
                }
                if(entry.getValue() == '<'){
                    neigh.add(new Point(entry.getKey().x, entry.getKey().y - 1));
                }
                if(entry.getValue() == '>'){
                    neigh.add(new Point(entry.getKey().x, entry.getKey().y + 1));
                }
                if(entry.getValue() == 'v'){
                    neigh.add(new Point(entry.getKey().x + 1, entry.getKey().y));
                }
                if(entry.getValue() == '^'){
                    neigh.add(new Point(entry.getKey().x - 1, entry.getKey().y));
                }
                neighbors.put(entry.getKey(), neigh);
            }
            assert start != null;
            System.out.println(neighbors.get(start));

            Comparator<PointWithVal> comp = Comparator.comparingInt(e -> e.value);
            PriorityQueue<PointWithVal> frontier = new PriorityQueue<>(comp.reversed());
            frontier.add(new PointWithVal(start, 0, new ArrayList<>()));
            //HashMap<Point, Point> came_from = new HashMap<>();
            //came_from.put(start, null);
            HashMap<Point, Integer> cost_so_far = new HashMap<>();
            cost_so_far.put(start, 0);

            while (!frontier.isEmpty()){
                PointWithVal currentWithVal = frontier.poll();
                Point current = currentWithVal.point;
                if(current.equals(finish)) break;

                for(Point next: neighbors.get(current)){
                    if(currentWithVal.previous.contains(next)) continue;
                    int new_cost = cost_so_far.get(current) + 1;
                    if(!cost_so_far.containsKey(next) || new_cost > cost_so_far.get(next)){
                        cost_so_far.put(next, new_cost);
                        ArrayList<Point> p = new ArrayList<>();
                        for(Point point: currentWithVal.previous) p.add(new Point(point.x, point.y));
                        p.add(current);
                        frontier.add(new PointWithVal(next, new_cost, p));
                        //came_from.put(next, current);
                    }
                }
            }
            System.out.println(cost_so_far.get(finish));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}