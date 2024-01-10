package presentation;

import businessLogic.Lecture;
import dataAccess.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;

public class LectureUploadGUI extends JFrame {
    private JLabel lectureNoLabel;
    private JTextField lectureNoField;
    private JLabel mediaNameLabel;
    private JTextField mediaNameField;
    private JLabel courseCodeLabel;
    private JComboBox<String> courseCodeComboBox; // Using JComboBox for course selection
    private JLabel descriptionLabel;
    private JTextArea descriptionArea;
    private JButton uploadButton;

    public LectureUploadGUI() {
        setTitle("Upload Lecture");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600); // Increased window size
        setLocationRelativeTo(null);
        initComponents();
        setupLayout();
        setupListeners();
        setVisible(true);
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Increased space between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        addComponent(panel, gbc, lectureNoLabel, 0, 0);
        addComponent(panel, gbc, lectureNoField, 1, 0);
        addComponent(panel, gbc, mediaNameLabel, 0, 1);
        addComponent(panel, gbc, mediaNameField, 1, 1);
        addComponent(panel, gbc, courseCodeLabel, 0, 2);
        addComponent(panel, gbc, courseCodeComboBox, 1, 2); // Using JComboBox for course selection
        addComponent(panel, gbc, descriptionLabel, 0, 3);
        addComponent(panel, gbc, new JScrollPane(descriptionArea), 1, 3);

        JButton showCredentialsButton = new JButton("Preview");
        showCredentialsButton.addActionListener(e -> showCredentials());
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(showCredentialsButton, gbc);

        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(e -> uploadLecture());
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(uploadButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, Component component, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }

    private void initComponents() {
        lectureNoLabel = new JLabel("Lecture Number:");
        lectureNoField = new JTextField(20); // Increased field size
        mediaNameLabel = new JLabel("Lecture Name:");
        mediaNameField = new JTextField(40); // Increased field size
        courseCodeLabel = new JLabel("Course Code:");
        courseCodeComboBox = new JComboBox<>(getCourseCodes()); // Using JComboBox for course selection
        descriptionLabel = new JLabel("Description:");
        descriptionArea = new JTextArea(5, 20);

        JButton showCredentialsButton = new JButton("Preview");
        showCredentialsButton.addActionListener(e -> showCredentials());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        panel.add(lectureNoLabel);
        panel.add(lectureNoField);
        panel.add(mediaNameLabel);
        panel.add(mediaNameField);
        panel.add(courseCodeLabel);
        panel.add(courseCodeComboBox);
        panel.add(descriptionLabel);
        panel.add(new JScrollPane(descriptionArea));

        panel.add(showCredentialsButton);

        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(e -> uploadLecture());
        panel.add(uploadButton);

        add(panel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        uploadButton.addActionListener(e -> uploadLecture());
    }

    private void showCredentials() {
        int lectureNo = Integer.parseInt(lectureNoField.getText());
        String mediaName = mediaNameField.getText();
        String courseCode = String.valueOf(courseCodeComboBox.getSelectedItem()); // Get selected course code
        String description = descriptionArea.getText();

        LocalDateTime uploadedOn = LocalDateTime.now(); // Current timestamp

        String credentialsMessage = "Lecture Number: " + lectureNo + "\n" +
                "Media Name: " + mediaName + "\n" +
                "Course Code: " + courseCode + "\n" +
                "Description: " + description + "\n" +
                "Uploaded On: " + uploadedOn;

        int option = JOptionPane.showConfirmDialog(this, credentialsMessage, "Uploaded Lecture Credentials", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            uploadFile(); // Call method to upload file to server
        }
    }

    private void uploadFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Here, you can write logic to upload the selected file to the server
            // You might need to use FTP, HTTP POST, or any other protocol depending on your server setup
            // For demonstration, we'll just display a message
            JOptionPane.showMessageDialog(this, "File uploaded to server!");
        }
    }

    private void uploadLecture() {
        int lectureNo = Integer.parseInt(lectureNoField.getText());
        String mediaName = mediaNameField.getText();
        String courseCode = String.valueOf(courseCodeComboBox.getSelectedItem()); // Get selected course code
        String description = descriptionArea.getText();
        LocalDateTime uploadedOn = LocalDateTime.now(); // Current timestamp

        Lecture lecture = new Lecture(lectureNo, mediaName, null, courseCode, description, uploadedOn);
        // Note: Media location is set to null as it won't be used in this version.

        // Call a method from your DatabaseHandler to insert the lecture into the database
        DatabaseHandler.insertLecture(lecture);

        JOptionPane.showMessageDialog(this, "Lecture uploaded successfully!");
        dispose();
    }

    private String[] getCourseCodes() {
        // Replace this with your logic to retrieve course codes from the database or another data source
        return new String[]{"Course 1", "Course 2", "Course 3"};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LectureUploadGUI::new);
    }
}
