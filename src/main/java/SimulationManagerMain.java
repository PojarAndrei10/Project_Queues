import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimulationManagerMain implements Runnable{
    private Integer numarClienti;
    private Integer numarCozi;
    private Integer timpSimulare;
    private Integer timpMinSosire;
    private Integer timpMaxSosire;
    private Integer timpMinServire;
    private Integer timpMaxServire;
    private CopyOnWriteArrayList<Client> clienti;
    private Scheduler scheduler;
   private Policy politicaDistribuireClienti;
    private FileWriter w;
    private File fileTxt;
    public static int timpAsteptareTotal;
    public void generateRandomClient(){
        int i = 0;
        Random random = new Random();
        Client client;
        Integer k,oraSosire,oraServire;
        System.out.println(timpMinServire);
        System.out.println(timpMaxServire);
        while (i < numarClienti) {
            k = i+1;
            oraSosire = random.nextInt(timpMinSosire,timpMaxSosire+1); //ca sa faca interval inchis
            oraServire= random.nextInt(timpMinServire,timpMaxServire+1);
            client = new Client(k,oraSosire,oraServire);
            clienti.add(i,client);
            i++;
        }
        Collections.sort(clienti, new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return Integer.compare(c1.getArrivalTime(), c2.getArrivalTime());
            }
        });
    }
    public Double timpMediuAsteptare(CopyOnWriteArrayList<MarketQueue> market){
        Integer numarClientiInAsteptare = 0;
        Double timpAsteptare = 0.0;
        boolean ok=false;
        for(MarketQueue qm : market){
            if(qm.getCoada().size()>0)
            {
                timpAsteptare=timpAsteptare+qm.getTimeWaiting().doubleValue();
                numarClientiInAsteptare=numarClientiInAsteptare+qm.getNumberClientsWaiting().intValue();
            }
        }
        if(numarClientiInAsteptare!=0)
            return (timpAsteptare/numarClientiInAsteptare);
        return 0.0;
    }
    public Integer numarClientiInAsteptare(CopyOnWriteArrayList<MarketQueue> market){
        Iterator<MarketQueue> it = market.iterator();
        Integer numarulClientilorInAsteptare = 0;
        boolean ok=false;
        while (it.hasNext()) {
            MarketQueue mq = it.next();
            if (mq.getCoada().size() > 0) {
                numarulClientilorInAsteptare =numarulClientilorInAsteptare+ mq.getNumberClientsWaiting().intValue();
            }
        }
        return numarulClientilorInAsteptare;
    }
    public void stareMarket(MarketWindow marketWindow,CopyOnWriteArrayList<MarketQueue> market, int i,
                            int x, int y) throws IOException {
        StringBuilder sb = new StringBuilder();
        Integer k = 1;
        sb.append("Timer: ").append(i).append("\n");
        marketWindow.settingL("Timer: " + i, x, y);
        sb.append("Clientii in asteptare :").append(clienti).append("\n");
        sb.append("\n");
        int pixeli=50;
        for (MarketQueue j : market) {
            x=x+pixeli;
            sb.append(j.toString(k)).append("\n");
            marketWindow.settingL(j.toString(k), x, y);
            k++;
        }
        sb.append("\n");
        w.write(sb.toString());
    }
    @Override
    public void run() {
        Integer numarMaximClientiInAsteptare = 0;
        Integer oraDeVarf = -1;
        Integer timpCurent = 0;
        MarketWindow m = new MarketWindow();
        m.setVisible(true);
        Double timpMediuAsteptare = 0.0;
        Double timpMediuServire = 0.0;
        int x;
        int pixel=50;
        Client c;
        SimulationManagerMain.timpAsteptareTotal=0;
        while(timpCurent<timpSimulare &&  (!clienti.isEmpty() || !scheduler.isEmpty())  )
        {
            while(clienti.size()>0 && clienti.get(0).getArrivalTime()==timpCurent)
            {
                c= clienti.get(0);
                scheduler.dispatchTask(c);
                timpMediuServire =timpMediuServire+ clienti.get(0).getServiceTime();
                clienti.remove(0);
            }
            m.getInterfataPanel().removeAll();
            try {
                x = pixel;
                final int y = pixel;

                stareMarket(m,scheduler.getMarketQueue(),timpCurent,x,y);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            m.getInterfataPanel().revalidate();
            m.getInterfataPanel().repaint();

            timpMediuAsteptare =timpMediuAsteptare+ timpMediuAsteptare(scheduler.getMarketQueue());
            Integer itNumarClienti ;
            itNumarClienti = numarClientiInAsteptare(scheduler.getMarketQueue());

            if(itNumarClienti>numarMaximClientiInAsteptare)
            {
                numarMaximClientiInAsteptare = itNumarClienti;
                oraDeVarf = timpCurent; //calculam peak hour
            }
            timpCurent++;
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            //aici facem interfata pentru rezultatele finale
            m.getInterfataPanel().removeAll();
            String rezultate = "FINAL DE SIMULARE  Rezultate:";
            rezultate =rezultate+ "Durata medie a serviciului: " + String.format("%.2f",timpMediuServire/numarClienti)+"\n";
            rezultate =rezultate+ "Timp mediu de așteptare: " + String.format("%.2f",SimulationManagerMain.timpAsteptareTotal*1.0/numarClienti)+"\n";
            rezultate =rezultate+ "Ora de varf: "+oraDeVarf;
            w.write(rezultate);
            // setare etichete pentru rezultatele simulării pe panoul de interfață
            m.settingL2("FINAL DE SIMULARE - Mai jos putem vedea rezultatele simularii:",100,450);
            m.settingL2("Durata medie a serviciului: " + String.format("%.2f",timpMediuServire/numarClienti),165,450);
            m.settingL2("Timp mediu de așteptare: " + String.format("%.2f",SimulationManagerMain.timpAsteptareTotal*1.0/numarClienti),190,450);
            m.settingL2("Ora de varf: "+oraDeVarf,215,450);
            // revalidare și redesenare panou de interfață
            m.getInterfataPanel().revalidate();
            m.getInterfataPanel().repaint();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        CopyOnWriteArrayList<MarketQueue> mq = scheduler.getMarketQueue();
        int index = 0;
        while(index < mq.size())
        {
            MarketQueue i = mq.get(index);
            i.setOk(true);
            i.stop();
            index++;
        }

        try {
            w.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public SimulationManagerMain(InterfataSimulare interfataSimulare) {
        this.clienti = new CopyOnWriteArrayList<>();

        this.numarClienti = interfataSimulare.getAlegereNumarClienti();
        this.numarCozi = interfataSimulare.getAlegereNumarCozi();
        this.timpSimulare= interfataSimulare.getAlegereDurataMaximaSimulare();
        this.timpMinSosire= interfataSimulare.getAlegereTimpMinSosire();
        this.timpMaxSosire = interfataSimulare.getAlegereTimpMaxSosire();
        this.timpMinServire = interfataSimulare.getAlegereTimpMinServire();
        this.timpMaxServire = interfataSimulare.getAlegereTimpMaxServire();
        this.politicaDistribuireClienti = interfataSimulare.getPoliticaDeSelectie();
        generateRandomClient();
        this.scheduler = new Scheduler(numarCozi,politicaDistribuireClienti);
        fileTxt = new File("log.txt");
        try{
            //w=new FileReader(fileTxt);
            w = new FileWriter(fileTxt);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        InterfataSimulare interfataSimulare = new InterfataSimulare();
        interfataSimulare.setVisible(true);
    }
}