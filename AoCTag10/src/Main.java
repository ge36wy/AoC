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
            ArrayList<String> lines = new ArrayList<>();
            int row = 0;
            int column;
            int c = 0;
            ArrayList<char[]> allChars = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if(line.contains("S")) row = c;
                c++;
                lines.add(line);
                char[] charLine = new char[280];
                for (int i = 0; i < 280; i++){
                        charLine[i] = '.';
                }
                allChars.add(charLine);
                char[] chars = new char[280];
                Arrays.fill(chars, '.');
                allChars.add(chars);
            }
            column = lines.get(row).indexOf("S");
            int nextRow = row + 1;
            int nextColumn = column;
            String nextSymbol = "|";
            while (!nextSymbol.equals("S")) {
                allChars.get(nextRow * 2)[nextColumn * 2] = nextSymbol.toCharArray()[0];
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
                        } else {
                            nextRow--;
                            column--;
                        }
                    }
                    case "J" -> {
                        if (nextRow > row) {
                            nextColumn--;
                            row++;
                        } else {
                            nextRow--;
                            column++;
                        }
                    }
                    case "7" -> {
                        if (nextRow < row) {
                            nextColumn--;
                            row--;
                        } else {
                            nextRow++;
                            column++;
                        }
                    }
                    case "F" -> {
                        if (nextRow < row) {
                            nextColumn++;
                            row--;
                        } else {
                            nextRow++;
                            column--;
                        }
                    }
                }
                nextSymbol = lines.get(nextRow).substring(nextColumn, nextColumn + 1);
            }
            allChars.get(nextRow * 2)[nextColumn * 2] = '7'; //Start
            //close the line gaps
            for(int i = 0; i < allChars.size(); i++){
                for (int j = 0; j < allChars.get(i).length; j++) {
                    //down
                    if(i < allChars.size() - 2 && (allChars.get(i)[j] == '|' || allChars.get(i)[j] == '7' || allChars.get(i)[j] == 'F') && (allChars.get(i + 2)[j] == '|' || allChars.get(i + 2)[j] == 'L' || allChars.get(i + 2)[j] == 'J')){
                        if(allChars.get(i + 1)[j] == '.') allChars.get(i + 1)[j] = '|';
                    }
                    //left
                    if(j > 1 && (allChars.get(i)[j] == '-' || allChars.get(i)[j] == '7' || allChars.get(i)[j] == 'J') && (allChars.get(i)[j - 2] == '-' || allChars.get(i)[j - 2] == 'L' || allChars.get(i)[j - 2] == 'F')){
                        if(allChars.get(i)[j - 1] == '.') allChars.get(i)[j - 1] = '-';
                    }
                }
            }
            //remove outer edge
            for(int i = 0; i < allChars.size(); i++){
                for (int j = 0; j < allChars.get(i).length; j++) {
                    if (allChars.get(i)[j] == '.' && (i == 0 || i == (allChars.size() - 1) || j == 0 || j == (allChars.get(i).length - 1))){
                        allChars.get(i)[j] = ' ';
                    }
                }
            }
            //remove rest
            int removed = 1;
            while(removed > 0){
                removed = 0;
                for(int i = 0; i < allChars.size(); i++) {
                    for (int j = 0; j < allChars.get(i).length; j++) {
                        if (allChars.get(i)[j] == '.' && (((i < allChars.size() - 1) && allChars.get(i + 1)[j] == ' ') || (i > 0 && allChars.get(i - 1)[j] == ' ') || ((j < allChars.get(i).length - 1) && allChars.get(i)[j + 1] == ' ') || j > 0 && (allChars.get(i)[j - 1] == ' '))) {
                            allChars.get(i)[j] = ' ';
                            removed++;
                        }
                    }
                }
            }
            int points = 0;
            for(int i = 0; i < allChars.size(); i += 2){
                for (int j = 0; j < allChars.get(i).length; j += 2){
                    if(allChars.get(i)[j] == '.') points++;
                }
            }
            long after = System.nanoTime();
            System.out.println(points);
            System.out.println("Time: " + TimeUnit.NANOSECONDS.toMillis(after - before) + " milliseconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}