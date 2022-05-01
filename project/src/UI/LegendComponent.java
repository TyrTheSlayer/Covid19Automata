package UI;

import javax.swing.*;
import java.awt.*;

public class LegendComponent extends JComponent {
    public LegendComponent() {
        super();
        setPreferredSize(new Dimension(500, 500));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D canvas = (Graphics2D) g;
        canvas.setStroke(new BasicStroke(3));
        canvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.darkGray);
        g.fillRect(0,0,1500,1000);

        Font f = new Font("Verdana", Font.PLAIN, 20);

        JLabel leg = new JLabel("Legend:");
        leg.setForeground(Color.white);
        leg.setBackground(Color.white);
        leg.setBounds(50, 50, 200, 20);
        leg.setFont(f);
        add(leg);

        JLabel sus = new JLabel("Susceptible:");
        sus.setForeground(Color.white);
        sus.setBounds(50,100,200,20);
        sus.setFont(f);
        add(sus);

        JLabel inf = new JLabel("Infected:");
        inf.setForeground(Color.white);
        inf.setBounds(50,130,200,20);
        inf.setFont(f);
        add(inf);

        JLabel rec = new JLabel("Recovered:");
        rec.setForeground(Color.white);
        rec.setBounds(50,160,200,20);
        rec.setFont(f);
        add(rec);

        g.setColor(Color.decode("#0072B2"));
        g.fillOval(200,100,25,25);

        g.setColor(Color.decode("#CC79A7"));
        g.fillOval(200,130,25,25);

        g.setColor(Color.CYAN);
        g.fillOval(200,160,25,25);

        /*JPanel jp = new JPanel();
        //jp.setSize(2, 4);

        JLabel sus = new JLabel("Susceptible");
        JLabel sas = new JLabel("Susceptibl");
        JLabel sis = new JLabel("Susceptib");
        JLabel ses = new JLabel("Suscepti");
        JLabel sos = new JLabel("Suscept");

        jp.add(sus);
        jp.add(sas);
        jp.add(sis);
        jp.add(ses);
        jp.add(sos);

        add(jp, BorderLayout.CENTER);
        repaint();*/

        /*canvas.setColor(new Color(255, 192, 0));
        canvas.fillOval(150, 150, 200, 200);
        canvas.setColor(Color.BLACK);
        canvas.drawOval(150, 150, 200, 200);

        canvas.fillOval(200, 210, 20, 20);
        canvas.fillOval(280, 210, 20, 20);
        canvas.drawArc(190, 240, 120, 80, 190, 160);*/
    }
}

