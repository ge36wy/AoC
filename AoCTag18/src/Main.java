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
            long total = 0;
            long border = 0;
            ArrayList<Coordinate> coordinates = new ArrayList<>();
            ArrayList<Instruction> instructions = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String s = line.split("\s")[2];
                s = s.substring(2, s.length() - 1);
                Direction d = null;
                if(s.charAt(s.length() - 1) == '0') d = Direction.R;
                if(s.charAt(s.length() - 1) == '1') d = Direction.D;
                if(s.charAt(s.length() - 1) == '2') d = Direction.L;
                if(s.charAt(s.length() - 1) == '3') d = Direction.U;
                long distance = Long.parseLong(s.substring(0, s.length() - 1), 16);
                border += distance;
                instructions.add(new Instruction(d, distance));
            }

            coordinates.add(new Coordinate(0, 0));
            for(int i = 1; i < instructions.size(); i++) {
                long x = 0;
                long y = 0;
                for(int j = 0; j < i; j++) {
                    if (instructions.get(j).direction == Direction.D) x += instructions.get(j).distance;
                    if (instructions.get(j).direction == Direction.U) x -= instructions.get(j).distance;
                    if (instructions.get(j).direction == Direction.R) y += instructions.get(j).distance;
                    if (instructions.get(j).direction == Direction.L) y -= instructions.get(j).distance;
                }
                coordinates.add(new Coordinate(x, y));
            }
            ArrayList<Matrix> matrices = new ArrayList<>();
            for(int i = 0; i < coordinates.size(); i++){
                Coordinate c1 = coordinates.get(i);
                Coordinate c2 = coordinates.get((i + 1) % coordinates.size());
                matrices.add(new Matrix(c1, c2));

            }
            for (Matrix m: matrices) {
                total += m.determ();
            }
            total = Math.abs(total) + border;
            total /= 2;
            System.out.println((total + 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}