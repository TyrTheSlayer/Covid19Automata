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
import java.io.FileNotFoundException;
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
            sc.useDelimiter(",");
            while(sc.hasNext()){
                data = sc.next();
            }
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

        JLabel results = new JLabel("Final Results:");
        results.setBounds(20, 10, 400, 30);
        results.setFont(new Font("Verdana", Font.PLAIN, 24));
        this.add(results);

        JLabel succeptible = new JLabel("Succeptible: ");
        succeptible.setBounds(20, 40, 200, 100);
        results.setFont(new Font("Verdana", Font.PLAIN, 18));
        this.add(succeptible);
        //Set size
        this.setSize(700, 700);
        // uses no layout managers
        this.setLayout(null);
        // makes the frame visible
        this.setVisible(true);
    }
}
