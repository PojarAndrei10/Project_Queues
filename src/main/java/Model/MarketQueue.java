package Model;

import Model.Client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
public class MarketQueue implements Runnable{
    private BlockingQueue <Client> coada;
    private AtomicInteger timeWaiting;
    private AtomicInteger numberClientsWaiting;
    private Boolean ok=false;
    public MarketQueue()
    {
        this.coada=new LinkedBlockingQueue<>();
        this.timeWaiting=new AtomicInteger(0);
        this.numberClientsWaiting=new AtomicInteger(0);
    }
    public BlockingQueue<Client> getCoada() {
        return coada;
    }
    public void setCoada(BlockingQueue<Client> coada) {
        this.coada = coada;
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
    public void adaugareClient(Client c)
    {
        this.getCoada().add(c);
        numberClientsWaiting.getAndIncrement();
        timeWaiting.getAndAdd(c.getServiceTime());
    }
    public void stergereClient(Client c)
    {
        this.getCoada().remove(c);
        numberClientsWaiting.getAndDecrement();
        timeWaiting.getAndAdd(-c.getServiceTime());
    }
    @Override
    public void run() {
        while(!ok)
        {
            Client c1;
            try{
                if(this.coada.size()>0) {
                    c1 = this.coada.element();
                    Thread.sleep(1000 );
                    if(c1.getServiceTime()>1)
                        c1.setServiceTime(c1.getServiceTime() - 1);
                    else
                    stergereClient(c1);
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
        for(Client i: this.getCoada())
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
        //FUNCTIA DE STOP
    }
}
