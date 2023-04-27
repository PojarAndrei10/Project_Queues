package BusinessLogic;

import BusinessLogic.Strategy;
import Model.Client;
import Model.MarketQueue;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void adaugareClient(CopyOnWriteArrayList<MarketQueue> qm, Client c) {
        MarketQueue mq = new MarketQueue();
        int minim = Integer.MAX_VALUE;
        Iterator<MarketQueue> it = qm.iterator();
        while (it.hasNext()) {
            MarketQueue i = it.next();
            if (i.getNumberClientsWaiting().get() < minim) {
                minim = i.getNumberClientsWaiting().get();
                mq= i;
            }
        }
        if(mq.getTimeWaiting().intValue()>1)
        SimulationManagerMain.timpAsteptareTotal+=mq.getTimeWaiting().intValue()-1;
        mq.adaugareClient(c);
    }
}