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
            ArrayList<char[]> layout = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                layout.add(line.toCharArray());
            }
            for(int l = 1; l < layout.size(); l++){
                for (int i = 0; i < layout.get(l).length; i++){
                    if(layout.get(l)[i] == '#' || layout.get(l)[i] == '.') continue;
                    int j = l - 1;
                    while (layout.get(j)[i] == '.'){
                        j--;
                        if(j < 0){
                            break;
                        }
                    }
                    j++;
                    layout.get(l)[i] = '.';
                    layout.get(j)[i] = 'O';
                }
            }
            for(int l = 0; l < layout.size(); l++){
                for(int i = 0; i < layout.get(l).length; i++){
                    if(layout.get(l)[i] == 'O') total += layout.size() - l;
                }
            }
            System.out.println(total);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}