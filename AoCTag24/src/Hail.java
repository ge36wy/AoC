public class Hail {
    long px;
    long py;
    long pz;
    long vx;
    long vy;
    long vz;
    double m;
    double b;

    public Hail(long px, long py, long pz, long vx, long vy, long vz){
        this.px = px;
        this.py = py;
        this.pz = pz;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.m = (double)vy / vx;
        this.b = py - m * px;
    }

    @Override
    public String toString() {
        return "Hail{" +
                "px=" + px +
                ", py=" + py +
                ", pz=" + pz +
                ", vx=" + vx +
                ", vy=" + vy +
                ", vz=" + vz +
                ", m=" + m +
                ", b=" + b +
                '}';
    }
}
