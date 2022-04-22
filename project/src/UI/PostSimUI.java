/**
 * @author Aedan Wells
 *
 * @description UI for users after simulation completion
 */

package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Scanner;

public class PostSimUI extends JFrame {
    public Scanner sc;
    public String[] finalDayData;
    public PostSimUI(String title) {
        super(title);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        Color green = Color.decode("#32cd32");
        //setting up labels for data
        JLabel results = new JLabel("Final Results:");
        results.setBounds(20, 10, 400, 30);
        results.setFont(new Font("Verdana", Font.PLAIN, 24));
        results.setForeground(green);
        this.add(results);

        JLabel succeptible = new JLabel("Susceptible: " + finalDayData[0]);
        succeptible.setBounds(20, 40, 200, 100);
        succeptible.setFont(new Font("Verdana", Font.PLAIN, 18));
        succeptible.setForeground(green);
        this.add(succeptible);

        JLabel infected = new JLabel("Infected: " + finalDayData[1]);
        infected.setBounds(20, 70, 200, 100);
        infected.setFont(new Font("Verdana", Font.PLAIN, 18));
        infected.setForeground(green);
        this.add(infected);

        JLabel recovered = new JLabel("Recovered: " + finalDayData[2]);
        recovered.setBounds(20, 100, 200, 100);
        recovered.setFont(new Font("Verdana", Font.PLAIN, 18));
        recovered.setForeground(green);
        this.add(recovered);

        JLabel dead = new JLabel("Dead: " + finalDayData[3]);
        dead.setBounds(20, 130, 200, 100);
        dead.setFont(new Font("Verdana", Font.PLAIN, 18));
        dead.setForeground(green);
        this.add(dead);

        JLabel vaxx = new JLabel("Vaccination: " + finalDayData[4]);
        vaxx.setBounds(20, 160, 200, 100);
        vaxx.setFont(new Font("Verdana", Font.PLAIN, 18));
        vaxx.setForeground(green);
        this.add(vaxx);

//        JLabel menu = new JLabel("Data Graphs");
//        vaxx.setBounds(20, 250, 200, 100);
//        results.setFont(new Font("Verdana", Font.PLAIN, 18));
//        this.add(menu);
        //setting up graph pictures
        JLabel lbl = new JLabel();
        ImageIcon img = new ImageIcon("./postsim/dist/density.png");
        lbl.setIcon(img);
        lbl.setBounds(150, 275, 700, 700);
        lbl.setVisible(true);
        this.add(lbl);
        //Set size
        this.setSize(800, 900);
        Color color = Color.decode("#8b0000");
        this.getContentPane().setBackground(color);
        // uses no layout managers
        this.setLayout(null);
        // makes the frame visible
        this.setVisible(true);
    }
}
