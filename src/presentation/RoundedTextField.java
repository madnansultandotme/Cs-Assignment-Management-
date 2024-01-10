package presentation;
import javax.swing.*;
import java.awt.*;

class RoundedTextField extends JTextField {
    private int arc;

    public RoundedTextField(int columns, int arc) {
        super(columns);
        this.arc = arc;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
        super.paintComponent(g2);
        g2.dispose();
    }

    public char[] getPassword() {
        return getText().toCharArray();
    }
}
