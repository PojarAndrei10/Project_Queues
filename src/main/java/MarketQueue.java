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
                    Thread.sleep(1000 * c1.getServiceTime());
                    stergereClient(c1);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public String toString(int k) {
        String rez=k+" ";
        rez=rez+"Timp asteptare : "+timeWaiting.toString()+" ";
        rez=rez+"Numar de clienti care asteapta :"+numberClientsWaiting.toString()+" :";
        for(Client i:this.getCoada())
            rez=rez+ i+" ";
        return rez;
    }

}
