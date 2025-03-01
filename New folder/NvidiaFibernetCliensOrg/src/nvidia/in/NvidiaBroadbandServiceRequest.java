package nvidia.in;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class NvidiaBroadbandServiceRequest extends JFrame {
    // Add constant for field height
    private static final int FIELD_HEIGHT = 25;
    private JTextField requestIdField;
    private JTextField userField;
    private JComboBox<String> requestTypeCombo;
    private JTextField dateField;
    private JButton submitButton;
    private JButton clearButton;
    private JButton goBackButton;
    // private JButton retrieveButton;
    
    // Database connection
    private ConnectionJDBC dbConnection;
    
    public NvidiaBroadbandServiceRequest() {
        // Set up the frame
        super("Nvidia Broadband - Service Request");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(true);
        
        // Initialize database connection
        dbConnection = new ConnectionJDBC();
        
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(42, 116, 180));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create title label
        JLabel titleLabel = new JLabel("Service Request Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Form panel with GridBagLayout for form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(42, 116, 180));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Request ID
        JLabel requestIdLabel = new JLabel("Request ID:");
        requestIdLabel.setForeground(Color.WHITE);
        requestIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        formPanel.add(requestIdLabel, gbc);
        
        JPanel requestIdPanel = new JPanel(new BorderLayout(5, 0));
        requestIdPanel.setBackground(new Color(42, 116, 180));
        
        requestIdField = new JTextField();
        requestIdField.setText(generateNextRequestId());
        requestIdField.setPreferredSize(new Dimension(380, FIELD_HEIGHT));
        requestIdPanel.add(requestIdField, BorderLayout.CENTER);
        
        // retrieveButton = new JButton("Retrieve");
        // retrieveButton.setMargin(new Insets(0, 5, 0, 5));
        // retrieveButton.addActionListener(e -> retrieveRequestData());
        // requestIdPanel.add(retrieveButton, BorderLayout.EAST);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        formPanel.add(requestIdPanel, gbc);
        
        // User
        JLabel userLabel = new JLabel("User:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        formPanel.add(userLabel, gbc);
        
        userField = new JTextField();
        userField.setPreferredSize(new Dimension(380, FIELD_HEIGHT));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.8;
        formPanel.add(userField, gbc);
        
        // Request Type
        JLabel requestTypeLabel = new JLabel("Request Type:");
        requestTypeLabel.setForeground(Color.WHITE);
        requestTypeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        formPanel.add(requestTypeLabel, gbc);
        
        String[] requestTypes = {
            "Connection Issue",
            "Speed Problem",
            "Bill Related",
            "Technical Support",
            "Plan Upgrade",
            "Network Outage",
            "Router Configuration",
            "Installation Request",
            "Service Relocation",
            "General Inquiry"
        };
        requestTypeCombo = new JComboBox<>(requestTypes);
        requestTypeCombo.setPreferredSize(new Dimension(380, FIELD_HEIGHT));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.8;
        formPanel.add(requestTypeCombo, gbc);
        
        // Date with spinner panel
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.2;
        formPanel.add(dateLabel, gbc);
        
        JPanel datePanel = new JPanel(new BorderLayout(5, 0));
        datePanel.setBackground(new Color(42, 116, 180));
        
        // Get current date in yyyy-MM-dd format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        
        dateField = new JTextField(currentDate);
        dateField.setPreferredSize(new Dimension(350, FIELD_HEIGHT));
        datePanel.add(dateField, BorderLayout.CENTER);
        
        JPanel spinnerPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        spinnerPanel.setPreferredSize(new Dimension(30, FIELD_HEIGHT));
        
        JButton upButton = new JButton("▲");
        upButton.setMargin(new Insets(0, 2, 0, 2));
        upButton.setFont(new Font("Arial", Font.PLAIN, 8));
        upButton.addActionListener(e -> adjustDate(1));
        spinnerPanel.add(upButton);
        
        JButton downButton = new JButton("▼");
        downButton.setMargin(new Insets(0, 2, 0, 2));
        downButton.setFont(new Font("Arial", Font.PLAIN, 8));
        downButton.addActionListener(e -> adjustDate(-1));
        spinnerPanel.add(downButton);
        
        datePanel.add(spinnerPanel, BorderLayout.EAST);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.8;
        formPanel.add(datePanel, gbc);
        
        // Add form panel to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(new Color(42, 116, 180));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        buttonPanel.add(Box.createHorizontalGlue());
        
        // Submit button
        submitButton = new JButton("Submit Request");
        submitButton.setBackground(new Color(51, 122, 183));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(150, 35));
        submitButton.addActionListener(e -> submitForm());
        buttonPanel.add(submitButton);
        
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        
        // Clear Form button
        clearButton = new JButton("Clear Form");
        clearButton.setBackground(new Color(51, 122, 183));
        clearButton.setForeground(Color.WHITE);
        clearButton.setPreferredSize(new Dimension(120, 35));
        clearButton.addActionListener(e -> clearForm());
        buttonPanel.add(clearButton);
        
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        
        // Go Back button
        goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(51, 122, 183));
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setPreferredSize(new Dimension(120, 35));
        goBackButton.addActionListener(e -> goBack());
        buttonPanel.add(goBackButton);
        
        buttonPanel.add(Box.createHorizontalGlue());
        
        // Add button panel to main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add the main panel to the frame
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        setContentPane(scrollPane);
        
        // Set minimum size to accommodate all components
        mainPanel.setPreferredSize(new Dimension(600, 400));
        
        // Center the frame on the screen
        setLocationRelativeTo(null);
    }
    
    private String generateNextRequestId() {
        try {
            // Query to get the maximum request_id
            String query = "SELECT MAX(request_id) FROM service_request";
            ResultSet rs = dbConnection.s.executeQuery(query);
            
            if (rs.next()) {
                int maxId = rs.getInt(1);
                return String.valueOf(maxId + 1);
            } else {
                return "101"; // Default starting ID if no records exist
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "101"; // Default in case of error
        }
    }
    
    private void retrieveRequestData() {
        try {
            String requestId = requestIdField.getText().trim();
            if (requestId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter a Request ID to retrieve", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Prepare SQL query
            String query = "SELECT * FROM service_request WHERE request_id = ?";
            PreparedStatement pstmt = dbConnection.c.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(requestId));
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Populate form fields with data from database
                userField.setText(rs.getString("user"));
                
                // Set the correct item in the combo box
                String type = rs.getString("type");
                for (int i = 0; i < requestTypeCombo.getItemCount(); i++) {
                    if (requestTypeCombo.getItemAt(i).equals(type)) {
                        requestTypeCombo.setSelectedIndex(i);
                        break;
                    }
                }
                
                // Format the date
                java.sql.Date sqlDate = rs.getDate("date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dateField.setText(sdf.format(sqlDate));
                
                JOptionPane.showMessageDialog(this, 
                    "Request data retrieved successfully", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "No request found with ID: " + requestId, 
                    "Not Found", 
                    JOptionPane.WARNING_MESSAGE);
            }
            
            pstmt.close();
            rs.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid Request ID format. Please enter a numeric value.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Database error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void adjustDate(int days) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = sdf.parse(dateField.getText());
            long time = currentDate.getTime();
            time += days * 24 * 60 * 60 * 1000L;
            Date newDate = new Date(time);
            dateField.setText(sdf.format(newDate));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    
    private void submitForm() {
        // Validate form
        if (requestIdField.getText().isEmpty() || userField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in all required fields", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Check if the request ID already exists
            int requestId = Integer.parseInt(requestIdField.getText().trim());
            String user = userField.getText().trim();
            String type = (String) requestTypeCombo.getSelectedItem();
            String dateStr = dateField.getText().trim();
            
            // Convert date string to java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(dateStr);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            
            // Check if request ID already exists
            String checkQuery = "SELECT COUNT(*) FROM service_request WHERE request_id = ?";
            PreparedStatement checkStmt = dbConnection.c.prepareStatement(checkQuery);
            checkStmt.setInt(1, requestId);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            
            PreparedStatement pstmt;
            String query;
            
            if (count > 0) {
                // Update existing record
                query = "UPDATE service_request SET user = ?, type = ?, status = ?, date = ? WHERE request_id = ?";
                pstmt = dbConnection.c.prepareStatement(query);
                pstmt.setString(1, user);
                pstmt.setString(2, type);
                pstmt.setString(3, "Pending"); // Default status for updates
                pstmt.setDate(4, sqlDate);
                pstmt.setInt(5, requestId);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, 
                    "Service request updated successfully!\nRequest ID: " + requestId, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Insert new record
                query = "INSERT INTO service_request (request_id, user, type, status, date) VALUES (?, ?, ?, ?, ?)";
                pstmt = dbConnection.c.prepareStatement(query);
                pstmt.setInt(1, requestId);
                pstmt.setString(2, user);
                pstmt.setString(3, type);
                pstmt.setString(4, "Pending"); // Default status for new requests
                pstmt.setDate(5, sqlDate);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, 
                    "Service request submitted successfully!\nRequest ID: " + requestId, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
            // Clean up resources
            pstmt.close();
            checkStmt.close();
            rs.close();
            
            // Generate a new request ID for the next submission
            requestIdField.setText(generateNextRequestId());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid Request ID format. Please enter a numeric value.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Database error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid date format. Please use YYYY-MM-DD format.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        userField.setText("");
        requestTypeCombo.setSelectedIndex(0);
        
        // Reset date to current date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateField.setText(sdf.format(new Date()));
        
        // Generate a new request ID
        requestIdField.setText(generateNextRequestId());
    }
    
    private void goBack() {
        // In a real application, this might navigate to a previous screen
        JOptionPane.showMessageDialog(this, 
            "Navigating back to previous screen", 
            "Navigation", 
            JOptionPane.INFORMATION_MESSAGE);

            try {
                UserDashboard us=new UserDashboard();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    
    public static void main(String[] args) {
        // Create and display the form
        SwingUtilities.invokeLater(() -> {
            NvidiaBroadbandServiceRequest form = new NvidiaBroadbandServiceRequest();
            form.setVisible(true);
        });
    }
}