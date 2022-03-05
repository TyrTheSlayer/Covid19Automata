/**
 * @author Samuel Nix, Jonathan Carsten
 *
 * Main function for simulation
 */

import Grid.MainFrame;
import custom_classes.SimSettings;
import UI.UI.UI;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) {

        SimSettings settings = new SimSettings();
        /* Initialize UI object here
            ...
           Update settings from user through UI object
         */
        runSim();
    }

    public static void runSim() {
        /**
         * Initialize Person ArrayList and pass to GridPanel through Mainframe
         */
        System.out.println("Calling runSim()");

        //MainFrame mainFrame = new MainFrame("Test");

        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    //mainFrame.startWindow();
                }
            });
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
