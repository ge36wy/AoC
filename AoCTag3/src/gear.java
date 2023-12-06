import java.util.ArrayList;

public class gear {
    int line;
    int position;
    ArrayList<numb> neighbors = new ArrayList<>();

    public gear(int line, int position){
        this.line = line;
        this.position = position;
    }
}
