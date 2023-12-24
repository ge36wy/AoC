import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int total = 0;
            ArrayList<Hail> hailstones = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" @ ");
                String[] pos = s[0].split(", ");
                String[] vel = s[1].split(", ");
                hailstones.add(new Hail(Long.parseLong(pos[0]), Long.parseLong(pos[1]), Long.parseLong(pos[2]), Long.parseLong(vel[0]), Long.parseLong(vel[1]), Long.parseLong(vel[2])));
            }

            for(int i = 0; i < hailstones.size(); i++){
                for(int j = i + 1; j < hailstones.size(); j++) {
                    Hail first = hailstones.get(i);
                    Hail second = hailstones.get(j);
                    double xIntersection = (second.b - first.b) / (first.m - second.m);
                    if(xIntersection < 200000000000000L || xIntersection > 400000000000000L) continue;
                    if(first.vx < 0 && first.px < xIntersection || first.vx > 0 && first.px > xIntersection) continue;
                    if(second.vx < 0 && second.px < xIntersection || second.vx > 0 && second.px > xIntersection) continue;

                    double yIntersection = first.m * xIntersection + first.b;
                    if(yIntersection < 200000000000000L || yIntersection > 400000000000000L) continue;
                    if(first.vy < 0 && first.py < yIntersection || first.vy > 0 && first.py > yIntersection) continue;
                    if(second.vy < 0 && second.py < yIntersection || second.vy > 0 && second.py > yIntersection) continue;
                    total++;
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}