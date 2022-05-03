/**
 * @author Samuel Nix
 *
 * @description Creates a legend for the program
 */

package UI;

import javax.swing.*;
import java.awt.*;

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
        Font bf = new Font("Verdana", Font.PLAIN,17);

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


        //Jlabels for Buildings
        JLabel buildings = new JLabel("Buildings:");
        buildings.setForeground(Color.WHITE);
        buildings.setBounds(50,290, 200,25);
        buildings.setFont(f);
        add(buildings);

        JLabel sch = new JLabel("School");
        sch.setFont(bf);
        sch.setForeground(Color.WHITE);
        sch.setBounds(90, 310, 100,50);
        add(sch);
        g.setColor(Color.decode("#9e746f"));
        g.fillRect(70,350,100,30);

        JLabel sto = new JLabel("Store");
        sto.setFont(bf);
        sto.setForeground(Color.WHITE);
        sto.setBounds(243, 310, 100,50);
        add(sto);
        g.setColor(Color.decode("#9e7f6f"));
        g.fillRect(240,350,50,50);

        JLabel lib = new JLabel("Library");
        lib.setFont(bf);
        lib.setForeground(Color.WHITE);
        lib.setBounds(390, 310, 100,50);
        add(lib);
        g.setColor(Color.decode("#9e936f"));
        g.fillRect(370,350,100,50);

        JLabel drugs = new JLabel("Drug Store");
        drugs.setFont(bf);
        drugs.setForeground(Color.WHITE);
        drugs.setBounds(520, 310, 100,50);
        add(drugs);
        g.setColor(Color.decode("#889e6f"));
        g.fillRect(560,350,20,20);

        JLabel con = new JLabel("Concert Hall");
        con.setFont(bf);
        con.setForeground(Color.WHITE);
        con.setBounds(78, 420, 150,30);
        add(con);
        g.setColor(Color.decode("#6f9e97"));
        g.fillRect(70,460,130,130);

        JLabel cas = new JLabel("Casino");
        cas.setFont(bf);
        cas.setForeground(Color.WHITE);
        cas.setBounds(273, 420, 100,50);
        add(cas);
        g.setColor(Color.decode("#6f769e"));
        g.fillRect(270,460,70,70);

        JLabel quack = new JLabel("Rubber Duck Factory");
        quack.setFont(bf);
        quack.setForeground(Color.WHITE);
        quack.setBounds(370, 420, 300,50);
        add(quack);
        g.setColor(Color.decode("#826f9e"));
        g.fillRect(410,460,100,80);

        JLabel hos = new JLabel("Hospital");
        hos.setFont(bf);
        hos.setForeground(Color.WHITE);
        hos.setBounds(592, 420, 100,50);
        add(hos);
        g.setColor(Color.decode("#9d6f9e"));
        g.fillRect(580,460,100,100);



        canvas.setColor(new Color(255, 192, 0));
        canvas.fillOval(850, 175, 200, 200);
        canvas.setColor(Color.BLACK);
        canvas.drawOval(850, 175, 200, 200);

        canvas.fillOval(900, 235, 20, 20);
        canvas.fillOval(980, 235, 20, 20);
        canvas.drawArc(890, 265, 120, 80, 190, 160);


    }
}

