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

public class MainFrame extends JFrame{
    public GridPanel gridPanel;

    public MainFrame(String title) {
        super(title);
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
        gridPanel = new GridPanel(15, 50, 50, 15*50, 15*50, 0, 0);

        add(gridPanel, BorderLayout.CENTER);
        // set minimum size for window
        this.setMinimumSize(new Dimension(gridPanel.gridPixelWidth, gridPanel.gridPixelHeight));

        pack();
        // open window
        setVisible(true);
    }
}
