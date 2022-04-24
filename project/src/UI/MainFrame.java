/**
 * @author Samuel Nix
 * @author Summer Bronson
 * @author Aedan Wells
 *
 * A frame to display the simulation
 */

package UI;

import Grid.GridPanel;
import Simulator.SimSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.Runtime;
import java.util.Scanner;

public class MainFrame extends JFrame{
    public GridPanel gridPanel;
    private boolean playing = false;
    private float rate = 16;
    private JLabel rateLabel = new JLabel("Rate: " + rate);
    private SimSettings settings;
    public MainFrame(String title, SimSettings settings) {
        super(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.settings = settings;

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gridPanel.writeData();
                PostSimUI postSimUI = new PostSimUI("Test");
                postSimUI.startWindow();

                File pathToExe = new File("postsim\\dist\\plot.exe");
                File pathToCSV = new File("postsim\\simulation.csv");
                ProcessBuilder builder = new ProcessBuilder(pathToExe.getAbsolutePath(), "-f", "simulation.csv", "-d");
                builder.directory(new File("postsim"));
                builder.redirectErrorStream(true);
                Process process = null;
                try {
                    process = builder.start();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                Scanner s = new Scanner(process.getInputStream());
                StringBuilder text = new StringBuilder();
                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append("\n");
                }
                s.close();

                int result = 0;
                try {
                    result = process.waitFor();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                System.out.printf( "Process exited with result %d and output %s%n", result, text );
            }
        });
    }

    /**
     * Starts the window for the simulation
     */
    public void startWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        // creates a new gridPanel
        gridPanel = new GridPanel(10, 60, 120, 0, 0, settings);
        gridPanel.setPause_len((long) (1000/60)); // Init the pause len for the sim
        add(gridPanel, BorderLayout.CENTER);
        // set minimum size for window
        this.setMinimumSize(new Dimension(gridPanel.gridPixelWidth, gridPanel.gridPixelHeight));
        this.setResizable(false);

        ActionListener playSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Used to allow this to track sim status
                if(!playing) {
                    playing = true;
                    gridPanel.start();
                    rateLabel.setText("Rate: " + rate);
                }
            }
        };

        ActionListener pauseSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playing) {
                    playing = false;
                    gridPanel.pause();
                    rateLabel.setText("Paused");
                }
            }
        };

        ActionListener speedUpSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rate *= 2;
                if (rate > 256)
                    rate = 256;
                gridPanel.setPause_len((long) (1000/rate));
                rateLabel.setText("Rate: " + rate);
            }
        };

        ActionListener slowDownSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rate *= 0.5;
                if (rate < 1)
                    rate = 1;
                gridPanel.setPause_len((long) (1000/rate));
                rateLabel.setText("Rate: " + rate);
            }
        };

        JPanel buttonPanel = new JPanel();

        JButton start = new JButton("Play");
        JButton pause = new JButton("Pause");
        JButton speedUp = new JButton("Speed Up");
        JButton speedDown = new JButton("Slow Down");

        start.addActionListener(playSim);
        pause.addActionListener(pauseSim);
        speedUp.addActionListener(speedUpSim);
        speedDown.addActionListener(slowDownSim);

        buttonPanel.add(start);
        buttonPanel.add(pause);
        buttonPanel.add(speedUp);
        buttonPanel.add(speedDown);
        buttonPanel.add(rateLabel);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        // open window
        setVisible(true);
    }
}
