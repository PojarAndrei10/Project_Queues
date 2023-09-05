package GUI;

import BusinessLogic.SimulationManagerMain;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ChangeListener, ActionListener {
    private InterfataSimulare interfataSimulare;
    public boolean isValid(){
        boolean ok=true;
        Integer numarClienti;
        numarClienti=interfataSimulare.getAlegereNumarClienti();
        Integer numarCozi;
        numarCozi=interfataSimulare.getAlegereNumarCozi();
        Integer durataSimulare;
        durataSimulare=interfataSimulare.getAlegereDurataMaximaSimulare();
        Integer timpMinSosire;
        timpMinSosire=interfataSimulare.getAlegereTimpMinSosire();
        Integer timpMaxSosire;
        timpMaxSosire=interfataSimulare.getAlegereTimpMaxSosire();
        Integer timpMinServire;
        timpMinServire=interfataSimulare.getAlegereTimpMinServire();
        Integer timpMaxServire;
        timpMaxServire=interfataSimulare.getAlegereTimpMaxServire();
        if(numarClienti<=0) return false;
        if(numarCozi<=0) return false;
        if(durataSimulare<=0) return false;
        if(timpMinSosire<=0) return false;
        if(timpMaxSosire<=0) return false;
        if(timpMinServire<=0) return false;
        if(timpMaxServire<=0) return false;
        if(timpMinSosire>timpMaxSosire) return false;
        if(timpMinServire>timpMaxServire) return false;
        if(timpMinSosire>durataSimulare) return false;
        if(timpMaxSosire>durataSimulare) return false;
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        interfataSimulare.setVisible(false);
        SimulationManagerMain sim = new SimulationManagerMain(interfataSimulare);
        Thread thread = new Thread(sim);
        thread.start();
    }
    public Controller(InterfataSimulare interfataSimulare){
        this.interfataSimulare=interfataSimulare;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (isValid()) {
            interfataSimulare.startSimulare.setEnabled(true);
        } else {
            interfataSimulare.startSimulare.setEnabled(false);
        }
    }
}

