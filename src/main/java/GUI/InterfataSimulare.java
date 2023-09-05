package GUI;

import BusinessLogic.Policy;
import javax.swing.*;
import java.awt.*;

public class InterfataSimulare  extends  JFrame{
    private JPanel panel = new JPanel();
    private JLabel nrClients = new JLabel();
    private JLabel nrQueue = new JLabel();
    private JLabel maxSimulationTime = new JLabel();
    private JLabel minArrivalTime = new JLabel();
    private JLabel maxArrivalTime = new JLabel();
    private JLabel minServingTime = new JLabel();
    private JLabel maxServingTime = new JLabel();
    private JLabel title = new JLabel();
    private JLabel distributionPolicy = new JLabel();
    private JSpinner choiceOfClientsNumber = new JSpinner();
    private JSpinner choiceOfQueueNumber = new JSpinner();
    private JSpinner choiceMaxDurationSimulation = new JSpinner();
    private JSpinner choiceMinArrivalTime = new JSpinner();
    private JSpinner choiceMaxArrivalTime = new JSpinner();
    private JSpinner choiceMinServingTime = new JSpinner();
    private JSpinner choiceMaxServingTime = new JSpinner();
    private ButtonGroup selectionPolicy = new ButtonGroup();
    public JButton startSimulation = new JButton();
    private JButton exitButton=new JButton();
    private JRadioButton shortestQ = new JRadioButton();
    private JRadioButton shortestT = new JRadioButton();
    Controller c = new Controller(this);

    public void labelManagement()
    {
        title.setBounds(470, 10, 200, 200);
        title.setText("GESTIONARE COZI");
        title.setFont(new Font("Serif", Font.ITALIC, 16));

        nrClients.setBounds(40, 150, 200, 30);
        nrClients.setText("Numar clienti");
        nrClients.setFont(new Font("Serif", Font.ITALIC, 16));

        nrQueue.setBounds(40, 190, 200, 30);
        nrQueue.setText("Numar cozi");
        nrQueue.setFont(new Font("Serif", Font.ITALIC, 16));

        maxSimulationTime.setBounds(10,230,200,30);
        maxSimulationTime.setText("Timp max simulare");
        maxSimulationTime.setFont(new Font("Serif", Font.ITALIC, 16));

        minArrivalTime.setBounds(450,150,200,30);
        minArrivalTime.setText("Timp minim sosire");
        minArrivalTime.setFont(new Font("Serif", Font.ITALIC, 16));

        maxArrivalTime.setBounds(450,190,200,30);
        maxArrivalTime.setText("Timp maxim sosire");
        maxArrivalTime.setFont(new Font("Serif",Font.ITALIC,16));

        minServingTime.setBounds(450,230,200,30);
        minServingTime.setText("Timp minim servire");
        minServingTime.setFont(new Font("Serif",Font.ITALIC,16));

        maxServingTime.setBounds(450,270,200,30);
        maxServingTime.setText("Timp maxim servire");
        maxServingTime.setFont(new Font("Serif",Font.ITALIC,16));

        panel.add(title);
        panel.add(nrClients);
        panel.add(nrQueue);
        panel.add(maxSimulationTime);
        panel.add(minArrivalTime);
        panel.add(maxArrivalTime);
        panel.add(minServingTime);
        panel.add(maxServingTime);
    }
    public void jSpinnerManagement()
    {
        choiceOfClientsNumber.setBounds(140, 150, 250, 30);
        panel.add(choiceOfClientsNumber);

        choiceOfQueueNumber.setBounds(140, 190, 250, 30);
        panel.add(choiceOfQueueNumber);

        choiceMaxDurationSimulation.setBounds(140,230,250,30);
        panel.add(choiceMaxDurationSimulation);

        choiceMinArrivalTime.setBounds(580,150,250,30);
        panel.add(choiceMinArrivalTime);

        choiceMaxArrivalTime.setBounds(580,190,250,30);
        panel.add(choiceMaxArrivalTime);

        choiceMinServingTime.setBounds(580,230,250,30);
        panel.add(choiceMinServingTime);

        choiceMaxServingTime.setBounds(580,270,250,30);
        panel.add(choiceMaxServingTime);
    }
    public void buttonManagement()
    {
        startSimulation.setBounds(50, 400, 200, 50);
        startSimulation.setText("START");
        startSimulation.setBackground(new Color(72, 99, 182));
        startSimulation.addActionListener(c);
        panel.add(startSimulation);

        exitButton.setBounds(50,500,200,50);
        exitButton.setText("EXIT");
        exitButton.setBackground(new Color(72, 99, 182));
        exitButton.addActionListener(e ->  {
            System.exit(0);
        });
        panel.add(exitButton);
    }
    public void restManagement()
    {
        distributionPolicy.setBounds(600,400,400,50);
        distributionPolicy.setText("Politica de selectie(Selection BusinessLogic.Policy):");
        distributionPolicy.setFont(new Font("Serif", Font.ITALIC, 16));

        shortestQ = new JRadioButton("Cea mai scurtă coadă de așteptare", true);
        shortestQ.setBounds(500,500,300,50);
        shortestQ.setBackground(new Color(255, 255, 182));
        shortestQ.setFont(new Font("Serif", Font.ITALIC, 16));

        shortestT = new JRadioButton("Cel mai scurt timp de așteptare", false);
        shortestT.setBounds(850,500,400,50);
        shortestT.setBackground(new Color(255, 255, 182));
        shortestT.setFont(new Font("Serif", Font.ITALIC, 16));

        selectionPolicy = new ButtonGroup();
        selectionPolicy.add(shortestQ);
        selectionPolicy.add(shortestT);

        panel.add(distributionPolicy);
        panel.add(shortestQ);
        panel.add(shortestT);
    }
    public void applicationInterface() {
        this.setTitle("Gestionarea cozilor de asteptare folosind thread-uri si mecanisme de sincronizare");
        panel.setBackground(new Color(255, 255, 182));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        labelManagement();
        jSpinnerManagement();
        buttonManagement();
        restManagement();
        this.setContentPane(panel);
        this.setVisible(true);
    }
    public Integer getChoiceOfClientsNumber() {
        return ((Integer) choiceOfClientsNumber.getValue()).intValue();
    }
    public Integer getChoiceOfQueueNumber() {
        return ((Integer) choiceOfQueueNumber.getValue()).intValue();
    }
    public Integer getChoiceMaxDurationSimulation() {
        return ((Integer) choiceMaxDurationSimulation.getValue()).intValue();
    }
    public Integer getChoiceMinArrivalTime() {
        return ((Integer) choiceMinArrivalTime.getValue()).intValue();
    }
    public Integer getChoiceMaxArrivalTime() {
        return ((Integer) choiceMaxArrivalTime.getValue()).intValue();
    }
    public Integer getChoiceMinServingTime() {
        return ((Integer) choiceMinServingTime.getValue()).intValue();
    }
    public Integer getChoiceMaxServingTime() {
        return ((Integer) choiceMaxServingTime.getValue()).intValue();
    }
    public Policy getSelectionPolicy(){
        if(shortestQ.isSelected())
            return Policy.SHORTEST_QUEUE;
        else
            return Policy.SHORTEST_TIME;
    }
    public InterfataSimulare() {
        this.applicationInterface();
    }
}