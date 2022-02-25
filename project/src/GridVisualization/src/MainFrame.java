import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
    public GridPanel gridPanel;
    private Container c;

    public MainFrame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void startWindow() {
        // set up initial rules
        /*for (int surviveRule:initialSurviveRules) {
            surviveRules.add(surviveRule);
        }

        for (int bornRule:initialBornRules) {
            bornRules.add(bornRule);
        }*/

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("ColorChooser.font", font);*/

        setLayout(new BorderLayout());
        c = getContentPane();
        c.setBackground(Color.GREEN);

        /// create grid panel
        gridPanel = new GridPanel(20, 20, 15, 20*20, 20*15, 0, 0);
        add(gridPanel, BorderLayout.CENTER);
        gridPanel.gridOn = !gridPanel.gridOn;


        this.setMinimumSize(new Dimension(gridPanel.gridPixelWidth-60, gridPanel.gridPixelHeight));
        //this.setSize(new Dimension(gridPanel.gridPixelWidth, gridPanel.gridPixelHeight));

        /// create menu bar
        /*mainMenuBar = new MainMenuBar();
        setJMenuBar(mainMenuBar);*/

        /// create tool bar
        /*toolBar = new ToolBar();
        add(toolBar, BorderLayout.LINE_START);*/


        //// add main key bindings and listeners
        /// camera move listeners (WASD + arrow keys)
        /*gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("UP"), MOVE_CAM_UP);
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("W"), MOVE_CAM_UP);
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DOWN"), MOVE_CAM_DOWN);
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("S"), MOVE_CAM_DOWN);
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_CAM_RIGHT);
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("D"), MOVE_CAM_RIGHT);
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("LEFT"), MOVE_CAM_LEFT);
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("A"), MOVE_CAM_LEFT);*/

        /*gridPanel.getActionMap().put(MOVE_CAM_UP, new MoveCamAction(0, -10));
        gridPanel.getActionMap().put(MOVE_CAM_DOWN, new MoveCamAction(0, 10));
        gridPanel.getActionMap().put(MOVE_CAM_RIGHT, new MoveCamAction(10, 0));
        gridPanel.getActionMap().put(MOVE_CAM_LEFT, new MoveCamAction(-10, 0));*/

        /// zoom listeners (CTRL+(=/-))
        /*gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK), ZOOM_OUT);
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.CTRL_DOWN_MASK), ZOOM_IN);

        gridPanel.getActionMap().put(ZOOM_OUT, new ZoomAction(false));
        gridPanel.getActionMap().put(ZOOM_IN, new ZoomAction(true));

        // pause and time listeners
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_SPACE), PAUSE_UNPAUSE);
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ENTER), STEP_FRAME);

        gridPanel.getActionMap().put(PAUSE_UNPAUSE, new PauseAction());
        gridPanel.getActionMap().put(STEP_FRAME, new StepFrameAction());

        // reset listener
        gridPanel.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke((char) KeyEvent.VK_BACK_SPACE), RESET);
        gridPanel.getActionMap().put(RESET, new ResetAction());*/

        // add window resize listener
        //addComponentListener(new ComponentResize());

        //setIconImage(new ImageIcon(gameIcon).getImage());

        pack();
        // open window
        setVisible(true);
    }

    public synchronized void updateGrid(ArrayList<ArrayList<Point>> prepareUpdateLists) {
        /// update grid
        gridPanel.gridLock.writeLock().lock();
        try {
            // increment age
            gridPanel.grid.replaceAll((point, age) -> age+1);

            // add and delete coordinates
            for (Point addCoordinate:prepareUpdateLists.get(0)) {
                gridPanel.grid.put(addCoordinate, 0);
            }

            for (Point delCoordinate:prepareUpdateLists.get(1)) {
                gridPanel.grid.remove(delCoordinate);
            }
            gridPanel.steps += 1;
        } finally {
            gridPanel.gridLock.writeLock().unlock();
        }
    }

    /*private class GridToggleAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            gridPanel.gridOn = !gridPanel.gridOn;
            //toolBar.updateGridButton(gridPanel.gridOn);
            gridPanel.repaint();
        }*/
}
