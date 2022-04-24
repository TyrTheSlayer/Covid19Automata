/**
 * @author Aedan Wells
 *
 * @description UI for users after simulation completion
 */

package UI;


import Simulator.SimSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.Scanner;

public class PostSimUI extends JFrame {
    public Scanner sc;
    public String[] finalDayData;
    public String[] optionsToChoose;
    public ImageIcon img;
    private SimSettings settings;
    public PostSimUI(String title, SimSettings settings) {
        super(title);
        //setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.settings = settings;
        String data = "";
        try{
            this.sc = new Scanner(new File("./postsim/simulation.csv"));
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                data = sc.next();
            }
            this.finalDayData = new String[5];
            this.finalDayData = data.split(",");

        } catch  (Exception e) {
            e.printStackTrace();
        }


        File dir = new File("./postsim");
        File[] directoryListing = dir.listFiles();
        int j = 0;
        if (directoryListing != null) {
            this.optionsToChoose = new String[directoryListing.length-4];
            for (File child : directoryListing) {
                if(child.getName().contains(".png")){
                    this.optionsToChoose[j] = child.getName().replace(".png", "");
                    j++;
                }

            }
        }

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Starts the Post Sim UI window
     */
    public void startWindow(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //color definition
        Color periwinkle = Color.decode("#5284ff");
        Color cream = Color.decode("#fff9ea");
        //setting up labels for data
        JLabel results = new JLabel("Final Results:");
        results.setBounds(20, 10, 400, 30);
        results.setFont(new Font("Verdana", Font.PLAIN, 24));
        results.setForeground(periwinkle);
        this.add(results);

        JLabel succeptible = new JLabel("Susceptible: " + finalDayData[0]);
        succeptible.setBounds(20, 40, 200, 100);
        succeptible.setFont(new Font("Verdana", Font.PLAIN, 18));
        succeptible.setForeground(periwinkle);
        this.add(succeptible);

        JLabel infected = new JLabel("Infected: " + finalDayData[1]);
        infected.setBounds(20, 70, 200, 100);
        infected.setFont(new Font("Verdana", Font.PLAIN, 18));
        infected.setForeground(periwinkle);
        this.add(infected);

        JLabel recovered = new JLabel("Recovered: " + finalDayData[2]);
        recovered.setBounds(20, 100, 200, 100);
        recovered.setFont(new Font("Verdana", Font.PLAIN, 18));
        recovered.setForeground(periwinkle);
        this.add(recovered);

        JLabel dead = new JLabel("Dead: " + finalDayData[3]);
        dead.setBounds(20, 130, 200, 100);
        dead.setFont(new Font("Verdana", Font.PLAIN, 18));
        dead.setForeground(periwinkle);
        this.add(dead);

        JLabel vaxx = new JLabel("Vaccination: " + finalDayData[4]);
        vaxx.setBounds(20, 160, 200, 100);
        vaxx.setFont(new Font("Verdana", Font.PLAIN, 18));
        vaxx.setForeground(periwinkle);
        this.add(vaxx);
        Double deadPercent = 0.0;
        if(isNumeric(finalDayData[3])){
            deadPercent = (Double.parseDouble(finalDayData[3]) / (double)settings.getPopulation())*100;
            JLabel population = new JLabel("% of population dead: " + String.format("%.2f", deadPercent) + "%");
            population.setBounds(300, 0, 300, 100);
            population.setFont(new Font("Verdana", Font.PLAIN, 18));
            this.add(population);
        }else{
            JLabel population = new JLabel("% of population dead: INVALID");
            population.setBounds(300, 0, 300, 100);
            population.setFont(new Font("Verdana", Font.PLAIN, 18));
            this.add(population);
        }
        //get death picture
        String filename = "";
        if(deadPercent <= 10){
            //happy
            filename = "happy.jpg";
        }else if(deadPercent <= 25){
            //medium
            filename = "awkward.jpg";
        }else if(deadPercent <= 50){
            //bad
            filename = "sad.jpg";
        }else{
            //very bad
            filename = "depression.jpg";
        }
        JLabel deadLbl = new JLabel();
        ImageIcon pic = new ImageIcon("./project/src/UI/"+ filename);
        deadLbl.setIcon(pic);
        deadLbl.setBounds(300, 100, 462, 261);
        deadLbl.setVisible(true);
        this.add(deadLbl);

        JLabel menuLabel = new JLabel("Data Graphs:");
        menuLabel.setBounds(10, 280, 200, 100);
        menuLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(menuLabel);

        //set drop down menu

        JComboBox<String> menu = new JComboBox<>(this.optionsToChoose);
        menu.setBounds(10, 350, 160, 25);
        this.add(menu);

        //setting up graph pictures
        JLabel lbl = new JLabel();
        this.img = new ImageIcon("./postsim/density.png");
        lbl.setIcon(img);
        lbl.setBounds(15, 300, 650, 650);
        lbl.setVisible(true);
        this.add(lbl);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                lbl.setIcon(new ImageIcon("./postsim/" + menu.getItemAt(menu.getSelectedIndex()) + ".png"));
            }
        });
        //Set size
        this.setSize(800, 1000);
        this.getContentPane().setBackground(cream);
        // uses no layout managers
        this.setLayout(null);
        // makes the frame visible
        this.setVisible(true);
    }

    /**
     * Set up lines for borders of elements
     *
     * @param g graphics component doing the drawing
     */
    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        //value border
        Line2D lin = new Line2D.Float(20, 35, 225, 35);
        g2.draw(lin);
        lin = new Line2D.Float(20, 35, 20, 275);
        g2.draw(lin);
        lin = new Line2D.Float(20, 275, 225, 275);
        g2.draw(lin);
        lin = new Line2D.Float(225, 35, 225, 275);
        g2.draw(lin);
        //graph border
        Rectangle rect = new Rectangle(20, 415, 640, 480);
        g2.draw(rect);

    }

    /**
     * Checks to see if a string is a number
     *
     * @param str string being checked
     * @return true if it is a string, false otherwise
     */
    public boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
