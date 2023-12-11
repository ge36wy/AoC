import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            int total = 0;
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
                Arrays.fill(charArray, ' ');
                String str = new String(charArray);
                lines.add(emptyLines.get(i), str);
            }
            //add empty Columns
            for(i = emptyRows.size() - 1; i >= 0; i--){
                for(int j = 0; j < lines.size(); j++){
                    String newLine = lines.get(j).substring(0, emptyRows.get(i)) + "." + lines.get(j).substring(emptyRows.get(i));
                    lines.set(j, newLine);
                }
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
                    int rowDiff = Math.abs(galaxies.get(i).row - galaxies.get(j).row);
                    int columnDiff = Math.abs(galaxies.get(i).column - galaxies.get(j).column);
                    total += rowDiff + columnDiff;
                }
            }
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
}