package presentation;

import businessLogic.TeacherDashboardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeacherDashboard extends JFrame {
    private TeacherDashboardController controller;

    private JLabel headerLabel;
    private JButton createAssignmentButton;
    private JButton editAssignmentButton;
    private JButton uploadAssignmentButton;
    private JButton setDeadlineButton;
    private JButton viewSubmittedAssignmentsButton;
    private JButton viewScheduleButton;
    private JButton logoutButton;
    private JTextArea submittedAssignmentsArea;

    public TeacherDashboard() {
     //   this.controller = controller;
        initComponents();
        setUndecorated(true);
        setupListeners();
        setupLayout();
        setLayoutProperties();
    }

    private void initComponents() {
        // Initialize UI components
        // ...
        headerLabel = new JLabel("Welcome, " + LoginGUI.getUserName());
        createAssignmentButton = new JButton("Create Assignment");
        editAssignmentButton = new JButton("Edit Assignment");
        uploadAssignmentButton = new JButton("Upload Assignment File");
        setDeadlineButton = new JButton("Set Assignment Deadline");
        viewSubmittedAssignmentsButton = new JButton("View Submitted Assignments");
        viewScheduleButton = new JButton("View Schedule");
        logoutButton = new JButton("Logout");
        submittedAssignmentsArea = new JTextArea(15, 20);
        submittedAssignmentsArea.setEditable(false);
        submittedAssignmentsArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // Additional UI components and configurations...

        // Set up button styles and mouse listeners
        customizeButton(createAssignmentButton);
        customizeButton(editAssignmentButton);
        customizeButton(uploadAssignmentButton);
        customizeButton(setDeadlineButton);
        customizeButton(viewSubmittedAssignmentsButton);
        customizeButton(viewScheduleButton);
        customizeButton(logoutButton);
    }

    private void setupListeners() {
        createAssignmentButton.addActionListener(e -> createAssignment());
        editAssignmentButton.addActionListener(e -> editAssignment());
        uploadAssignmentButton.addActionListener(e -> uploadAssignment());
        setDeadlineButton.addActionListener(e -> setDeadline());
        viewSubmittedAssignmentsButton.addActionListener(e -> viewSubmittedAssignments());
        viewScheduleButton.addActionListener(e -> viewSchedule());
        logoutButton.addActionListener(e -> logout());

        submittedAssignmentsArea.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Perform actions when the submitted assignments area is clicked
                JOptionPane.showMessageDialog(TeacherDashboard.this, "Clicked on submitted assignments area!");
            }
        });
    }

    private void setupLayout() {
        // Set up UI layout
        // ...
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JPanel headerPanel = new JPanel();
            headerPanel.add(headerLabel);
            headerPanel.setSize(750,550);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.add(createPaddedButton(createAssignmentButton));
            buttonPanel.add(createPaddedButton(editAssignmentButton));
            buttonPanel.add(createPaddedButton(uploadAssignmentButton));
            //buttonPanel.add(createPaddedButton(setDeadlineButton));
            buttonPanel.add(createPaddedButton(viewSubmittedAssignmentsButton));
            buttonPanel.add(createPaddedButton(viewScheduleButton));
            buttonPanel.add(createPaddedButton(logoutButton));

            JPanel assignmentsPanel = new JPanel(new BorderLayout());
            assignmentsPanel.setBorder(BorderFactory.createTitledBorder("Submitted Assignments"));
            JScrollPane scrollPane = new JScrollPane(submittedAssignmentsArea);
            assignmentsPanel.add(scrollPane, BorderLayout.CENTER);

            mainPanel.add(headerPanel, BorderLayout.NORTH);
            mainPanel.setSize(750,550);
            mainPanel.add(buttonPanel, BorderLayout.WEST);
            mainPanel.add(assignmentsPanel, BorderLayout.CENTER);

            add(mainPanel);
            setVisible(true);
        }


    private Component createPaddedButton(JButton button) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(button);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Adding padding to buttons
        return panel;
    }
    private void setLayoutProperties() {
        setSize(750, 550);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
    private void customizeButton(JButton button) {
        // Customize button appearance and mouse interactions
        // ...
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(Color.LIGHT_GRAY); // Change background color on hover
                    button.setForeground(Color.BLUE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(null); // Reset background color on exit
                    button.setForeground(Color.BLACK);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    button.setBackground(Color.GRAY); // Change background color on click
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    button.setBackground(null); // Reset background color after click
                }
            });
        }


    // Action methods for button functionalities
    private void createAssignment() {
        controller.createAssignment();
    }

    private void editAssignment() {
        controller.editAssignment();
    }

    private void uploadAssignment() {
        controller.uploadAssignment();
    }

    private void setDeadline() {
        controller.setDeadline();
    }

    private void viewSubmittedAssignments() {
        controller.viewSubmittedAssignments();
    }

    private void viewSchedule() {
        controller.viewSchedule();
    }

    private void logout() {
        controller.logout();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          //  TeacherDashboardController controller = new TeacherDashboardController();
            new TeacherDashboard();
            // Assuming you have a method in the TeacherDashboardController to set the TeacherDashboard reference
           // controller.setTeacherDashboard(teacherDashboard);
        });
    }
}
