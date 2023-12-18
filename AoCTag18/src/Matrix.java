public class Matrix {
    long x1;
    long x2;
    long y1;
    long y2;

    public Matrix(Coordinate c1, Coordinate c2){
        this.x1 = c1.x;
        this.x2 = c2.x;
        this.y1 = c1.y;
        this.y2 = c2.y;
    }

    public long determ(){
        return x1 * y2 - x2 * y1;
    }
}
