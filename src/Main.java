import presentation.*;
import javax.swing.*;
import java.awt.*;

class BackgroundFrame extends JFrame {
    public BackgroundFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                Color color1 = new Color(0, 102, 204);
                Color color2 = new Color(255, 153, 51);

                GradientPaint gp = new GradientPaint(
                        0, 0, color1,
                        getWidth(), getHeight(), color2
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BorderLayout());


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        gradientPanel.add(buttonPanel, BorderLayout.EAST);

        setContentPane(gradientPanel);
        setVisible(true);
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BackgroundFrame backgroundFrame = new BackgroundFrame();

            // Launch the LoginGUI after a delay (you can adjust the delay as needed)
            Timer timer = new Timer(100, e -> {
                LoginGUI loginFrame = new LoginGUI();
                loginFrame.setLocationRelativeTo(null);
                loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the login frame
                loginFrame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE); // Ensure it remains on top
                loginFrame.setVisible(true);
            });
            timer.setRepeats(false); // Run only once
            timer.start();
        });
    }
}
