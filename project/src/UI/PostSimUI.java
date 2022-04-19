/**
 * @author Aedan Wells
 *
 * @description UI for users after simulation completion
 */

package UI;

import Simulator.SimSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PostSimUI extends JFrame {

    public PostSimUI(String title) {
        super(title);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void startWindow(){
        PostSimUI frame = new PostSimUI("Test");

        try {
            // UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel results = new JLabel("Results");
        results.setBounds(493, 120, 110, 30);
        results.setFont(new Font("Verdana", Font.PLAIN, 12));

        frame.add(results);

        //Set size
        frame.setSize(700, 370) ;

        // uses no layout managers
        frame.setLayout(null);

        // makes the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        startWindow();
    }
}
