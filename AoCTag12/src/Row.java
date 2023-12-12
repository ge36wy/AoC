import java.util.ArrayList;

public class Row {
    ArrayList<Character> springs;
    ArrayList<Integer> numbers;
    int total;
    int missing;
    ArrayList<Integer> qmarks = new ArrayList<>();
    ArrayList<ArrayList<Integer>> comb = new ArrayList<>();

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

    public void getAllCombinations(){
        combinationUtil(qmarks, qmarks.size(), missing, 0, new int[missing], 0);
    }
    void combinationUtil(ArrayList<Integer> arr, int n, int r, int index,
                                int[] data, int i)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j=0; j<r; j++)
                list.add(data[j]);
            comb.add(list);
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= n)
            return;

        // current is included, put next at next location
        data[index] = arr.get(i);
        combinationUtil(arr, n, r, index+1, data, i+1);

        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        combinationUtil(arr, n, r, index, data, i+1);
    }

    public ArrayList<Character> replace(ArrayList<Integer> nums){
        ArrayList<Character> replaced = new ArrayList<>(springs);
        for(Integer i: nums){
            replaced.set(i, '#');
        }
        return replaced;
    }

    public boolean correct(){
        ArrayList<Integer> groups = new ArrayList<>();
        int c = 0;
        boolean in = false;
        for (Character spring : springs) {
            if (in) {
                if (spring == '#') {
                    c++;
                } else {
                    groups.add(c);
                    c = 0;
                    in = false;
                }
            } else {
                if (spring == '#') {
                    c++;
                    in = true;
                }
            }
        }
        if(c > 0) groups.add(c);
        return groups.equals(numbers);
    }
}
