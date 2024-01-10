package presentation;

import businessLogic.LoginService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame implements ActionListener {
    protected static JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeCombo;
    private static String LoggedIn;
    protected LoginService loginService;
    public LoginGUI() {
       // loginService=new businesslogic.LoginService()
        //super(loginService);
        setUndecorated(true);
        setTitle("Login Page");
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

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        ActionListener enterListener = e -> performLogin(); // Define an action listener

        usernameField.addActionListener(enterListener); // Add action listener to the username field
        passwordField.addActionListener(enterListener); // Add action listener to the password field
        JLabel signUpLabel = new JLabel("<html><u>Sign Up</u></html>");
        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpLabel.setForeground(Color.BLUE.darker());
        signUpLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                if (e.getSource() == signUpLabel) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new SignUpUI();
                        }
                    });
                }
            }
        });

        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getRootPane().getInputMap(condition);
        ActionMap actionMap = getRootPane().getActionMap();

        // Example: Pressing 'Enter' triggers login
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterKey, "performSignUp");
        actionMap.put("performSignUp", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(userTypeLabel);
        panel.add(userTypeCombo);
        panel.add(new JLabel());
        panel.add(loginButton);
        panel.add(new JLabel());
        panel.add(signUpLabel);

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

    public void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        LoggedIn = usernameField.getText();
        String userType = (String) userTypeCombo.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean isValidUser = loginService.authenticateUser(username, password, userType);
            if (isValidUser) {
                setVisible(false);
                if (userType.equals("Student")) {
                    SwingUtilities.invokeLater(StudentDashboard::new);
                } else {
                    SwingUtilities.invokeLater(TeacherDashboard::new);
                }
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials for " + userType, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            performLogin();
        }
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        userTypeCombo.setSelectedIndex(0);
    }
}
