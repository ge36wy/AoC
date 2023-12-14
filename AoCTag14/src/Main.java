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
            long total;
            ArrayList<char[]> layout = new ArrayList<>();
            ArrayList<Long> totals =new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                layout.add(line.toCharArray());
            }
            for(long k = 0; k < 1000000000L; k++) {
                //north
                for (int l = 1; l < layout.size(); l++) {
                    for (int i = 0; i < layout.get(l).length; i++) {
                        if (layout.get(l)[i] == '#' || layout.get(l)[i] == '.') continue;
                        int j = l - 1;
                        while (layout.get(j)[i] == '.') {
                            j--;
                            if (j < 0) {
                                break;
                            }
                        }
                        j++;
                        layout.get(l)[i] = '.';
                        layout.get(j)[i] = 'O';
                    }
                }
                //west
                for (int l = 1; l < layout.get(0).length; l++) {
                    for (char[] chars : layout) {
                        if (chars[l] == '#' || chars[l] == '.') continue;
                        int j = l - 1;
                        while (chars[j] == '.') {
                            j--;
                            if (j < 0) {
                                break;
                            }
                        }
                        j++;
                        chars[l] = '.';
                        chars[j] = 'O';
                    }
                }
                //south
                for (int l = layout.size() - 2; l >= 0; l--) {
                    for (int i = 0; i < layout.get(l).length; i++) {
                        if (layout.get(l)[i] == '#' || layout.get(l)[i] == '.') continue;
                        int j = l + 1;
                        while (layout.get(j)[i] == '.') {
                            j++;
                            if (j >= layout.size()) {
                                break;
                            }
                        }
                        j--;
                        layout.get(l)[i] = '.';
                        layout.get(j)[i] = 'O';
                    }
                }
                //east
                for (int l = layout.get(0).length - 2; l >= 0; l--) {
                    for (char[] chars : layout) {
                        if (chars[l] == '#' || chars[l] == '.') continue;
                        int j = l + 1;
                        while (chars[j] == '.') {
                            j++;
                            if (j >= layout.get(0).length) {
                                break;
                            }
                        }
                        j--;
                        chars[l] = '.';
                        chars[j] = 'O';
                    }
                }
                //calc load
                total = 0;
                for(int l = 0; l < layout.size(); l++){
                    for(int i = 0; i < layout.get(l).length; i++){
                        if(layout.get(l)[i] == 'O') total += layout.size() - l;
                    }
                }
                if(totals.contains(total)){
                    System.out.println(total);
                    System.out.println(k);
                    System.out.println(totals.indexOf(total));
                    //Notice repetition, compute cycle
                }
                else totals.add(total);
            }
            for(char[] l: layout){
                System.out.println(l);
            }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}