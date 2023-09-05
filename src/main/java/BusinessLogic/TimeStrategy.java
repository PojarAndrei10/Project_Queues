package BusinessLogic;

import Model.Client;
import Model.MarketQueue;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
public class TimeStrategy implements Strategy {
    @Override
    public void addClient(CopyOnWriteArrayList<MarketQueue> qm, Client c) {
        MarketQueue mq = new MarketQueue();
        int min= Integer.MAX_VALUE;
        Iterator<MarketQueue> it = qm.iterator();
        while (it.hasNext()) {
            MarketQueue i = it.next();
            if (i.getTimeWaiting().get() < min) {
                min = i.getTimeWaiting().get();
                mq= i;
            }
        }
        if(min>1)
        SimulationManagerMain.totalWaitingTime+=min-1;
        mq.addClients(c);
    }
}