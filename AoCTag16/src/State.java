public class State {
    Direction direction;
    int x;
    int y;

    public State(Direction direction, int x, int y){
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        State other = (State) obj;
        return direction == other.direction && x == other.x && y == other.y;
    }
}
