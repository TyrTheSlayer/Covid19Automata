package UI;

import javax.swing.*;
import java.awt.*;

/**
 * @author Samuel Nix
 *
 * @description Creates a legend for the program
 */
public class LegendComponent extends JComponent {
    public LegendComponent() {
        super();
        setPreferredSize(new Dimension(500, 500));
    }

    /**
     * Creates a paintcomponent
     * @param g graphics
     */
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
        leg.setBounds(50, 50, 200, 25);
        leg.setFont(f);
        add(leg);

        //JLabels for Infection Status
        JLabel sus = new JLabel("Susceptible:");
        sus.setForeground(Color.white);
        sus.setBounds(50,100,200,25);
        sus.setFont(f);
        add(sus);

        JLabel inf = new JLabel("Infected:");
        inf.setForeground(Color.white);
        inf.setBounds(50,130,200,25);
        inf.setFont(f);
        add(inf);

        JLabel rec = new JLabel("Recovered:");
        rec.setForeground(Color.white);
        rec.setBounds(50,160,200,25);
        rec.setFont(f);
        add(rec);

        g.setColor(Color.decode("#0072B2"));
        g.fillOval(200,100,20,20);

        g.setColor(Color.decode("#CC79A7"));
        g.fillOval(200,130,20,20);

        g.setColor(Color.CYAN);
        g.fillOval(200,160,20,20);

        //JLabels for Entrance/Exits
        JLabel ent = new JLabel("Entrance:");
        ent.setForeground(Color.white);
        ent.setBounds(50,210,200,25);
        ent.setFont(f);
        add(ent);

        JLabel exit = new JLabel("Exit:");
        exit.setForeground(Color.white);
        exit.setBounds(50,240,200,25);
        exit.setFont(f);
        add(exit);

        g.setColor(Color.decode("#009E73"));
        g.fillRect(200,210,20,20);

        g.setColor(Color.decode("#D55E00"));
        g.fillRect(200,240,20,20);

        canvas.setColor(new Color(255, 192, 0));
        canvas.fillOval(850, 175, 200, 200);
        canvas.setColor(Color.BLACK);
        canvas.drawOval(850, 175, 200, 200);

        canvas.fillOval(900, 235, 20, 20);
        canvas.fillOval(980, 235, 20, 20);
        canvas.drawArc(890, 265, 120, 80, 190, 160);
    }
}

