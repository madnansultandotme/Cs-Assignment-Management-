package presentation;

import businessLogic.StudentDashboardController;

import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {
   private StudentDashboardController controller;

    private JLabel headerLabel;
    private JButton viewAssignmentsButton;
    private JButton viewAssignmentMarksButton;
    private JButton submitAssignmentButton;
    private JButton editAssignmentButton;
    private JButton viewScheduleButton;
    private JButton logoutButton;
    private JTextArea scheduleTextArea;
    private JButton downloadScheduleButton;

    public StudentDashboard() {
      //  this.controller = controller;
        setTitle("Student Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 550);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);
        initComponents();
        setupListeners();
        setupLayout();
    }

    private void initComponents() {
        headerLabel = new JLabel("Welcome, " + LoginGUI.getUserName());

        viewAssignmentsButton = createButton("View Assignments");
        viewAssignmentMarksButton = createButton("View Assignment Marks");
        submitAssignmentButton = createButton("Submit Assignment");
        editAssignmentButton = createButton("Edit Assignment");
        viewScheduleButton = createButton("View Schedule");
        logoutButton = createButton("Logout");

        scheduleTextArea = new JTextArea(10, 20);
        scheduleTextArea.setEditable(false);
        scheduleTextArea.setText(generateTimetable());

        downloadScheduleButton = createButton("Download Timetable");
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setBackground(Color.LIGHT_GRAY);

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setForeground(Color.BLACK);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLUE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLACK);
            }
        });

        return button;
    }

    private void setupListeners() {
        viewAssignmentsButton.addActionListener(e -> controller.viewAssignments());
        viewAssignmentMarksButton.addActionListener(e -> controller.viewAssignmentMarks());
        submitAssignmentButton.addActionListener(e -> controller.submitAssignment());
        editAssignmentButton.addActionListener(e -> controller.editAssignment());
        viewScheduleButton.addActionListener(e -> controller.viewSchedule());
        logoutButton.addActionListener(e -> controller.logout());
        downloadScheduleButton.addActionListener(e -> controller.downloadTimetable());
    }

    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        headerPanel.add(headerLabel);
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        buttonPanel.add(viewAssignmentsButton);
        buttonPanel.add(viewAssignmentMarksButton);
        buttonPanel.add(submitAssignmentButton);
        buttonPanel.add(editAssignmentButton);
        buttonPanel.add(viewScheduleButton);
        buttonPanel.add(logoutButton);

        JPanel schedulePanel = new JPanel(new BorderLayout());
        schedulePanel.add(new JScrollPane(scheduleTextArea), BorderLayout.CENTER);

        JPanel downloadPanel = new JPanel(new FlowLayout());
        downloadPanel.add(downloadScheduleButton);

        schedulePanel.add(downloadPanel, BorderLayout.SOUTH);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(schedulePanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    // Dummy method to generate timetable content
    private String generateTimetable() {
        return "Day 1:\nMathematics - 9:15 AM - 10:45 AM\nPhysics - 11:00 AM - 12:30 PM\n...";
    }
    public static void main(String[] args) {
        StudentDashboard studentDashboard = new StudentDashboard();
    }
}

