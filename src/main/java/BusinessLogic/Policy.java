package BusinessLogic;
public enum Policy{
    SHORTEST_TIME ("Shortest time"),
    SHORTEST_QUEUE ("Shortest queue");
    private final String name;
    Policy(String s){
        name = s;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
       return this.name;
    }
}