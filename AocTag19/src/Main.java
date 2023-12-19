import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    static HashMap<String,ArrayList<String>> nameToWork = new HashMap<>();
    static ArrayList<Rating> accepted = new ArrayList<>();
    public static void main(String[] args){
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            long total = 0;

            ArrayList<Rating> ratings = new ArrayList<>();
            while ((line = reader.readLine()) != null && line.length() > 0) {
                    String[] s = line.split("\\{");
                    String[] flows =  s[1].substring(0, s[1].length() - 1).split(",");
                    nameToWork.put(s[0], new ArrayList<>(List.of(flows)));
            }
            while ((line = reader.readLine()) != null) {
                String[] r =  line.substring(1, line.length() - 1).split(",");
                ratings.add(new Rating(Integer.parseInt(r[0].split("=")[1]), Integer.parseInt(r[1].split("=")[1]), Integer.parseInt(r[2].split("=")[1]), Integer.parseInt(r[3].split("=")[1])));
            }
            for(Rating r: ratings) {
                decide(r);
            }
            for(Rating r: accepted){
                total += r.sum();
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decide(Rating r){
        String start = "in";
        ArrayList<String> rules = nameToWork.get(start);
        while (true){
            for (String rule: rules){
                if(rule.equals("A")){
                    accepted.add(r);
                    return;
                }
                if (rule.equals("R")) return;
                int num = 0;
                switch (rule.charAt(0)) {
                    case 'x' -> num = r.x;
                    case 'm' -> num = r.m;
                    case 'a' -> num = r.a;
                    case 's' -> num = r.s;
                }
                if(rule.charAt(1) == '>'){
                    String[] s = rule.split(">")[1].split(":");
                    if(num > Integer.parseInt(s[0])){
                        if(s[1].equals("A")){
                            accepted.add(r);
                            return;
                        }
                        if (s[1].equals("R")) return;
                        rules = nameToWork.get(s[1]);
                        break;
                    }
                }else if(rule.charAt(1) == '<'){
                    String[] s = rule.split("<")[1].split(":");
                    if(num < Integer.parseInt(s[0])){
                        if(s[1].equals("A")){
                            accepted.add(r);
                            return;
                        }
                        if (s[1].equals("R")) return;
                        rules = nameToWork.get(s[1]);
                        break;
                    }
                }else{
                    rules = nameToWork.get(rule);
                    break;
                }
            }
        }
    }
}