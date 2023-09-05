package BusinessLogic;

import Model.Client;
import Model.MarketQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
public class Scheduler {
    private CopyOnWriteArrayList<MarketQueue> marketQueue;
    private ArrayList<Thread> listThread;
    private Strategy strategy;
    public void selectionPolicy(Policy selectionPolicy){
        if(selectionPolicy == Policy.SHORTEST_QUEUE)
            this.strategy = new ShortestQueueStrategy();
        if(selectionPolicy == Policy.SHORTEST_TIME)
            this.strategy = new TimeStrategy();
    }
    public Scheduler(int max, Policy sp)
    {
        int i = 0;
        this.marketQueue= new CopyOnWriteArrayList<>();
        this.listThread = new ArrayList<>();
        selectionPolicy(sp);
        MarketQueue qm;
        while(i < max) {
            qm = new MarketQueue();
            marketQueue.add(i, qm);
            listThread.add(i, new Thread(qm));
            i++;
        }
        for (Thread t: listThread) {
            t.start();
        }
    }
    public void dispatchTask(Client c)
    {
        strategy.addClient(this.marketQueue,c);
    }
    public Boolean isEmpty(){
        Iterator<MarketQueue> it=this.marketQueue.iterator();
        MarketQueue mq;
        while(it.hasNext())
        {
            mq=it.next();
            if(!mq.getQueue().isEmpty()) return false;
        }
        return true;
    }
    public CopyOnWriteArrayList<MarketQueue> getMarketQueue() {
        return marketQueue;
    }
    public void setMarketQueue(CopyOnWriteArrayList<MarketQueue> marketQueue) {
        this.marketQueue = marketQueue;
    }
    public ArrayList<Thread> getListThread() {
        return listThread;
    }
    public void setListThread(ArrayList<Thread> listThread) {
        this.listThread = listThread;
    }
    public Strategy getStrategy() {
        return strategy;
    }
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}