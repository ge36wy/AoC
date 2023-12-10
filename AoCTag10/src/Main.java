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
            ArrayList<String> lines = new ArrayList<>();
            int total = 1;
            int row = 0;
            int column;
            int c = 0;
            while ((line = reader.readLine()) != null) {
                if(line.contains("S")) row = c;
                c++;
                lines.add(line);
            }
            column = lines.get(row).indexOf("S");
            int nextRow = row + 1;
            int nextColumn = column;
            String nextSymbol = "|";
            while (!nextSymbol.equals("S")){
                total++;
                switch (nextSymbol) {
                    case "|" -> {
                        if (nextRow > row) {
                            nextRow++;
                            row++;
                        } else {
                            nextRow--;
                            row--;
                        }
                    }
                    case "-" -> {
                        if (nextColumn > column) {
                            nextColumn++;
                            column++;
                        } else {
                            nextColumn--;
                            column--;
                        }
                    }
                    case "L" -> {
                        if (nextRow > row) {
                            nextColumn++;
                            row++;
                        } else{
                            nextRow--;
                            column--;
                        }
                    }
                    case "J" -> {
                        if (nextRow > row) {
                            nextColumn--;
                            row++;
                        } else{
                            nextRow--;
                            column++;
                        }
                    }
                    case "7" -> {
                        if (nextRow < row) {
                            nextColumn--;
                            row--;
                        } else{
                            nextRow++;
                            column++;
                        }
                    }
                    case "F" -> {
                        if (nextRow < row) {
                            nextColumn++;
                            row--;
                        } else{
                            nextRow++;
                            column--;
                        }
                    }
                }
                nextSymbol = lines.get(nextRow).substring(nextColumn, nextColumn + 1);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}