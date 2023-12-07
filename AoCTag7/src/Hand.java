import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hand {
    ArrayList<Integer> countsNoJ;
    String hand;
    String noJ;
    int val;
    int jokers;

    public Hand(ArrayList<Integer> countsNoJ, String hand, int val){
        this.countsNoJ = countsNoJ;
        this.hand = hand;
        this.val = val;
        this.noJ = this.hand.replace("J", "");
        jokers = this.hand.length() - noJ.length();
    }

    public int charToInt(char c){
        if(c - '0' <= 9) return c - '0';
        if(c == 'T') return 10;
        if(c == 'J') return 1;
        if(c == 'Q') return 12;
        if(c == 'K') return 13;
        if(c == 'A') return 14;
        System.err.println("Achtung");
        return 0;
    }

    public int compare(Hand h2){
        for(int i = 0; i < Math.min(this.countsNoJ.size(), h2.countsNoJ.size()); i++){
            if(this.countsNoJ.get(i) > h2.countsNoJ.get(i)) return 1;
            if(this.countsNoJ.get(i) < h2.countsNoJ.get(i)) return -1;
        }
        for (int i = 0; i < 5; i++){
            if(this.hand.charAt(i) != h2.hand.charAt(i)){
                if(charToInt(this.hand.charAt(i)) > charToInt(h2.hand.charAt(i))) return 1;
                return -1;
            }
        }
        System.err.println("Achtung");
        return 0;
    }
}
