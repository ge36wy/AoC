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
        setBM();
    }

    public void setBM(){
        this.m = (double)vy / vx;
        this.b = py - m * px;
    }

    public void setVx(long vx) {
        this.vx = vx;
        setBM();
    }

    public void setVy(long vy) {
        this.vy = vy;
        setBM();
    }

    public void setVz(long vz) {
        this.vz = vz;
        setBM();
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
