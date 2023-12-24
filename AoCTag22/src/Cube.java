public class Cube {
    int x1;
    int y1;
    int z1;
    int x2;
    int y2;
    int z2;
    int volume;

    public Cube(int x1, int y1, int z1, int x2, int y2, int z2){
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.volume = Math.abs((1 + x2 - x1) * (1 + y2 - y1) * (1 + z2 - z1));
    }

    @Override
    public String toString() {
        return "Cube{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", z1=" + z1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", z2=" + z2 +
                ", volume=" + volume +
                '}';
    }
}
