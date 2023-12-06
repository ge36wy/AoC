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
            int total = 0;
            String line;
            ArrayList<String> zeilen = new ArrayList<>();
            ArrayList<numb> numbers = new ArrayList<>();
            ArrayList<gear> gears = new ArrayList<>();
            int l = 0;
            while ((line = reader.readLine()) != null) {
                zeilen.add(line);
                String noDot = line.replace(".", " ");
                String gearSpaces = noDot.replaceAll("[^*]", " ");
                String numSpaces = noDot.replaceAll("\\D", " ");
                String[] nums = numSpaces.trim().split("\s+");
                String[] g = gearSpaces.trim().split("\s+");
                int p = 0;
                for(String s: nums){
                    int pos = line.indexOf(s, p);
                    p = pos + s.length();
                    numbers.add(new numb(l, pos, Integer.parseInt(s), s.length()));
                }
                p = 0;
                if(!Objects.equals(g[0], "")) {
                    for (String s : g) {
                        int pos = line.indexOf(s, p);
                        p = pos + 1;
                        gears.add(new gear(l, pos));
                    }
                }
                l++;
            }
            ArrayList<numb> parts = new ArrayList<>();
            for(numb num: numbers){
                String surround = "";
                if(num.line > 0) surround = surround.concat(zeilen.get(num.line - 1).substring(Integer.max(0, num.position - 1), Integer.min(num.position + num.length + 1, zeilen.get(num.line - 1).length())));
                if(num.line < zeilen.size() - 1) surround = surround.concat(zeilen.get(num.line + 1).substring(Integer.max(0, num.position - 1), Integer.min(num.position + num.length + 1, zeilen.get(num.line + 1).length())));
                surround = surround.concat(zeilen.get(num.line).substring(Integer.max(0, num.position - 1), Integer.min(num.position + num.length + 1, zeilen.get(num.line).length())));
                if(!surround.replace(".", "").replaceAll("\\d", "").equals("")){
                    parts.add(num);
                }
            }
            ArrayList<gear> twoNeighbors = new ArrayList<>();
            for(gear g: gears){
                ArrayList<numb> numbersAboveBelow = new ArrayList<>();
                for(numb n: parts){
                    if(n.line == g.line - 1 || n.line == g.line || n.line == g.line + 1){
                        numbersAboveBelow.add(n);
                    }

                }
                for(numb n: numbersAboveBelow){
                    if(n.position <= g.position + 1 && n.position + n.length >= g.position){
                        g.neighbors.add(n);
                    }
                }
                if (g.neighbors.size() == 2){
                    twoNeighbors.add(g);
                }
            }
            for (gear g: gears){
            }
            for (gear g: twoNeighbors){
                total += g.neighbors.get(0).value * g.neighbors.get(1).value;
            }

            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}