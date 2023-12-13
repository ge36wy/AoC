import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;
            ArrayList<ArrayList<String>> patterns = new ArrayList<>();
            ArrayList<String> pattern = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if(line.length() == 0){
                    patterns.add(pattern);
                    pattern = new ArrayList<>();
                }else{
                    pattern.add(line);
                }
            }
            patterns.add(pattern);


            for(ArrayList<String> patt: patterns) {
                for (int i = 0; i < patt.size() - 1; i++) {
                    if (patt.get(i).equals(patt.get(i + 1))) {
                        if (isReflection(patt, i - 1, i + 2)) {
                            System.out.println(i + " A");
                            total += (i + 1) * 100L;
                        }
                    }
                }
            }
            ArrayList<ArrayList<String>> flipped = new ArrayList<>();
            for(ArrayList<String> patt: patterns) {
                ArrayList<String> st = new ArrayList<>();
                StringBuilder s = new StringBuilder();
                for(int i = 0; i < patt.get(0).length(); i++){
                    for(String li: patt){
                       s.append(li.charAt(i));
                    }
                    st.add(String.valueOf(s));
                    s = new StringBuilder();
                }
                flipped.add(st);
            }
            for(ArrayList<String> patt: flipped) {
                for (int i = 0; i < patt.size() - 1; i++) {
                    if (patt.get(i).equals(patt.get(i + 1))) {
                        if (isReflection(patt, i - 1, i + 2)) {
                            System.out.println(i + " B");
                            total += (i + 1);
                        }
                    }
                }
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isReflection(ArrayList<String> patt, int st, int fin){
        while (st >= 0 && fin < patt.size()){
            if(!Objects.equals(patt.get(st), patt.get(fin))){
                return false;
            }
            st--;
            fin++;
        }
        return true;
    }
}