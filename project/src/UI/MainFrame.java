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
    public LegendComponent leg;
    private boolean playing = false;
    private float rate = 16;
    private JLabel rateLabel = new JLabel("Rate: " + rate);
    private SimSettings settings;
    public MainFrame(String title, SimSettings settings) {
        super(title);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.settings = settings;

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gridPanel.writeData();

                File pathToExe = new File("postsim\\dist\\plot.exe");
                File pathToSIMCSV = new File("postsim\\simulation.csv");
                ProcessBuilder builder = new ProcessBuilder(pathToExe.getAbsolutePath(), "-d", "-p");
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

                System.out.printf("Process1 exited with result %d and output %s%n", result, text);
                System.out.printf("Start Process 2\n");
                PostSimUI postSimUI = new PostSimUI("Post Simulation", settings);
                postSimUI.startWindow();
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

        //creates a legend component
        leg = new LegendComponent();
        leg.setVisible(true);

        // creates a new gridPanel
        gridPanel = new GridPanel(10, 60, 120, 0, 0, settings);
        gridPanel.setPause_len((long) (1000/60)); // Init the pause len for the sim
        add(gridPanel, BorderLayout.CENTER);


        // set minimum size for window
        this.setMinimumSize(new Dimension(gridPanel.gridPixelWidth, gridPanel.gridPixelHeight));
        this.setResizable(false);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gridPanel.pause();
            }
        });

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

        ActionListener exitSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.super.dispose();

                LoadingFrame load = new LoadingFrame();
                load.setVisible(true);
                load.repaint();

                gridPanel.writeData();

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

                PostSimUI postSimUI = new PostSimUI("Post Simulation", settings);

                load.setVisible(false);

                postSimUI.startWindow();
            }
        };

        ActionListener Legend = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playing) {
                    playing = false;
                    gridPanel.pause();
                    remove(gridPanel);
                    add(leg, BorderLayout.CENTER);
                    rateLabel.setText("Paused");
                    repaint();
                } else {
                    playing = true;
                    remove(leg);
                    add(gridPanel, BorderLayout.CENTER);
                    repaint();
                }
            }
        };

        JPanel buttonPanel = new JPanel();

        JButton start = new JButton("Play");
        JButton pause = new JButton("Pause");
        JButton speedUp = new JButton("Speed Up");
        JButton speedDown = new JButton("Slow Down");
        JButton exit = new JButton("Exit");
        JButton legend = new JButton("Legend");

        start.addActionListener(playSim);
        pause.addActionListener(pauseSim);
        speedUp.addActionListener(speedUpSim);
        speedDown.addActionListener(slowDownSim);
        exit.addActionListener(exitSim);
        legend.addActionListener(Legend);

        buttonPanel.add(start);
        buttonPanel.add(pause);
        buttonPanel.add(speedUp);
        buttonPanel.add(speedDown);
        buttonPanel.add(legend);
        buttonPanel.add(exit);
        buttonPanel.add(rateLabel);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        // open window
        setVisible(true);
    }
}
