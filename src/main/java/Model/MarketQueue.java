package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
public class MarketQueue implements Runnable{
    private BlockingQueue <Client> queue;
    private AtomicInteger timeWaiting;
    private AtomicInteger numberClientsWaiting;
    private Boolean ok=false;
    public MarketQueue()
    {
        this.queue=new LinkedBlockingQueue<>();
        this.timeWaiting=new AtomicInteger(0);
        this.numberClientsWaiting=new AtomicInteger(0);
    }
    public BlockingQueue<Client> getQueue() {
        return queue;
    }
    public void setQueue(BlockingQueue<Client> coada) {
        this.queue = coada;
    }
    public AtomicInteger getTimeWaiting() {
        return timeWaiting;
    }
    public void setTimeWaiting(AtomicInteger timeWaiting) {
        this.timeWaiting = timeWaiting;
    }
    public AtomicInteger getNumberClientsWaiting() {
        return numberClientsWaiting;
    }
    public void setNumberClientsWaiting(AtomicInteger numberClientsWaiting) {
        this.numberClientsWaiting = numberClientsWaiting;
    }
    public void addClients(Client c)
    {
        this.getQueue().add(c);
        numberClientsWaiting.getAndIncrement();
        timeWaiting.getAndAdd(c.getServiceTime());
    }
    public void deleteClients(Client c)
    {
        this.getQueue().remove(c);
        numberClientsWaiting.getAndDecrement();
        timeWaiting.getAndAdd(-c.getServiceTime());
    }
    @Override
    public void run() {
        while(!ok)
        {
            Client c1;
            try{
                if(this.queue.size()>0) {
                    c1 = this.queue.element();
                    Thread.sleep(1000 );
                    if(c1.getServiceTime()>1)
                        c1.setServiceTime(c1.getServiceTime() - 1);
                    else
                    deleteClients(c1);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public String toString(int k) {
        StringBuilder sb=new StringBuilder("Coada : "+k+" :");
        sb.append("Timp asteptare :").append(timeWaiting.toString()).append(" ");
        sb.append("Numar de clienti care asteapta :").append(numberClientsWaiting.toString()).append(" ->");
        for(Client i: this.getQueue())
            sb.append(" ").append(i);
        return sb.toString();
    }
    public void setOk(boolean b) {
        this.ok=ok;
    }
    public Boolean getOk() {
        return ok;
    }
    public void stop()
    {
        //STOP
    }
}
