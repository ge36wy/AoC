import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
    static HashMap<String, ArrayList<String>> layout = new HashMap<>();
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(": ");
                String[] right = s[1].split("\s");
                if(!layout.containsKey(s[0])) layout.put(s[0], new ArrayList<>());
                for(String s1: right){
                    if(!layout.containsKey(s1)) layout.put(s1, new ArrayList<>());
                    layout.get(s[0]).add(s1);
                    layout.get(s1).add(s[0]);
                }
            }
            /*for(String s: layout.keySet()){
                for (String t: layout.get(s)){
                    System.out.println(s + " -> " + t + ";");
                }
            }*/
            //plot to figure these out
            layout.get("ztc").remove("ttv");
            layout.get("ttv").remove("ztc");
            layout.get("bdj").remove("vfh");
            layout.get("vfh").remove("bdj");
            layout.get("rpd").remove("bnv");
            layout.get("bnv").remove("rpd");
            System.out.println(isConnected(layout));
            int first = getConnected("jkn", layout);
            int second = getConnected("vbr", layout);
            System.out.println(first * second);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getConnected(String start, HashMap<String, ArrayList<String>> layout){
        HashSet<String> visited = new HashSet<>();
        visited.add(start);
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add(start);
        while(!queue.isEmpty()){
            String current = queue.poll();
            ArrayList<String> following = layout.get(current);
            for(String next: following){
                if(!visited.contains(next)){
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
        return visited.size();
    }


        public static boolean isConnected(HashMap<String, ArrayList<String>> layout){
        String start = layout.keySet().iterator().next();
        HashSet<String> visited = new HashSet<>();
        visited.add(start);
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add(start);
        while(!queue.isEmpty()){
            String current = queue.poll();
            ArrayList<String> following = layout.get(current);
            for(String next: following){
                if(!visited.contains(next)){
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
        return visited.size() == layout.size();
    }
}