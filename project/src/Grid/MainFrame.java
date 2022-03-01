/**
 * @author Samuel Nix
 * @author Summer Bronson
 *
 * A frame to display the simulation
 */

package Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    public GridPanel gridPanel;
    private boolean playing = false;
    private float rate = 2;
    public MainFrame(String title) {
        super(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        gridPanel = new GridPanel(20, 30, 30, 0, 0);
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
                }
            }
        };

        ActionListener pauseSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playing) {
                    playing = false;
                    gridPanel.pause();
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
            }
        };

        ActionListener slowDownSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rate *= 0.5;
                if (rate < 1)
                    rate = 1;
                gridPanel.setPause_len((long) (1000/rate));
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

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        // open window
        setVisible(true);
    }
}
