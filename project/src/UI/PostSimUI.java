package UI;


import Simulator.SimSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 * @author Aedan Wells
 *
 * UI for users after simulation completion
 */
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
        //Grabs postsim directory, pngs are all that are set in dropdown menu
        File dir = new File("./postsim/");
        File[] directoryListing = dir.listFiles();
        int j = 0;
        if (directoryListing != null) {
            this.optionsToChoose = new String[directoryListing.length-10];
            for (File child : directoryListing) {
                if(child.getName().contains(".png")){
                    this.optionsToChoose[j] = child.getName().replace(".png", "");
                    j++;
                }
            }
        }
        //On exit, end program
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
        Color cream = Color.decode("#fff9ea");
        //setting up labels for data
        JLabel results = new JLabel("Final Results:");
        results.setBounds(20, 10, 400, 30);
        results.setFont(new Font("Verdana", Font.PLAIN, 24));
        this.add(results);


        JLabel succeptible = new JLabel("Uninfected: " + finalDayData[0]);
        succeptible.setBounds(20, 40, 200, 100);
        succeptible.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(succeptible);

        JLabel infected = new JLabel("Infected: " + finalDayData[1]);
        infected.setBounds(20, 70, 200, 100);
        infected.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(infected);

        JLabel recovered = new JLabel("Recovered: " + finalDayData[2]);
        recovered.setBounds(20, 100, 200, 100);
        recovered.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(recovered);

        JLabel dead = new JLabel("Dead: " + finalDayData[3]);
        dead.setBounds(20, 130, 200, 100);
        dead.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(dead);

        JLabel vaxx = new JLabel("Vaccination: " + finalDayData[4]);
        vaxx.setBounds(20, 160, 200, 100);
        vaxx.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(vaxx);

        //General Percent Stats
        JLabel perc = new JLabel("End Of Sim Percentages:");
        perc.setBounds(300, 10, 400, 30);
        perc.setFont(new Font("Verdana", Font.PLAIN, 24));
        this.add(perc);

        Double deadPercent = (Double.parseDouble(finalDayData[3]) / (double)settings.getPopulation())*100;
        JLabel deadPopulation = new JLabel("% dead: " + String.format("%.2f", deadPercent) + "%");
        deadPopulation.setBounds(300, 20, 300, 100);
        deadPopulation.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(deadPopulation);

        Double uninfectedPercent = (Double.parseDouble(finalDayData[0]) / (double)settings.getPopulation())*100;
        JLabel uninfectedPopulation = new JLabel("% uninfected: " + String.format("%.2f", uninfectedPercent) + "%");
        uninfectedPopulation.setBounds(300, 50, 300, 100);
        uninfectedPopulation.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(uninfectedPopulation);

        Double infectedPercent = (Double.parseDouble(finalDayData[1]) / (double)settings.getPopulation())*100;
        JLabel infectedPopulation = new JLabel("% infected: " + String.format("%.2f", infectedPercent) + "%");
        infectedPopulation.setBounds(300, 90, 300, 100);
        infectedPopulation.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(infectedPopulation);

        Double recoveredPercent = (Double.parseDouble(finalDayData[2]) / (double)settings.getPopulation())*100;
        JLabel recoveredPopulation = new JLabel("% recovered: " + String.format("%.2f", recoveredPercent) + "%");
        recoveredPopulation.setBounds(300, 130, 300, 100);
        recoveredPopulation.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(recoveredPopulation);

        Double vaxxPercent = (Double.parseDouble(finalDayData[4]) / (double)settings.getPopulation())*100;
        JLabel vaxxPopulation = new JLabel("% vaccinated: " + String.format("%.2f", vaxxPercent) + "%");
        vaxxPopulation.setBounds(300, 170, 300, 100);
        vaxxPopulation.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(vaxxPopulation);

        JLabel menuLabel = new JLabel("Data Graphs:");
        menuLabel.setBounds(10, 220, 200, 100);
        //60 points
        menuLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(menuLabel);

        //set drop down menu
        JComboBox<String> menu = new JComboBox<>(this.optionsToChoose);
        menu.setBounds(10, 290, 160, 25);
        this.add(menu);

        //setting up graph pictures
        JLabel lbl = new JLabel();
        this.img = new ImageIcon("./postsim/density.png");
        lbl.setIcon(img);
        lbl.setBounds(15, 240, 650, 650);
        lbl.setVisible(true);
        this.add(lbl);


        JButton export = new JButton("Export as");
        export.setBounds(500, 290, 100, 25);
        this.add(export);

        JFileChooser exporter = new JFileChooser();
        ActionListener toExport = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (exporter.showSaveDialog(exporter.getParent()) == JFileChooser.APPROVE_OPTION) {
                    try {
                        if (!Files.exists(exporter.getSelectedFile().toPath()))
                            exporter.getSelectedFile().createNewFile();
                        System.out.println("What're you gonna do? Kill yourself?");
                        Files.copy(new File("./postsim/" + menu.getItemAt(menu.getSelectedIndex()) + ".png").toPath(), exporter.getSelectedFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                }
            }
        };

        export.addActionListener(toExport);



        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                lbl.setIcon(new ImageIcon("./postsim/" + menu.getItemAt(menu.getSelectedIndex()) + ".png"));
            }
        });
        //Set size
        this.setPreferredSize(new Dimension(800,875));
        this.getContentPane().setBackground(cream);
        // uses no layout managers
        this.setLayout(null);
        pack();
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
        Rectangle rect = new Rectangle(20, 355, 640, 490);
        g2.draw(rect);
        Rectangle rect2 = new Rectangle(300, 35, 310, 240);
        g2.draw(rect2);

    }
}
