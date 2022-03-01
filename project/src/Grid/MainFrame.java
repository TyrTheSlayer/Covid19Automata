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

        add(gridPanel, BorderLayout.CENTER);
        // set minimum size for window
        this.setMinimumSize(new Dimension(gridPanel.gridPixelWidth, gridPanel.gridPixelHeight));
        this.setResizable(false);

        ActionListener playSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        ActionListener pauseSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        ActionListener speedUpSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        ActionListener slowDownSim = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
