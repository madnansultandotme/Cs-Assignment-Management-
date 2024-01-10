package presentation;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AssignmentUploadGUI extends JFrame {
    private JLabel assignmentNumberLabel;
    private JTextField assignmentNumberField;
    private JLabel deadlineLabel;
    private JDateChooser deadlineChooser;
    private JButton uploadButton;
    private JButton pickFileButton; // Declaring pickFileButton here

    public AssignmentUploadGUI() {
        setTitle("Upload Assignment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 550);
        setLocationRelativeTo(null);
        initComponents();
        setupLayout();
        setVisible(true);
    }

    private void initComponents() {
        assignmentNumberLabel = new JLabel("Assignment Number:");
        assignmentNumberField = new JTextField(15);

        deadlineLabel = new JLabel("Deadline:");
        deadlineChooser = new JDateChooser();

        uploadButton = new JButton("Upload Assignment");
        uploadButton.addActionListener(e -> uploadAssignment());

        pickFileButton = new JButton("Pick Assignment File"); // Initializing pickFileButton
        pickFileButton.addActionListener(e -> pickAssignmentFile());
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        addComponent(panel, gbc, assignmentNumberLabel, 0, 0);
        addComponent(panel, gbc, assignmentNumberField, 1, 0);
        addComponent(panel, gbc, deadlineLabel, 0, 1);
        addComponent(panel, gbc, deadlineChooser, 1, 1);
        addComponent(panel, gbc, uploadButton, 0, 2);
        addComponent(panel, gbc, pickFileButton, 1, 2); // Adding pickFileButton to the panel

        add(panel, BorderLayout.CENTER);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, Component component, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }

    private void pickAssignmentFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Use the selectedFile (store it, display its information, etc.)
            // For instance, you might display the file name in a label or text field.
            // assignmentFileField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void uploadAssignment() {
        String assignmentNumber = assignmentNumberField.getText();
        Date deadline = deadlineChooser.getDate();
        LocalDate localDeadline = deadline.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Process the assignment upload logic using assignmentNumber, localDeadline, and the selected file.
        // For demonstration purposes, displaying a success message.
        JOptionPane.showMessageDialog(this, "Assignment uploaded successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AssignmentUploadGUI::new);
    }
}
