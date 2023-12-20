public class VisitedState{
    State state;
    int cost;
    int remain;


    public VisitedState(State state, int cost){
        this.state = state;
        this.cost = cost;
        remain = this.state.x + this.state.y;
    }
}
