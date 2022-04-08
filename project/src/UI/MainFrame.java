/**
 * @author Samuel Nix
 * @author Summer Bronson
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

public class MainFrame extends JFrame{
    public GridPanel gridPanel;
    private boolean playing = false;
    private float rate = 16;
    private JLabel rateLabel = new JLabel("Rate: " + rate);
    private SimSettings settings;
    public MainFrame(String title, SimSettings settings) {
        super(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.settings = settings;

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gridPanel.writeData();
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
