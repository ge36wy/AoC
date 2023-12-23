import java.util.ArrayList;

public class PointWithVal {
    Point point;
    int value;
    ArrayList<Point> previous;

    public PointWithVal(Point point, int value, ArrayList<Point> previous){
        this.point = point;
        this.value = value;
        this.previous = previous;
    }
}
