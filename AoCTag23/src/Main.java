import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<Point, Character> layout = new HashMap<>();
    static HashMap<Point, HashMap<Point, Integer>> distances = new HashMap<>();
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            Point start = null;
            Point finish = null;
            HashMap<Point, ArrayList<Point>> neighbors = new HashMap<>();
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
            ArrayList<Point> intersections = new ArrayList<>();
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
                        if(layout.containsKey(n) && layout.get(n) == '.'){
                            neigh.add(n);
                        }
                    }
                }
                neighbors.put(entry.getKey(), neigh);
            }
            System.out.println(neighbors.get(new Point(137, 41)));
            for(Map.Entry<Point, Character> entry : layout.entrySet()){
                int count = 0;
                if(entry.getValue() == '.'){
                    for (Point p: directions){
                        Point newPoint = new Point(entry.getKey().x + p.x, entry.getKey().y + p.y);
                        if(layout.containsKey(newPoint) && layout.get(newPoint) == '.'){
                            count++;
                        }
                    }
                    if(count > 2){
                        intersections.add(entry.getKey());
                    }
                }
            }
            intersections.add(0, start);
            for(Point current: intersections){
                ArrayList<Point> pf = neighbors.get(current);
                for(Point point: pf){
                    Point previous = current;
                    Point here = point;
                    int distance = 1;
                    while(true){
                        ArrayList<Point> points = new ArrayList<>();
                        for(Point p: neighbors.get(here)) points.add(new Point(p.x, p.y));
                        points.remove(previous);
                        if(points.size() > 1 || here.equals(finish) ||intersections.contains(here)) break;
                        previous = here;
                        here = points.get(0);
                        distance++;
                    }
                    if(!distances.containsKey(new Point(current.x, current.y))) distances.put(new Point(current.x, current.y), new HashMap<>());
                    distances.get(new Point(current.x, current.y)).put(here, distance);
                }
            }
            for(Point p: distances.keySet()) {
                System.out.println(p + " " + distances.get(p));
            }

            Comparator<PointWithVal> comp = Comparator.comparingInt(e -> e.value);
            PriorityQueue<PointWithVal> frontier = new PriorityQueue<>(comp.reversed());
            frontier.add(new PointWithVal(start, 0, new ArrayList<>()));
            int cost = 0;
            while (!frontier.isEmpty()){
                PointWithVal currentWithVal = frontier.poll();
                Point current = currentWithVal.point;
                if(current.equals(finish)) {
                    cost = Math.max(cost, currentWithVal.value);
                    continue;
                }
                for(Point next: distances.get(current).keySet()){
                    if(currentWithVal.previous.contains(next)) continue;
                    int new_cost = currentWithVal.value + distances.get(current).get(next);
                        ArrayList<Point> previous = new ArrayList<>();
                        for(Point point: currentWithVal.previous) previous.add(new Point(point.x, point.y));
                        previous.add(current);
                        frontier.add(new PointWithVal(next, new_cost, previous));
                }
            }
            System.out.println(cost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}