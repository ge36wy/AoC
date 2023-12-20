public class State{
    int id;
    int x;
    int y;
    int heatloss;
    Directions direction;


    public State(int id, int x, int y, int heatloss, Directions direction){
        this.id = id;
        this.x = x;
        this.y = y;
        this.heatloss = heatloss;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        State other = (State) obj;
        return this.id == other.id && direction == other.direction;
    }

    @Override
    public int hashCode() {
        int i = 13 * id;
        if(direction == Directions.UPDOWN) i += 7;
        if(direction == Directions.LEFTRIGHT) i += 14;
        return i;
    }
}
