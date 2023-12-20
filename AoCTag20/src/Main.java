import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static ArrayList<Module> modules = new ArrayList<>();
    static ArrayList<Conjunction> conjunctions = new ArrayList<>();
    static ArrayList<String> starts = new ArrayList<>();
    static int counter = 0;
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("input.txt");
        try {
            assert input != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            Module broadcaster = null;
            while ((line = reader.readLine()) != null) {
                String[] s = line.split(" -> ");
                String[] follow = s[1].split(", ");
                if(line.startsWith("broadcaster")){
                    starts.addAll(List.of(follow));
                    broadcaster = new Module("broadcaster", new ArrayList<>(List.of(follow)));
                    modules.add(broadcaster);
                    continue;
                }
                    if(line.charAt(0) == '%') modules.add(new FlipFlop(s[0].substring(1), false, new ArrayList<>(List.of(follow))));
                else{
                    Conjunction c = new Conjunction(s[0].substring(1), new ArrayList<>(List.of(follow)));
                    modules.add(c);
                    conjunctions.add(c);
                }
            }
            for(Conjunction conjunction:conjunctions){
                for (Module module: modules){
                    if(module.followers.contains(conjunction.name)){
                        conjunction.lastPulse.put(module.name, false);
                    }
                }
            }
            while (counter < 10000) {
                counter++;
                ArrayList<Signal> currentSignals = new ArrayList<>();
                for (String s : starts) {
                    Module rec = modules.stream().filter(x -> x.name.equals(s)).findFirst().orElse(null);
                    currentSignals.add(new Signal(broadcaster, rec, false));
                }
                while (!currentSignals.isEmpty()) {
                    Signal current = currentSignals.get(0);
                    currentSignals.remove(0);
                    currentSignals.addAll(sendSignal(current));
                }
            }
            //System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public static ArrayList<Signal> sendSignal(Signal current){
        //alle vier Vorgänger, dann kgV
        if (current.sender.name.equals("fk") && current.isHigh) {
            System.out.println(counter);
        }
        ArrayList<Signal> signals = new ArrayList<>();
        FlipFlop dummyFlop = new FlipFlop("", false, null);
        Conjunction dummyCon = new Conjunction("", null);
        if(current.receiver.getClass() == dummyFlop.getClass()){
            if(current.isHigh) return signals;
            int pos = modules.indexOf(current.receiver);
            FlipFlop f = (FlipFlop)modules.get(pos);
            f.turnedOn = !f.turnedOn;
            modules.set(pos, f);
            for(String s: f.followers){
                Module r = modules.stream().filter(x -> x.name.equals(s)).findFirst().orElse(null);
                signals.add(new Signal(current.receiver, r, f.turnedOn));
            }
            return signals;
        } else if (current.receiver.getClass() == dummyCon.getClass()) {
            int pos = modules.indexOf(current.receiver);
            Conjunction c = (Conjunction) modules.get(pos);
            c.lastPulse.put(current.sender.name, current.isHigh);
            modules.set(pos, c);
            boolean high = !c.lastPulse.values().stream().allMatch(x -> x);
            for(String s: c.followers){
                if(modules.stream().anyMatch(x -> x.name.equals(s))) {
                    Module r = modules.stream().filter(x -> x.name.equals(s)).findFirst().orElse(null);
                    signals.add(new Signal(current.receiver, r, high));
                }
            }
            return signals;
        }
        throw new RuntimeException();
   }
}