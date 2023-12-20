import java.util.ArrayList;
import java.util.HashMap;

public class Conjunction extends Module{
    HashMap<String, Boolean> lastPulse = new HashMap<>();

    public Conjunction(String name, ArrayList<String> followers){
        super(name, followers);
    }
}
