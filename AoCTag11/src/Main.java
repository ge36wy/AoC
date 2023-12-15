import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long before = System.nanoTime();
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0L;
            ArrayList<String> lines = new ArrayList<>();
            ArrayList<Integer> emptyLines = new ArrayList<>();
            ArrayList<Integer> emptyRows = new ArrayList<>();
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if(!line.contains("#")){
                    emptyLines.add(i);
                }
                lines.add(line);
                i++;
            }
            int length = lines.get(0).length();
            for(i = 0; i < length; i++){
                if(isEmptyRow(lines, i)) emptyRows.add(i);
            }
            //add empty Rows
            for(i = emptyLines.size() - 1; i >= 0; i--){
                char[] charArray = new char[length];
                Arrays.fill(charArray, 'X');
                String str = new String(charArray);
                lines.set(emptyLines.get(i), str);
            }
            //add empty Columns
            for(i = emptyRows.size() - 1; i >= 0; i--){
                for(int j = 0; j < lines.size(); j++){
                    String newLine = lines.get(j).substring(0, emptyRows.get(i)) + "X" + lines.get(j).substring(emptyRows.get(i) + 1);
                    lines.set(j, newLine);
                }
            }
            for(String s: lines){
                System.out.println(s);
            }

            ArrayList<Galaxy> galaxies = new ArrayList<>();
            for(i = 0; i < lines.size(); i++){
                String l = lines.get(i);
                int index = l.indexOf("#");
                while (index >= 0) {
                    galaxies.add(new Galaxy(i, index));
                    index = l.indexOf("#", index + 1);
                }
            }
            for(i = 0; i < galaxies.size(); i++){
                for(int j = i + 1; j < galaxies.size(); j++){
                    total += getDistance(lines, galaxies.get(i), galaxies.get(j));
                }
            }
            long after = System.nanoTime();
            System.out.println("Time: " + TimeUnit.NANOSECONDS.toMillis(after - before) + " milliseconds");
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isEmptyRow(ArrayList<String> lines, int index){
        for (String line : lines) {
            if (line.charAt(index) == '#') return false;
        }
        return true;
    }

    public static long getDistance(ArrayList<String> lines, Galaxy g1, Galaxy g2){
        long total = 0;
        int minRow = Math.min(g1.row, g2.row);
        int maxRow = Math.max(g1.row, g2.row);
        for (int i = minRow + 1; i <= maxRow; i++){
            if (lines.get(i).charAt(g1.column) == 'X'){
                total += 1000000;
            }else{
                total += 1;
            }
        }
        int minColumn = Math.min(g1.column, g2.column);
        int maxColumn = Math.max(g1.column, g2.column);
        for (int i = minColumn + 1; i <= maxColumn; i++){
            if (lines.get(g1.row).charAt(i) == 'X'){
                total += 1000000;
            }else{
                total += 1;
            }
        }
        return total;
    }
}