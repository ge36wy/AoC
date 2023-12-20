import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static ArrayList<State> horizontal = new ArrayList<>();
    public static ArrayList<State> vertical = new ArrayList<>();

    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            ArrayList<ArrayList<Integer>> layout = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                ArrayList<Integer> l = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    l.add(Character.getNumericValue(line.charAt(i)));
                }
                layout.add(l);
            }
            for (int i = 0; i < layout.size(); i++) {
                for (int j = 0; j < layout.get(0).size(); j++) {
                    horizontal.add(new State(i * layout.size() + j, i, j, layout.get(i).get(j), Directions.LEFTRIGHT));
                    vertical.add(new State(i * layout.size() + j, i, j, layout.get(i).get(j), Directions.UPDOWN));
                }
            }
            State start1 = horizontal.get(0);
            State start2 = vertical.get(0);
            State finish1 = horizontal.get(horizontal.size() - 1);
            State finish2 = vertical.get(vertical.size() - 1);

            HashMap<State, State> came_from = new HashMap<>();
            HashMap<State, Integer> cost_so_far = new HashMap<>();
            came_from.put(start1, null);
            came_from.put(start2, null);
            cost_so_far.put(start1, 0);
            cost_so_far.put(start2, 0);
            PriorityQueue<VisitedState> pq = new PriorityQueue<>(Comparator.comparingInt(e -> cost_so_far.get(e.state)));
            pq.add(new VisitedState(start1, 0));
            pq.add(new VisitedState(start2, 0));
            while (!pq.isEmpty()){
                VisitedState current = pq.poll();
                if(current.state.id == finish1.id) break;
                ArrayList<State> follows = new ArrayList<>();
                if(current.state.direction == Directions.UPDOWN){
                    if(current.state.y < layout.get(0).size() - 1) {
                        State s1 = horizontal.get(current.state.id + 1);
                        s1.direction = Directions.LEFTRIGHT;
                        follows.add(s1);
                    }
                    if(current.state.y > 0) {
                        State s4 = horizontal.get(current.state.id - 1);
                        s4.direction = Directions.LEFTRIGHT;
                        follows.add(s4);
                    }
                    if(current.state.y < layout.get(0).size() - 2) {
                        State s2 = horizontal.get(current.state.id + 2);
                        s2.direction = Directions.LEFTRIGHT;
                        follows.add(s2);
                    }
                    if(current.state.y > 1) {
                        State s5 = horizontal.get(current.state.id - 2);
                        s5.direction = Directions.LEFTRIGHT;
                        follows.add(s5);
                    }
                    if(current.state.y < layout.get(0).size() - 3) {
                        State s3 = horizontal.get(current.state.id + 3);
                        s3.direction = Directions.LEFTRIGHT;
                        follows.add(s3);
                    }
                    if(current.state.y > 2) {
                        State s6 = horizontal.get(current.state.id - 3);
                        s6.direction = Directions.LEFTRIGHT;
                        follows.add(s6);
                    }
                }
                if(current.state.direction == Directions.LEFTRIGHT){
                    if(current.state.x < layout.get(0).size() - 1) {
                        State s1 = vertical.get(current.state.id + layout.size());
                        s1.direction = Directions.UPDOWN;
                        follows.add(s1);
                    }
                    if(current.state.x > 0) {
                        State s4 = vertical.get(current.state.id - layout.size());
                        s4.direction = Directions.UPDOWN;
                        follows.add(s4);
                    }
                    if(current.state.x < layout.get(0).size() - 2) {
                        State s2 = vertical.get(current.state.id + 2 * layout.size());
                        s2.direction = Directions.UPDOWN;
                        follows.add(s2);
                    }
                    if(current.state.x > 1) {
                        State s5 = vertical.get(current.state.id - 2 * layout.size());
                        s5.direction = Directions.UPDOWN;
                        follows.add(s5);
                    }
                    if(current.state.x < layout.get(0).size() - 3) {
                        State s3 = vertical.get(current.state.id + 3 * layout.size());
                        s3.direction = Directions.UPDOWN;
                        follows.add(s3);
                    }
                    if(current.state.x > 2) {
                        State s6 = vertical.get(current.state.id - 3 * layout.size());
                        s6.direction = Directions.UPDOWN;
                        follows.add(s6);
                    }
                }
                for(State next: follows){
                    int loss = 0;
                    if(next.direction == Directions.UPDOWN) {
                        if (next.x < current.state.x) {
                            for (int i = 0; i < current.state.x - next.x; i++) {
                                loss += vertical.get(next.id + i * layout.get(0).size()).heatloss;
                            }
                        } else {
                            for (int i = 0; i < next.x - current.state.x; i++) {
                                loss += vertical.get(next.id - i * layout.get(0).size()).heatloss;
                            }
                        }
                    }
                    if(next.direction == Directions.LEFTRIGHT) {
                        if (next.y > current.state.y) {
                            for(int i = 0; i < next.y - current.state.y; i++){
                                loss += horizontal.get(next.id - i).heatloss;
                            }
                        }else {
                            for (int i = 0; i < current.state.y - next.y; i++) {
                                loss += horizontal.get(next.id + i).heatloss;
                            }
                        }
                    }
                    int newCost = cost_so_far.get(current.state) + loss;
                    if(!cost_so_far.containsKey(next) || newCost < cost_so_far.get(next)){
                        cost_so_far.put(next, newCost);
                        pq.add(new VisitedState(next, newCost));
                        came_from.put(next, current.state);
                    }
                }
            }
            State s = finish2;
            while (s != start1 && s!= start2){
                System.out.println(s.x + " " + s.y);
                s = came_from.get(s);
            }
            System.out.println(cost_so_far.get(finish2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}