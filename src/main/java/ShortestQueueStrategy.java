import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShortestQueueStrategy implements Strategy{
    @Override
    public void adaugareClient(CopyOnWriteArrayList<MarketQueue> qm, Client c) {
        MarketQueue mq = new MarketQueue();
        int minim = Integer.MAX_VALUE;
        Iterator<MarketQueue> it = qm.iterator();
        while (it.hasNext()) {
            MarketQueue i = it.next();
            if (i.getTimeWaiting().get() < minim) {
                minim = i.getTimeWaiting().get();
                mq= i;
            }
        }
        mq.adaugareClient(c);
    }
}