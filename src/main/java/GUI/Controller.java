package GUI;

import BusinessLogic.SimulationManagerMain;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ChangeListener, ActionListener {
    private InterfataSimulare simulationInterface;
    public boolean isValid(){
        boolean ok=true;
        Integer numberClients;
        numberClients=simulationInterface.getChoiceOfClientsNumber();
        Integer numberQueue;
        numberQueue=simulationInterface.getChoiceOfQueueNumber();
        Integer simulationDuration;
        simulationDuration=simulationInterface.getChoiceMaxDurationSimulation();
        Integer minArrivalTime;
        minArrivalTime=simulationInterface.getChoiceMinArrivalTime();
        Integer maxArrivalTime;
        maxArrivalTime=simulationInterface.getChoiceMaxArrivalTime();
        Integer minServingTime;
        minServingTime=simulationInterface.getChoiceMinServingTime();
        Integer maxServingTime;
        maxServingTime=simulationInterface.getChoiceMaxServingTime();
        if(numberClients<=0) return false;
        if(numberQueue<=0) return false;
        if(simulationDuration<=0) return false;
        if(minArrivalTime<=0) return false;
        if(maxArrivalTime<=0) return false;
        if(minServingTime<=0) return false;
        if(maxServingTime<=0) return false;
        if(minArrivalTime>maxArrivalTime) return false;
        if(minServingTime>maxServingTime) return false;
        if(minArrivalTime>simulationDuration) return false;
        if(maxArrivalTime>simulationDuration) return false;
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        simulationInterface.setVisible(false);
        SimulationManagerMain sim = new SimulationManagerMain(simulationInterface);
        Thread thread = new Thread(sim);
        thread.start();
    }
    public Controller(InterfataSimulare simulationInterface){
        this.simulationInterface=simulationInterface;
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if (isValid()) {
            simulationInterface.startSimulation.setEnabled(true);
        } else {
            simulationInterface.startSimulation.setEnabled(false);
        }
    }
}

