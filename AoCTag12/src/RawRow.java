import java.util.ArrayList;

public class RawRow {
    ArrayList<Character> springs;
    ArrayList<Integer> numbers;

    public RawRow(ArrayList<Character> springs, ArrayList<Integer> numbers){
        this.springs = springs;
        this.numbers = numbers;
    }

    @Override
    public int hashCode(){
        int result = 5;
        result = 13 * result + springs.hashCode();
        result = 17 * result + numbers.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object r2){
        if(getClass() != r2.getClass()) return false;
        RawRow r = (RawRow) r2;
        return this.springs.equals(r.springs) && this.numbers.equals(r.numbers);
    }
}
