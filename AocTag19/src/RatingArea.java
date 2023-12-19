public class RatingArea {
    int minX;
    int maxX;
    int minM;
    int maxM;
    int minA;
    int maxA;
    int minS;
    int maxS;

    public RatingArea(int minX, int maxX, int minM, int maxM, int minA, int maxA, int minS, int maxS){
        this.minX = minX;
        this.maxX = maxX;
        this.minM = minM;
        this.maxM = maxM;
        this.minA = minA;
        this.maxA = maxA;
        this.minS = minS;
        this.maxS = maxS;
    }

    public long amount(){
        return (long) (maxX + 1 - minX) * (maxM + 1 - minM) * (maxA + 1 - minA) * (maxS + 1 - minS);
    }
}
