import java.util.ArrayList;

public class FlipFlop extends Module{
    boolean turnedOn;

    public FlipFlop(String name, boolean turnedOn, ArrayList<String> followers){
        super(name, followers);
        this.turnedOn = turnedOn;
    }
}
