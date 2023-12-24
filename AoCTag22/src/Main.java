import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<Integer, ArrayList<Cube>> heightToCube = new HashMap<>();
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            ArrayList<Cube> cubes = new ArrayList<>();
            ArrayList<Cube> removable = new ArrayList<>();
            int maxY = 0;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split("~");
                String[] start = s[0].split(",");
                String[] end = s[1].split(",");
                maxY = Math.max(maxY, Integer.parseInt(end[2]));
                cubes.add(new Cube(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2]), Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2])));
            }
            System.out.println("max: " + maxY);
            for (int h = 1; h <= maxY; h++){
                heightToCube.put(h, new ArrayList<>());
            }
            cubes.sort(Comparator.comparingInt(e -> e.z1));
            for(Cube cube: cubes){
                int h;
                for (h = cube.z1; h > 0; h--){
                   if(!put(cube, h)) break;
                }
                for (int j = h + 1; j <= h + 1 + cube.z2 - cube.z1; j++){
                    heightToCube.get(j).add(cube);
                }
            }
            //System.out.println(heightToCube.get(2));
            for(Cube cube: cubes){
                if(removable(cube)) removable.add(cube);
            }
            System.out.println(removable.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean fits(ArrayList<Cube> existingCubes, Cube newCube){
        ArrayList<Point> filled = new ArrayList<>();
        for (Cube cube: existingCubes){
            for (int i = cube.x1; i <= cube.x2; i++) {
                for (int j = cube.y1; j <= cube.y2; j++) {
                    filled.add(new Point(i, j));
                }
            }
        }
        for (int i = newCube.x1; i <= newCube.x2; i++) {
            for (int j = newCube.y1; j <= newCube.y2; j++) {
                if(filled.contains(new Point(i, j))) return false;
            }
        }
        return true;
    }

    public static boolean put(Cube cube, int height){
        for (int j = 0; j <= cube.z2 - cube.z1; j++){
            if(!fits(heightToCube.get(height + j), cube)) return false;
        }
        return true;
    }

    public static boolean getsSupported(ArrayList<Point> points, Cube cube) {
        for (int i = cube.x1; i <= cube.x2; i++) {
            for (int j = cube.y1; j <= cube.y2; j++) {
                if (points.contains(new Point(i, j))) return true;
            }
        }
        return false;
    }

    public static boolean removable(Cube cube){
        ArrayList<Integer> slices = new ArrayList<>();
        for(Map.Entry<Integer, ArrayList<Cube>> entry : heightToCube.entrySet()){
            if(entry.getValue().contains(cube)) slices.add(entry.getKey());
        }
        int maxHeight = Collections.max(slices);
        ArrayList<Cube> upperCubes = heightToCube.get(maxHeight + 1);
        System.out.println(upperCubes);
        ArrayList<Point> points = new ArrayList<>();
        for (int i = cube.x1; i <= cube.x2; i++){
            for (int j = cube.y1; j <= cube.y2; j++) {
                points.add(new Point(i, j));
            }
        }
        ArrayList<Cube> sup = new ArrayList<>();
        if(upperCubes == null){
            return true;
        }
        for(Cube c: upperCubes){
            if(getsSupported(points, c)) sup.add(c);
        }
        for(Cube c: sup){
            ArrayList<Cube> supporters = new ArrayList<>();
            ArrayList<Cube> below = heightToCube.get(maxHeight);
            ArrayList<Point> points2 = new ArrayList<>();
            for (int i = c.x1; i <= c.x2; i++){
                for (int j = c.y1; j <= c.y2; j++) {
                    points2.add(new Point(i, j));
                }
            }
            for(Cube cube1: below){
                if(getsSupported(points2, cube1)) supporters.add(cube1);
            }
            if (supporters.size() == 1) return false;
        }
        return true;
    }
}