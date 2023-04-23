public enum Policy{
    //cele doua posibilitati de selection policy
    SHORTEST_QUEUE ("Shortest queue"),
    SHORTEST_TIME ("Shortest time");
    private final String nume;
    Policy(String s){
        nume = s;
    }
    public String getNume() {
        return nume;
    }
    @Override
    public String toString() {
       return this.nume;
    }
}