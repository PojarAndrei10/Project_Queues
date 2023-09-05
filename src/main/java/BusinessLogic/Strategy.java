package BusinessLogic;

import Model.Client;
import Model.MarketQueue;

import java.util.concurrent.CopyOnWriteArrayList;
public interface Strategy {
    public void addClient(CopyOnWriteArrayList<MarketQueue> qm, Client c);
}
