public class Rating {
    int x;
    int m;
    int a;
    int s;

    public Rating(int x, int m, int a, int s){
        this.x =x;
        this.m =m;
        this.a =a;
        this.s =s;
    }

    public int sum(){
        return x + m + a + s;
    }
}
