import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main {
    static HashMap<String,ArrayList<String>> nameToWork = new HashMap<>();
    static ArrayList<AreaRule> areas = new ArrayList<>();
    static long total = 0;
    public static void main(String[] args){
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null && line.length() > 0) {
                    String[] s = line.split("\\{");
                    String[] flows =  s[1].substring(0, s[1].length() - 1).split(",");
                    nameToWork.put(s[0], new ArrayList<>(List.of(flows)));
            }
            RatingArea init = new RatingArea(1, 4000, 1, 4000, 1, 4000, 1, 4000);
            areas.add(new AreaRule(init, nameToWork.get("in")));
            while(!areas.isEmpty()) {
                AreaRule areaRule = areas.get(0);
                areas.remove(0);
                decide(areaRule.area, areaRule.rule);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decide(RatingArea area, ArrayList<String> rules){
        if(area.maxX < area.minX || area.maxM < area.minM || area.maxA < area.minA || area.maxS < area.minS) return;
        for (String rule: rules){
            if(rule.equals("A")){
                total += area.amount();
                return;
            }
            if (rule.equals("R")) return;
            int numMin = 0;
            int numMax = 0;
            switch (rule.charAt(0)) {
                case 'x' -> {
                    numMin = area.minX;
                    numMax = area.maxX;
                }
                case 'm' -> {
                    numMin = area.minM;
                    numMax = area.maxM;
                }
                case 'a' -> {
                    numMin = area.minA;
                    numMax = area.maxA;
                }
                case 's' -> {
                    numMin = area.minS;
                    numMax = area.maxS;
                }
            }
            if(rule.charAt(1) == '>'){
                String[] s = rule.split(">")[1].split(":");
                int border = Integer.parseInt(s[0]);
                if(numMin > border){
                    if(s[1].equals("A")){
                        total += area.amount();
                        return;
                    }
                    if (s[1].equals("R")) return;
                    areas.add(new AreaRule(area, nameToWork.get(s[1])));
                    return;
                }
                if(numMax <= Integer.parseInt(s[0])){
                    continue;
                }
                switch (rule.charAt(0)) {
                    case 'x' -> {
                        areas.add(new AreaRule(new RatingArea(area.minX, border, area.minM, area.maxM, area.minA, area.maxA, area.minS, area.maxS), rules));
                        RatingArea r = new RatingArea(border + 1, area.maxX, area.minM, area.maxM, area.minA, area.maxA, area.minS, area.maxS);
                        if(s[1].equals("A")){
                            total += r.amount();
                            return;
                        }
                        if(s[1].equals("R")){
                            return;
                        }
                        areas.add(new AreaRule(r, nameToWork.get(s[1])));
                        return;
                    }
                    case 'm' -> {
                        areas.add(new AreaRule(new RatingArea(area.minX, area.maxX, area.minM, border, area.minA, area.maxA, area.minS, area.maxS), rules));
                        RatingArea r = new RatingArea(area.minX, area.maxX, border + 1, area.maxM, area.minA, area.maxA, area.minS, area.maxS);
                        if(s[1].equals("A")){
                            total += r.amount();
                            return;
                        }
                        if(s[1].equals("R")){
                            return;
                        }
                        areas.add(new AreaRule(r, nameToWork.get(s[1])));
                        return;
                    }
                    case 'a' -> {
                        areas.add(new AreaRule(new RatingArea(area.minX, area.maxX, area.minM, area.maxM, area.minA, border, area.minS, area.maxS), rules));
                        RatingArea r = new RatingArea(area.minX, area.maxX, area.minM, area.maxM, border + 1, area.maxA, area.minS, area.maxS);
                        if(s[1].equals("A")){
                            total += r.amount();
                            return;
                        }
                        if(s[1].equals("R")){
                            return;
                        }
                        areas.add(new AreaRule(r, nameToWork.get(s[1])));
                        return;
                    }
                    case 's' -> {
                        areas.add(new AreaRule(new RatingArea(area.minX, area.maxX, area.minM, area.maxM, area.minA, area.maxA, area.minS, border), rules));
                        RatingArea r = new RatingArea(area.minX, area.maxX, area.minM, area.maxM, area.minA, area.maxA, border + 1, area.maxS);
                        if(s[1].equals("A")){
                            total += r.amount();
                            return;
                        }
                        if(s[1].equals("R")){
                            return;
                        }
                        areas.add(new AreaRule(r, nameToWork.get(s[1])));
                        return;
                    }
                }
            }else if(rule.charAt(1) == '<'){
                String[] s = rule.split("<")[1].split(":");
                int border = Integer.parseInt(s[0]);
                if(numMax < border){
                    if(s[1].equals("A")){
                        total += area.amount();
                        return;
                    }
                    if (s[1].equals("R")) return;
                    areas.add(new AreaRule(area, nameToWork.get(s[1])));
                    return;
                }
                if(numMin >= Integer.parseInt(s[0])){
                    continue;
                }
                switch (rule.charAt(0)) {
                    case 'x' -> {
                        areas.add(new AreaRule(new RatingArea(border, area.maxX, area.minM, area.maxM, area.minA, area.maxA, area.minS, area.maxS), rules));
                        RatingArea r = new RatingArea(area.minX, border - 1, area.minM, area.maxM, area.minA, area.maxA, area.minS, area.maxS);
                        if(s[1].equals("A")){
                            total += r.amount();
                            return;
                        }
                        if(s[1].equals("R")){
                            return;
                        }
                        areas.add(new AreaRule(r, nameToWork.get(s[1])));
                        return;
                    }
                    case 'm' -> {
                        areas.add(new AreaRule(new RatingArea(area.minX, area.maxX, border, area.maxM, area.minA, area.maxA, area.minS, area.maxS), rules));
                        RatingArea r = new RatingArea(area.minX, area.maxX, area.minM, border - 1, area.minA, area.maxA, area.minS, area.maxS);
                        if(s[1].equals("A")){
                            total += r.amount();
                            return;
                        }
                        if(s[1].equals("R")){
                            return;
                        }
                        areas.add(new AreaRule(r, nameToWork.get(s[1])));
                        return;
                    }
                    case 'a' -> {
                        areas.add(new AreaRule(new RatingArea(area.minX, area.maxX, area.minM, area.maxM, border, area.maxA, area.minS, area.maxS), rules));
                        RatingArea r = new RatingArea(area.minX, area.maxX, area.minM, area.maxM, area.minA, border - 1, area.minS, area.maxS);
                        if(s[1].equals("A")){
                            total += r.amount();
                            return;
                        }
                        if(s[1].equals("R")){
                            return;
                        }
                        areas.add(new AreaRule(r, nameToWork.get(s[1])));
                        return;
                    }
                    case 's' -> {
                        areas.add(new AreaRule(new RatingArea(area.minX, area.maxX, area.minM, area.maxM, area.minA, area.maxA, border, area.maxS), rules));
                        RatingArea r = new RatingArea(area.minX, area.maxX, area.minM, area.maxM, area.minA, area.maxA, area.minS, border - 1);
                        if(s[1].equals("A")){
                            total += r.amount();
                            return;
                        }
                        if(s[1].equals("R")){
                            return;
                        }
                        areas.add(new AreaRule(r, nameToWork.get(s[1])));
                        return;
                    }
                }
            }else{
                areas.add(new AreaRule(area, nameToWork.get(rule)));
                return;
            }
        }
    }
}