package BusinessLogic;

import Model.Client;
import Model.MarketQueue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scheduler {
    private CopyOnWriteArrayList<MarketQueue> marketQueue;
    private ArrayList<Thread> listThread;
    private Strategy s;
    public void selectarePoliticaDeSelectie(Policy selectionPolicy){
        if(selectionPolicy == Policy.SHORTEST_QUEUE)
            this.s = new ShortestQueueStrategy();
        if(selectionPolicy == Policy.SHORTEST_TIME)
            this.s = new TimeStrategy();
    }
    public Scheduler(int max, Policy sp)
    {
        int i = 0;
        this.marketQueue= new CopyOnWriteArrayList<>();
        this.listThread = new ArrayList<>();
        selectarePoliticaDeSelectie(sp);
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
        s.adaugareClient(this.marketQueue,c);
    }
    public Boolean isEmpty(){
        Iterator<MarketQueue> it=this.marketQueue.iterator();
        MarketQueue mq;
        while(it.hasNext())
        {
            mq=it.next();
            if(!mq.getCoada().isEmpty()) return false;
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
    public Strategy getS() {
        return s;
    }
    public void setS(Strategy s) {
        this.s = s;
    }
}