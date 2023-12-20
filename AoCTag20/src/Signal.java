public class Signal {
    Module sender;
    Module receiver;
    boolean isHigh;

    public Signal(Module sender, Module receiver, boolean isHigh){
        this.sender = sender;
        this.receiver = receiver;
        this.isHigh = isHigh;
    }
}
