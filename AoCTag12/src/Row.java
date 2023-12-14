import java.util.ArrayList;

public class Row {
    ArrayList<Character> springs;
    ArrayList<Integer> numbers;
    int total;
    int missing;
    ArrayList<Integer> qmarks = new ArrayList<>();

    public Row(ArrayList<Character> springs, ArrayList<Integer> numbers){
        this.springs = springs;
        this.numbers = numbers;
        total = (int) numbers.stream().mapToDouble(a -> a).sum();
        missing = total;
        for(char c: springs){
            if(c == '#') missing--;
        }
        for(int i = 0; i < springs.size(); i++){
            if(springs.get(i) == '?') qmarks.add(i);
        }
    }


}
