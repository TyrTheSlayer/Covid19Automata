package GridVisualization;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame("Test");

        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    mainFrame.startWindow();
                }
            });
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
