import java.util.concurrent.CopyOnWriteArrayList;

public interface Strategy {
    public void adaugareClient(CopyOnWriteArrayList<MarketQueue> qm, Client c);
}
