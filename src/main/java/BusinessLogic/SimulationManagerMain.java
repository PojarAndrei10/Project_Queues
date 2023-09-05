package BusinessLogic;

import GUI.InterfataSimulare;
import GUI.MarketWindow;
import Model.Client;
import Model.MarketQueue;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimulationManagerMain implements Runnable{
    private Integer numberClients;
    private Integer numberQueue;
    private Integer simulationTime;
    private Integer minArrivalTime;
    private Integer maxArrivalTime;
    private Integer minServingTime;
    private Integer maxServingTime;
    private CopyOnWriteArrayList<Client> clients;
    private Scheduler scheduler;
    private Policy distributionPolicy;
    private FileWriter w;
    private File fileTxt;
    public static int totalWaitingTime;
    public void generateRandomClient(){
        int i = 0;
        Random random = new Random();
        Client client;
        Integer k,timeToArrive,timeToServe;
        System.out.println(minServingTime);
        System.out.println(maxServingTime);
        while (i < numberClients) {
            k = i+1;
            timeToArrive = random.nextInt(minArrivalTime,maxArrivalTime+1);
            timeToServe= random.nextInt(minServingTime,maxServingTime+1);
            client = new Client(k,timeToArrive,timeToServe);
            clients.add(i,client);
            i++;
        }
        Collections.sort(clients, new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return Integer.compare(c1.getArrivalTime(), c2.getArrivalTime());
            }
        });
    }
    public Double averageWaitingTime(CopyOnWriteArrayList<MarketQueue> market){
        Integer numberOfWaitingC = 0;
        Double waitingTime = 0.0;
        boolean ok=false;
        for(MarketQueue qm : market){
            if(qm.getQueue().size()>0)
            {
                waitingTime=waitingTime+qm.getTimeWaiting().doubleValue();
                numberOfWaitingC=numberOfWaitingC+qm.getNumberClientsWaiting().intValue();
            }
        }
        if(numberOfWaitingC!=0)
            return (waitingTime/numberOfWaitingC);
        return 0.0;
    }
    public Integer numberOfWaitingClients(CopyOnWriteArrayList<MarketQueue> market){
        Iterator<MarketQueue> it = market.iterator();
        Integer numberOfWaitingC = 0;
        boolean ok=false;
        while (it.hasNext()) {
            MarketQueue mq = it.next();
            if (mq.getQueue().size() > 0) {
                numberOfWaitingC =numberOfWaitingC+ mq.getNumberClientsWaiting().intValue();
            }
        }
        return numberOfWaitingC;
    }
    public void stateMarket(MarketWindow marketWindow, CopyOnWriteArrayList<MarketQueue> market, int i,
                            int x, int y) throws IOException {
        StringBuilder sb = new StringBuilder();
        Integer k = 1;
        sb.append("Timer: ").append(i).append("\n");
        marketWindow.settingL("Timer: " + i, x, y);
        sb.append("Clientii in asteptare :").append(clients).append("\n");
        sb.append("\n");
        int pixels=50;
        for (MarketQueue j : market) {
            x=x+pixels;
            sb.append(j.toString(k)).append("\n");
            marketWindow.settingL(j.toString(k), x, y);
            k++;
        }
        sb.append("\n");
        w.write(sb.toString());
    }
    @Override
    public void run() {
        Integer maximumNumberOfWaitingClients = 0;
        Integer peakTime = -1;
        Integer currentTime = 0;
        MarketWindow m = new MarketWindow();
        m.setVisible(true);
        Double averageWaitingTime = 0.0;
        Double averageServingTime = 0.0;
        int x;
        int pixel=50;
        Client c;
        SimulationManagerMain.totalWaitingTime=0;

        while(currentTime<simulationTime &&  (!clients.isEmpty() || !scheduler.isEmpty())  )
        {
            while(clients.size()>0 && clients.get(0).getArrivalTime()==currentTime)
            {
                c= clients.get(0);
                scheduler.dispatchTask(c);
                averageServingTime =averageServingTime + clients.get(0).getServiceTime();
                clients.remove(0);
            }
            m.getInterfacePanel().removeAll();
            try {
                x = pixel;
                final int y = pixel;

                stateMarket(m,scheduler.getMarketQueue(),currentTime,x,y);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            m.getInterfacePanel().revalidate();
            m.getInterfacePanel().repaint();

            averageWaitingTime =averageWaitingTime+ averageWaitingTime(scheduler.getMarketQueue());
            Integer itNrClients ;
            itNrClients = numberOfWaitingClients(scheduler.getMarketQueue());

            if(itNrClients>maximumNumberOfWaitingClients)
            {
                maximumNumberOfWaitingClients = itNrClients;
                peakTime = currentTime; //peak hour
            }
            currentTime++;
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        x=pixel;
        if(currentTime<=simulationTime)
        try {
            stateMarket(m,scheduler.getMarketQueue(),currentTime,x,x);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            m.getInterfacePanel().removeAll();
            String result = "FINAL DE SIMULARE  Rezultate:";
            result =result+ "Durata medie a serviciului: " + String.format("%.2f",averageServingTime/numberClients)+"\n";
            result =result+ "Timp mediu de așteptare: " + String.format("%.2f",SimulationManagerMain.totalWaitingTime*1.0/numberClients)+"\n";
            result =result+ "Ora de varf: "+peakTime;
            w.write(result);

            m.settingL2("FINAL DE SIMULARE - Mai jos putem vedea rezultatele simularii:",100,450);
            m.settingL2("Durata medie a serviciului: " + String.format("%.2f",averageServingTime/numberClients),165,450);
            m.settingL2("Timp mediu de așteptare: " + String.format("%.2f",SimulationManagerMain.totalWaitingTime*1.0/numberClients),190,450);
            m.settingL2("Ora de varf: "+peakTime,215,450);

            m.getInterfacePanel().revalidate();
            m.getInterfacePanel().repaint();
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
    public SimulationManagerMain(InterfataSimulare simulationInterface) {
        this.clients = new CopyOnWriteArrayList<>();
        this.numberClients = simulationInterface.getChoiceOfClientsNumber();
        this.numberQueue = simulationInterface.getChoiceOfQueueNumber();
        this.simulationTime= simulationInterface.getChoiceMaxDurationSimulation();
        this.minArrivalTime= simulationInterface.getChoiceMinArrivalTime();
        this.maxArrivalTime = simulationInterface.getChoiceMaxArrivalTime();
        this.minServingTime = simulationInterface.getChoiceMinServingTime();
        this.maxServingTime = simulationInterface.getChoiceMaxServingTime();
        this.distributionPolicy = simulationInterface.getSelectionPolicy();
        generateRandomClient();
        this.scheduler = new Scheduler(numberQueue,distributionPolicy);
        fileTxt = new File("log.txt");
        try{
            w = new FileWriter(fileTxt);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        InterfataSimulare simulationInterface = new InterfataSimulare();
        simulationInterface.setVisible(true);
    }
}