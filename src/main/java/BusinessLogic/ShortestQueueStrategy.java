package BusinessLogic;

import Model.Client;
import Model.MarketQueue;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addClient(CopyOnWriteArrayList<MarketQueue> qm, Client c) {
        MarketQueue mq = new MarketQueue();
        int min = Integer.MAX_VALUE;
        Iterator<MarketQueue> it = qm.iterator();
        while (it.hasNext()) {
            MarketQueue i = it.next();
            if (i.getNumberClientsWaiting().get() < min) {
                min = i.getNumberClientsWaiting().get();
                mq= i;
            }
        }
        if(mq.getTimeWaiting().intValue()>1)
        SimulationManagerMain.totalWaitingTime+=mq.getTimeWaiting().intValue()-1;
        mq.addClients(c);
    }
}