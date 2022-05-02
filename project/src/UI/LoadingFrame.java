package UI;

import javax.swing.*;
import java.awt.*;

/**
 * @author Samuel Nix
 *
 * @description Creates a frame with a smiley face :)
 */
public class LoadingFrame extends JFrame {
    public LoadingFrame() {
        super("Loading...");

        add(new LoadComponent());
        JLabel label = new JLabel("Loading...", SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        add(label, BorderLayout.SOUTH);
        pack();
        repaint();
    }

    /**
     * Creates a JComponent
     */
    public class LoadComponent extends JComponent {
        public LoadComponent() {
            super();
            setPreferredSize(new Dimension(500, 500));
        }

        /**
         * New paintcomponent
         * @param g graphics
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D canvas = (Graphics2D) g;
            canvas.setStroke(new BasicStroke(3));
            canvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            canvas.setColor(new Color(255, 192, 0));
            canvas.fillOval(150, 150, 200, 200);
            canvas.setColor(Color.BLACK);
            canvas.drawOval(150, 150, 200, 200);

            canvas.fillOval(200, 210, 20, 20);
            canvas.fillOval(280, 210, 20, 20);
            canvas.drawArc(190, 240, 120, 80, 190, 160);
        }
    }
}
