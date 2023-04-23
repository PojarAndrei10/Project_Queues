public class Client {
    private Integer ID;
    private Integer arrivalTime;
    private Integer serviceTime;
    public Client(Integer ID, Integer arrivalTime, Integer serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }
    public Integer getArrivalTime() {

        return arrivalTime;
    }
    public Integer getServiceTime() {

        return serviceTime;
    }
    public void setID(Integer ID) {

        this.ID = ID;
    }
    public void setArrivalTime(Integer arrivalTime) {

        this.arrivalTime = arrivalTime;
    }
    public void setServiceTime(Integer serviceTime) {

        this.serviceTime = serviceTime;
    }
    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", arrivalTime=" + arrivalTime +
                ", serviceTime=" + serviceTime +
                '}';
    }
}
