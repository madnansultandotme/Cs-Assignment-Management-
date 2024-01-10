package presentation;
import javax.swing.*;
import java.awt.*;

public class UniversityGUI extends JFrame {

    public UniversityGUI(String userType) {
        setTitle(userType);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JPanel centerPanel = new JPanel(new GridLayout(0, 2, 0, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        RoundedField idField = new RoundedField();
        RoundedField firstNameField = new RoundedField();
        RoundedField lastNameField = new RoundedField();
        RoundedField addressField = new RoundedField();
        RoundedField additionalInfoField = new RoundedField();

        if (userType.equals("Teacher")) {
            addLabelAndField(centerPanel, "Teacher ID:", idField);
            addLabelAndField(centerPanel, "Joining Date:", additionalInfoField);
        } else {
            addLabelAndField(centerPanel, "Student ID:", idField);
            addLabelAndField(centerPanel, "Session:", additionalInfoField);
        }
        addLabelAndField(centerPanel, "First Name:", firstNameField);
        addLabelAndField(centerPanel, "Last Name:", lastNameField);
        addLabelAndField(centerPanel, "Address:", addressField);

        panel.add(centerPanel, BorderLayout.CENTER);

        RoundedButton submitButton = new RoundedButton("Proceed");
        submitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Information Saved!");
            clearFields(idField, firstNameField, lastNameField, addressField, additionalInfoField);
        });
        panel.add(submitButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
        setResizable(false);
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField field) {
        panel.add(new JLabel(labelText));
        panel.add(field);
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    class RoundedField extends JTextField {
        RoundedField() {
            setOpaque(true); // Make the text field opaque
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (!isOpaque() && getBorder() instanceof RoundedBorder) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15); // You can adjust the roundness here
                super.paintComponent(g2);
                g2.dispose();
            } else {
                super.paintComponent(g);
            }
        }
    }

    class RoundedButton extends JButton {
        RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding for the button text
         // setBackground(Color.BLUE); // Modify as needed
            setBackground(null);
            setForeground(Color.BLACK); // Modify as needed
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (!isOpaque() && getBorder() instanceof RoundedBorder) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15); // You can adjust the roundness here
                super.paintComponent(g2);
                g2.dispose();
            } else {
                super.paintComponent(g);
            }
        }
    }
}
