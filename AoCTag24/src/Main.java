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
            ArrayList<Hail> hailstones = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" @ ");
                String[] pos = s[0].split(", ");
                String[] vel = s[1].split(", ");
                hailstones.add(new Hail(Long.parseLong(pos[0]), Long.parseLong(pos[1]), Long.parseLong(pos[2]), Long.parseLong(vel[0]), Long.parseLong(vel[1]), Long.parseLong(vel[2])));
            }


            /*for(int i = -500; i < 500; i++){
                for (int j = -500; j < 500; j++){
                    ArrayList<Hail> copy = new ArrayList<>();
                    for(Hail h: hailstones) copy.add(new Hail(h.px, h.py, h.pz, h.vx - i, h.vy - j, h.vz));
                    if(commonCollision(copy)) System.out.println(i + " " + j);
                }
            }*/
            // -63 -301
            ArrayList<Hail> copy = new ArrayList<>();
            for(Hail h: hailstones) copy.add(new Hail(h.px, h.py, h.pz, h.vx + 63, h.vy + 301, h.vz));
            ArrayList<Double> xDistance = commonCollision(copy);
            System.out.println(xDistance);
            for(int z = -5000; z < 5000; z++){
                ArrayList<Hail> zAxis = new ArrayList<>();
                for(Hail h: copy) zAxis.add(new Hail(h.px, h.pz, h.pz, h.vx, h.vz - z, h.vz));
                ArrayList<Double> res = commonCollision(zAxis);
                if(res != null)  System.out.println(res.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Double> commonCollision(ArrayList<Hail> corns){
        ArrayList<Double> avgX = new ArrayList<>();
        ArrayList<Double> avgY = new ArrayList<>();
        double maxX = 0;
        double maxY = 0;
        Hail first = corns.get(0);
        Hail second = corns.get(1);
        double xIntersection = (second.b - first.b) / (first.m - second.m);
        avgX.add(xIntersection);
        if(first.vx < 0 && first.px < xIntersection || first.vx > 0 && first.px > xIntersection) return null;
        if(second.vx < 0 && second.px < xIntersection || second.vx > 0 && second.px > xIntersection) return null;
        double yIntersection = first.m * xIntersection + first.b;
        avgY.add(yIntersection);
        if(first.vy < 0 && first.py < yIntersection || first.vy > 0 && first.py > yIntersection) return null;
        if(second.vy < 0 && second.py < yIntersection || second.vy > 0 && second.py > yIntersection) return null;
        for(int i = 2; i < corns.size() - 1; i += 2){
            first = corns.get(i);
            second = corns.get(i + 1);
            if((second.vx == 0 && Math.abs(second.px - xIntersection) < 2) || (first.vx == 0 && Math.abs(first.px - xIntersection) < 2) || (second.vy == 0 && Math.abs(second.py - yIntersection) < 2) || (first.vy == 0 && Math.abs(first.py - yIntersection) < 2)) continue;
            double newX = (second.b - first.b) / (first.m - second.m);
            avgX.add(newX);
            maxX = Math.max(maxX, Math.abs(newX - xIntersection));
            if(Math.abs(newX - xIntersection) > 2) return null;
            if(first.vx < 0 && first.px < newX || first.vx > 0 && first.px > newX) return null;
            if(second.vx < 0 && second.px < newX || second.vx > 0 && second.px > newX) return null;

            double newY = first.m * xIntersection + first.b;
            avgY.add(newY);
            maxY = Math.max(maxY, Math.abs(newY - yIntersection));
            if(Math.abs(newY - yIntersection) > 2) return null;
            if(first.vy < 0 && first.py < newY || first.vy > 0 && first.py > newY) return null;
            if(second.vy < 0 && second.py < newY || second.vy > 0 && second.py > newY) return null;
        }
        ArrayList<Double> xAndY = new ArrayList<>();
        xAndY.add(avgX.stream().mapToDouble(a -> a).average().orElse(0));
        xAndY.add(avgY.stream().mapToDouble(a -> a).average().orElse(0));
        return xAndY;
    }
}