package presentation;
import businessLogic.LoginService;
import businessLogic.SignUpController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SignUpUI extends JFrame implements ActionListener {
    protected static JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeCombo;
    private static String LoggedIn;
    protected LoginService loginService;
    public SignUpUI() {
        // loginService=new businesslogic.LoginService()
        //super(loginService);
        setUndecorated(true);
        setTitle("SignUp Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 550); // Adjusted size
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 0, 20)); // Increased vertical spacing
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Added margin

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new RoundedTextField(10, 20); // Adjusted text field size

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*');

        JLabel userTypeLabel = new JLabel("User Type:");
        String[] userTypes = {"Student", "Teacher"};
        userTypeCombo = new JComboBox<>(userTypes);

        JButton SignUpButton = new JButton("SignUp");
        SignUpButton.addActionListener(this);

        ActionListener enterListener = e -> performSignUp(); // Define an action listener

        usernameField.addActionListener(enterListener); // Add action listener to the username field
        passwordField.addActionListener(enterListener); // Add action listener to the password field
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(userTypeLabel);
        panel.add(userTypeCombo);
        panel.add(new JLabel());
        panel.add(SignUpButton);
        panel.add(new JLabel());
      //  panel.add(signUpLabel);

        add(panel);
        setVisible(true);
        setResizable(false);
    }

    public static String getUserName() {
        return LoggedIn;
    }

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

    public void performSignUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        LoggedIn = usernameField.getText();
        String userType = (String) userTypeCombo.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean isValidUser = SignUpController.addSignUp(username, password, userType);
            if (isValidUser) {
                setVisible(false);
                if (userType.equals("Student")) {
                    SwingUtilities.invokeLater(() -> new UniversityGUI(userType));
                } else {
                    SwingUtilities.invokeLater(()->new UniversityGUI(userType));
                }
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials for " + userType, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SignUp")) {
            performSignUp();
        }
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        userTypeCombo.setSelectedIndex(0);
    }
}
