import javax.swing.*;
import java.awt.*;

public class Interfata {
    private JFrame frame = new JFrame("Gestionarea cozilor de asteptare folosind thread-uri si mecanisme de sincronizare");
    private JPanel panel = new JPanel();
    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3 = new JTextField();
    private JTextField textField4 = new JTextField();
    private JTextField textField5 = new JTextField();
    private JTextField textField6 = new JTextField();
    private JTextField textField7 = new JTextField();
    private JButton butonStart = new JButton();
    private JButton butonExit = new JButton();
    private JLabel nrClienti=new JLabel();
    private JLabel nrCozi=new JLabel();
    private JLabel timpMaxSimulare=new JLabel();
    private JLabel timpMinSosire=new JLabel();
    private JLabel timpMaxSosire=new JLabel();
    private JLabel timpMinServire=new JLabel();
    private JLabel timpMaxServire=new JLabel();
    private JLabel titlu=new JLabel();

    public void gestionareLabel() {

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

    public void gestionareTextFields() {
        textField1.setBounds(140, 150, 250, 30);
        textField1.setText("");
        panel.add(textField1);

        textField2.setBounds(140, 190, 250, 30);
        textField2.setText("");
        panel.add(textField2);

        textField3.setBounds(140,230,250,30);
        textField3.setText("");
        panel.add(textField3);

       textField4.setBounds(580,150,250,30);
       textField4.setText("");
       panel.add(textField4);

       textField5.setBounds(580,190,250,30);
       textField5.setText("");
       panel.add(textField5);

       textField6.setBounds(580,230,250,30);
       textField6.setText("") ;
       panel.add(textField6);

       textField7.setBounds(580,270,250,30);
       textField7.setText("")  ;
       panel.add(textField7);

    }
    public void gestionareButoane()
    {
       butonStart.setBounds(50, 400, 200, 50);
       butonStart.setText("START");
       butonStart.setBackground(new Color(72, 99, 182));
       panel.add(butonStart);

        butonExit.setBounds(300,400,200,50);
        butonExit.setText("Buton exit");
        butonExit.setBackground(new Color(72, 99, 182));
        panel.add(butonExit);
    }
    public Interfata()
    {
        panel.setBackground(new Color(255, 255, 182));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        gestionareLabel();
        gestionareTextFields();
        gestionareButoane();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}