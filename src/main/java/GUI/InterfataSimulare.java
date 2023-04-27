package GUI;

import BusinessLogic.Policy;
import GUI.Controller;

import javax.swing.*;
import java.awt.*;

public class InterfataSimulare  extends  JFrame{
    private JPanel panel = new JPanel();
    private JLabel nrClienti = new JLabel();
    private JLabel nrCozi = new JLabel();
    private JLabel timpMaxSimulare = new JLabel();
    private JLabel timpMinSosire = new JLabel();
    private JLabel timpMaxSosire = new JLabel();
    private JLabel timpMinServire = new JLabel();
    private JLabel timpMaxServire = new JLabel();
    private JLabel titlu = new JLabel();
    private JLabel politicaDistribuireClienti = new JLabel();
    private JSpinner alegereNumarClienti = new JSpinner();
    private JSpinner alegereNumarCozi = new JSpinner();
    private JSpinner alegereDurataMaximaSimulare = new JSpinner();
    private JSpinner alegereTimpMinSosire = new JSpinner();
    private JSpinner alegereTimpMaxSosire = new JSpinner();
    private JSpinner alegereTimpMinServire = new JSpinner();
    private JSpinner alegereTimpMaxServire = new JSpinner();
    private ButtonGroup politicaSelectie = new ButtonGroup();
    public JButton startSimulare = new JButton();
    private JButton butonExit=new JButton();
    private JRadioButton shortestQ = new JRadioButton();
    private JRadioButton shortestT = new JRadioButton();
    Controller c = new Controller(this);

    public void gestionareLabel()
    {
        titlu.setBounds(470, 10, 200, 200);
        titlu.setText("GESTIONARE COZI");
        titlu.setFont(new Font("Serif", Font.ITALIC, 16));

        nrClienti.setBounds(40, 150, 200, 30);
        nrClienti.setText("Numar clienti");
        nrClienti.setFont(new Font("Serif", Font.ITALIC, 16));

        nrCozi.setBounds(40, 190, 200, 30);
        nrCozi.setText("Numar cozi");
        nrCozi.setFont(new Font("Serif", Font.ITALIC, 16));

        timpMaxSimulare.setBounds(10,230,200,30);
        timpMaxSimulare.setText("Timp max simulare");
        timpMaxSimulare.setFont(new Font("Serif", Font.ITALIC, 16));

        timpMinSosire.setBounds(450,150,200,30);
        timpMinSosire.setText("Timp minim sosire");
        timpMinSosire.setFont(new Font("Serif", Font.ITALIC, 16));

        timpMaxSosire.setBounds(450,190,200,30);
        timpMaxSosire.setText("Timp maxim sosire");
        timpMaxSosire.setFont(new Font("Serif",Font.ITALIC,16));

        timpMinServire.setBounds(450,230,200,30);
        timpMinServire.setText("Timp minim servire");
        timpMinServire.setFont(new Font("Serif",Font.ITALIC,16));

        timpMaxServire.setBounds(450,270,200,30);
        timpMaxServire.setText("Timp maxim servire");
        timpMaxServire.setFont(new Font("Serif",Font.ITALIC,16));

        panel.add(titlu);
        panel.add(nrClienti);
        panel.add(nrCozi);
        panel.add(timpMaxSimulare);
        panel.add(timpMinSosire);
        panel.add(timpMaxSosire);
        panel.add(timpMinServire);
        panel.add(timpMaxServire);
    }
    public void gestionareJSpinner()
    {
        alegereNumarClienti.setBounds(140, 150, 250, 30);
        panel.add(alegereNumarClienti);

        alegereNumarCozi.setBounds(140, 190, 250, 30);
        panel.add(alegereNumarCozi);

        alegereDurataMaximaSimulare.setBounds(140,230,250,30);
        panel.add(alegereDurataMaximaSimulare);

        alegereTimpMinSosire.setBounds(580,150,250,30);
        panel.add(alegereTimpMinSosire);


        alegereTimpMaxSosire.setBounds(580,190,250,30);
        panel.add(alegereTimpMaxSosire);

        alegereTimpMinServire.setBounds(580,230,250,30);
        panel.add(alegereTimpMinServire);

        alegereTimpMaxServire.setBounds(580,270,250,30);
        panel.add(alegereTimpMaxServire);
    }
    public void gestionareButoane()
    {
        startSimulare.setBounds(50, 400, 200, 50);
        startSimulare.setText("START");
        startSimulare.setBackground(new Color(72, 99, 182));
        startSimulare.addActionListener(c);
        panel.add(startSimulare);

        butonExit.setBounds(50,500,200,50);
        butonExit.setText("EXIT");
        butonExit.setBackground(new Color(72, 99, 182));
        butonExit.addActionListener(e ->  {
            System.exit(0);
        });
        panel.add(butonExit);
    }
    public void gestionareRestul()
    {
        politicaDistribuireClienti.setBounds(600,400,400,50);
        politicaDistribuireClienti.setText("Politica de selectie(Selection BusinessLogic.Policy):");
        politicaDistribuireClienti.setFont(new Font("Serif", Font.ITALIC, 16));

        shortestQ = new JRadioButton("Cea mai scurtă coadă de așteptare", true);
        shortestQ.setBounds(500,500,300,50);
        shortestQ.setBackground(new Color(255, 255, 182));
        shortestQ.setFont(new Font("Serif", Font.ITALIC, 16));

        shortestT = new JRadioButton("Cel mai scurt timp de așteptare", false);
        shortestT.setBounds(850,500,400,50);
        shortestT.setBackground(new Color(255, 255, 182));
        shortestT.setFont(new Font("Serif", Font.ITALIC, 16));

        politicaSelectie = new ButtonGroup();
        politicaSelectie.add(shortestQ);
        politicaSelectie.add(shortestT);

        panel.add(politicaDistribuireClienti);
        panel.add(shortestQ);
        panel.add(shortestT);
    }
    public void interfata() {
        this.setTitle("Gestionarea cozilor de asteptare folosind thread-uri si mecanisme de sincronizare");
        panel.setBackground(new Color(255, 255, 182));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        gestionareLabel();
        gestionareJSpinner();
        gestionareButoane();
        gestionareRestul();
        this.setContentPane(panel);
        this.setVisible(true);
    }
    public Integer getAlegereNumarClienti() {
        return ((Integer) alegereNumarClienti.getValue()).intValue();
    }
    public Integer getAlegereNumarCozi() {
        return ((Integer) alegereNumarCozi.getValue()).intValue();
    }
    public Integer getAlegereDurataMaximaSimulare() {
        return ((Integer) alegereDurataMaximaSimulare.getValue()).intValue();
    }
    public Integer getAlegereTimpMinSosire() {
        return ((Integer) alegereTimpMinSosire.getValue()).intValue();
    }
    public Integer getAlegereTimpMaxSosire() {
        return ((Integer) alegereTimpMaxSosire.getValue()).intValue();
    }
    public Integer getAlegereTimpMinServire() {
        return ((Integer) alegereTimpMinServire.getValue()).intValue();
    }
    public Integer getAlegereTimpMaxServire() {
        return ((Integer) alegereTimpMaxServire.getValue()).intValue();
    }
    public Policy getPoliticaDeSelectie(){
        if(shortestQ.isSelected())
            return Policy.SHORTEST_QUEUE;
        else
            return Policy.SHORTEST_TIME;
    }
    public InterfataSimulare() {
        this.interfata();
    }
}