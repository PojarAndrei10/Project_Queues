package GUI;

import javax.swing.*;
import java.awt.*;
public class MarketWindow extends JFrame{
    private SpringLayout sl = new SpringLayout();
    public static JPanel interfacePanel;
    public void setInterface(){
        //d va contine dimensiunea ecranului in pixeli si folosim clasa Toolkit pentru a obtine
        //dimensiunea ecranului implicit
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(d);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.interfacePanel = new JPanel(sl);
        this.interfacePanel.setBackground(new Color(153, 165, 201));
        this.getContentPane().add(this.interfacePanel);
    }
    public SpringLayout getSl() {
        return sl;
    }
    public void setSl(SpringLayout sl) {
        this.sl = sl;
    }
    public static void setInterfacePanel(JPanel interfataPanel) {
        MarketWindow.interfacePanel = interfataPanel;
    }
    public JPanel getInterfacePanel() {
        return interfacePanel;
    }
    public void settingL(String text,int x,int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.ITALIC, 12));
        label.setOpaque(true);
        label.setBackground(new Color(153, 165, 201));
        label.setForeground(new Color(13, 13, 13));

        interfacePanel.add(label);
        SpringLayout.Constraints labelConstraints = sl.getConstraints(label);
        labelConstraints.setX(Spring.constant(y));
        labelConstraints.setY(Spring.constant(x));
        label.setVisible(true);
    }
    public void settingL2(String text,int x,int y)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.ITALIC, 25));
        label.setOpaque(true);
        label.setBackground(new Color(153, 165, 201));
        label.setForeground(new Color(13, 13, 13));

        interfacePanel.add(label);
        SpringLayout.Constraints labelConstraints = sl.getConstraints(label);
        labelConstraints.setX(Spring.constant(y));
        labelConstraints.setY(Spring.constant(x));
        label.setVisible(true);
    }
    public MarketWindow()
    {
        this.setInterface();
    }
}